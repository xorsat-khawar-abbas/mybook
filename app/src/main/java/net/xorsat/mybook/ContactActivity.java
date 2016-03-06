package net.xorsat.mybook;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class ContactActivity extends Activity {
    EditText mEditTextName;
    EditText mEditTextPhone;
    EditText mEditTextMessage;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        init();
        populate();
    }

    private void populate() {
//        String strName = mSharedPreferences.getString("key_name", "N/A");
//        mEditTextName.setText(strName);
        String strName = mSharedPreferences.getString("key_name", "N/A");
        mEditTextName.setText(strName);
        mEditTextPhone.setText(mSharedPreferences.getString("key_phone", ""));
    }

    private void init() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditTextName = (EditText) findViewById(R.id.contact_editText_name);
        mEditTextPhone = (EditText) findViewById(R.id.contact_editText_phone);
        mEditTextMessage = (EditText) findViewById(R.id.contact_editText_message);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("key_name", mEditTextName.getText().toString());
        editor.putString("key_phone", mEditTextPhone.getText().toString());
        //editor.putString("key_message", mEditTextMessage.getText().toString());
        editor.commit();
    }

    private boolean isValidate() {
        if (mEditTextName.length() == 0) {
            showMessage("Please give name field");
            return false;
        } else if (mEditTextPhone.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please give your phone number");
            return false;
        } else if (mEditTextMessage.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please give your message");
            return false;
        }
        return true;
    }

    public void onClick_send(View view) {
        if (isValidate()) {
            savePreferences();
            startActivity(new Intent(this, ThanksActivity.class));
            finish();
        }

    }

}
