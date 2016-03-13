package net.xorsat.mybook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.xorsat.model.Product;

public class ProductDetailActivity extends Activity {

    public static Product mProduct;

    TextView tvProductTitle;
    TextView tvProductPrice;
    TextView tvProductDecs;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();
        populate();
    }

    private void init() {
        context = this;
        tvProductTitle = (TextView) findViewById(R.id.actionBar_textView_title);
        tvProductPrice = (TextView) findViewById(R.id.productDetail_textView_productPrice);
        tvProductDecs = (TextView) findViewById(R.id.productDetail_textView_productDescription);
    }

    private void populate() {
        tvProductTitle.setText(mProduct.getProduct_name());
        tvProductPrice.setText(mProduct.getProduct_price());
        tvProductDecs.setText(mProduct.getProduct_description());
    }

    public void onClick_order(View v) {
        OrderActivity.item = mProduct;
        startActivity(new Intent(context, OrderActivity.class));
    }

    public void onClick_share(View v) {
        String strShareText = String.format(getResources().getString(R.string.message_product_share),
                mProduct.getProduct_name(), mProduct.getProduct_price());

        Intent mIntent = new Intent();
        mIntent.setAction(Intent.ACTION_SEND);
        mIntent.putExtra(Intent.EXTRA_TEXT, strShareText);
        mIntent.setType("text/plain");
        //startActivity(mIntent);
        startActivity(Intent.createChooser(mIntent,"Share " + mProduct.getProduct_name()));
    }
}