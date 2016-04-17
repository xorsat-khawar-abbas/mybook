package net.xorsat.mybook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void onClick_about(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void onClick_contact(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    public void onClick_products(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
        startActivity(intent);
    }

    public void onClick_gcm(View view) {
        Intent intent = new Intent(this, GCMActivity.class);
        startActivity(intent);
    }

    public void onClick_map(View view) {
        Intent intent = new Intent(this, BranchActivity.class);
        startActivity(intent);
    }
}
