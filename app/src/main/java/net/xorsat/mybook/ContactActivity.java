package net.xorsat.mybook;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ContactActivity extends Activity {
    Activity context;
    TextView mTextViewTitle;
    TextView mTextViewName;
    TextView mTextViewEmail;
    TextView mTextViewPhone;
    TextView mTextViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        context = this;
        init();
    }

    private void init() {
        context = this;
        mTextViewTitle = (TextView) findViewById(R.id.action_bar_title);
        mTextViewName = (TextView) findViewById(R.id.contact_editText_name);
        mTextViewEmail = (TextView) findViewById(R.id.contact_editText_email);
        mTextViewPhone = (TextView) findViewById(R.id.contact_editText_phone);
        mTextViewMessage = (TextView) findViewById(R.id.contact_editText_message);
    }

    private boolean isValidated() {
        if (mTextViewName.getText().length() == 0) {
            //H.showMessage(context,"Give name");
            return false;
        }
        if (mTextViewEmail.getText().length() == 0) {
            // H.showMessage(context,"Give email");
            return false;
        }
        if (mTextViewMessage.getText().length() == 0) {
            // H.showMessage(context,"Give message");
            return false;
        }
        return true;
    }

    public void onClick_send(View view) {
        if (isValidated()) {
            new asyncTask_post().execute();
        }
    }

    private class asyncTask_post extends AsyncTask<Void, Void, String> {
        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage("Please wait");
            mProgressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String response;
            try {
                response = sendRequest();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                return "3";
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            mProgressDialog.dismiss();
            String strMsg = "Sorry try again.";
            String status = "0";
            try {
                JSONObject mJSONObject = new JSONObject(response);
                status = mJSONObject.getString("cmd_status");
                if (status.equalsIgnoreCase("1")) {
                    JSONObject mJSONObjectData = new JSONObject(mJSONObject.getString("data"));
                    strMsg = mJSONObjectData.getString("response_message");
                    ThanksActivity.MESSAGE = strMsg;
                    startActivity(new Intent(context, ThanksActivity.class));
                    finish();
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
            }
            super.onPostExecute(response);
        }

    }


    public String sendRequest() {
        String strJson = "";
        try {
            JSONObject jObject = new JSONObject();
            jObject.put(C.JSON_KEY_DATA, getInputJsonData());  //url
            jObject.put(C.JSON_KEY_CMD, C.JSON_VALUE_CMD_POST_FORM_RESPONSE);
            Log.i("p_data", jObject.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(C.POST_KEY, jObject.toString()));

            strJson = httpPost(C.URL_SERVICE, params);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            strJson = "";
        }
        return strJson;
    }

    private JSONObject getInputJsonData() {
        JSONObject mJSONObject = new JSONObject();
        try {
            mJSONObject.put(C.JSON_KEY_APP_ID, C.APP_ID);
            mJSONObject.put(C.JSON_KEY_FORM_RESPONSE_TEXT, getInputJsonResponseText().toString());
            mJSONObject.put(C.JSON_KEY_FORM_DEVICE_ID, "exampledeviceid");
            mJSONObject.put(C.JSON_KEY_FORM_ID, C.JSON_VALUE_FORM_CONTACT_US_ID);
            mJSONObject.put(C.JSON_KEY_FORM_RESPONSE_DATE, "12/02/16");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJSONObject;
    }

    private JSONObject getInputJsonResponseText() {
        JSONObject mJSONObject = new JSONObject();
        try {
            mJSONObject.put(C.JSON_KEY_FORM_RESPONSE_TEXT_NAME, mTextViewName.getText());
            mJSONObject.put(C.JSON_KEY_FORM_RESPONSE_TEXT_EMAIL, mTextViewEmail.getText());
            mJSONObject.put(C.JSON_KEY_FORM_RESPONSE_TEXT_PHONE, mTextViewPhone.getText());
            mJSONObject.put(C.JSON_KEY_FORM_RESPONSE_TEXT_message, mTextViewMessage.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJSONObject;
    }

    protected String httpPost(String url, List<NameValuePair> nameValuePairs) {
        String strResponse = "";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            strResponse = EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            Log.e("Error", e.getMessage());
        } catch (IOException e) {
            Log.e("Error", e.getMessage());
        }
        return strResponse;
    }


}
