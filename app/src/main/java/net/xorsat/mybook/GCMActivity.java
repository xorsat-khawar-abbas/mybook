package net.xorsat.mybook;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GCMActivity extends Activity {
    Context context;
    GoogleCloudMessaging gcm;
    String gcmId = "";
    WebView mWebViewResponse;
    String sender_id = "620209984911"; // project number

    //String api_key = "AIzaSyCMoZrRZ-dRWAF8IP9-xAiBvU8uneKOkWs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcm);
        mWebViewResponse = (WebView) findViewById(R.id.gcm_webView);
        context = this;
    }

    public void onClick_register_gcm(View view) {
        if (gcmId.length() == 0) {
            new asyncTask_RegisterGCM().execute();
        }
    }

    public void onClick_register_web(View view) {
        if (gcmId.length() > 0) {
            new asyncTask_RegisterWeb().execute();
        } else {
            print("Warning", "Please register device first");
        }
    }

    private class asyncTask_RegisterGCM extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                gcm = GoogleCloudMessaging.getInstance(context);
                gcmId = gcm.register(sender_id);
            } catch (IOException ex) {
                return "Error:" + ex.getMessage();
            }
            return gcmId;
        }

        @Override
        protected void onPostExecute(String msg) {
            print("Register GCM : ", msg);
        }
    }

    private void print(String tag, String message) {
        message = "<b>" + tag + "</b><br>" + message;
        mWebViewResponse.loadData(message, "text/html", "utf-8");
    }

    private class asyncTask_RegisterWeb extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String msg = "";
            try {
                if (gcmId.length() > -0) {
                    msg = registerDeviceToWebServer(gcmId);
                }
            } catch (Exception ex) {
                msg = "Error :" + ex.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String result) {
            print("web response : ", result);
        }
    }

    private String registerDeviceToWebServer(String gcmId) {
        String url = "http://demo.xorsat.org/xorfood/api/register.php";
        String strResponse = "No response";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("device_gcm_id", gcmId));
            nameValuePairs.add(new BasicNameValuePair("device_api_key", ""));
            nameValuePairs.add(new BasicNameValuePair("device_type", "1"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            strResponse = EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            //Log.e("ClientProtocolException:", e.getMessage());
            strResponse = e.getMessage();
        } catch (IOException e) {
            Log.e("IOException:", e.getMessage());
            strResponse = e.getMessage();
        }
        return strResponse;
    }
}
