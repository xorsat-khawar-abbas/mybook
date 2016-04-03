package net.xorsat.mybook;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.xorsat.adapter.ProductAdapter;
import net.xorsat.datasource.ProductDatasource;
import net.xorsat.model.Product;

import java.util.ArrayList;

public class ProductListActivity extends Activity {

    ListView mListView;
    Context context;
    ArrayList<Product> arrayList;
    ProductDatasource mProductDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_product_list);
        new get_data_AsynchTask().execute();
    }

    private void populate() {
        mListView = (ListView) findViewById(R.id.productlist_listview);
        // Initialized ArrayAdapter
        ProductAdapter mProductAdapter = new ProductAdapter(context, arrayList);

        mListView.setAdapter(mProductAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product _mProduct = arrayList.get(position);
                ProductDetailActivity.mProduct = _mProduct;
                startActivity(new Intent(context, ProductDetailActivity.class));
                //Toast.makeText(context, mProduct.getProduct_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class get_data_AsynchTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage("Please wait");
            mProgressDialog.setTitle("Loading..");
            mProgressDialog.show();

            arrayList = new ArrayList<>();
            mProductDatasource = new ProductDatasource(context);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            arrayList = mProductDatasource.getList();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            populate();

            super.onPostExecute(aVoid);
        }
    }
}
