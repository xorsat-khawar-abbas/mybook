package net.xorsat.mybook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.xorsat.model.Product;

public class OrderActivity extends Activity {
    EditText mEditTextName;
    EditText mEditTextPhone;
    EditText mEditTextAddress;
    TextView tvProductName;
    TextView tvProductPrice;
    EditText mEditTextQuantity;
    Context context;
    SharedPreferences mSharedPreferences;
    public static Product item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
        populate();
    }

    private void init() {
        context = this;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditTextName = (EditText) findViewById(R.id.order_editText_name);
        mEditTextPhone = (EditText) findViewById(R.id.order_editText_phone);
        mEditTextAddress = (EditText) findViewById(R.id.order_editText_address);
        mEditTextQuantity = (EditText) findViewById(R.id.order_editText_productQuantity);
        tvProductName = (TextView) findViewById(R.id.row_product_name);
        tvProductPrice = (TextView) findViewById(R.id.row_product_price);
    }

    private void populate() {
        String strName = mSharedPreferences.getString("key_name", "");
        mEditTextName.setText(strName);
        String strPhone = mSharedPreferences.getString("key_phone", "");
        mEditTextPhone.setText(strPhone);
        String strAddress = mSharedPreferences.getString("key_address", "");
        mEditTextAddress.setText(strAddress);
        tvProductName.setText(item.getProduct_name());
        tvProductPrice.setText(item.getProduct_price());
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void savePreferences() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString("key_name", mEditTextName.getText().toString());
        editor.putString("key_phone", mEditTextPhone.getText().toString());
        editor.putString("key_address", mEditTextAddress.getText().toString());
        editor.commit();
    }

    private boolean isValidate() {
        if (mEditTextName.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please Give Your Name");
            return false;
        } else if (mEditTextPhone.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please Give Your Phone Number");
            return false;
        } else if (mEditTextAddress.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please Give Your Address");
            return false;
        }
        return true;
    }

    public void onClick_order(View view) {
        if (isValidate()) {
            savePreferences();
            Intent mIntent = new Intent(this, ThanksActivity.class);
            startActivity(mIntent);
        }
    }


}