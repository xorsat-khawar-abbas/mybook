package net.xorsat.mybook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import net.xorsat.adapter.ProductAdapter;
import net.xorsat.datasource.ProductDatasource;
import net.xorsat.model.Product;

import java.util.ArrayList;

public class ProductListActivity extends Activity {

    ListView mListView;
    Context context;
    ArrayList<Product> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_product_list);

        arrayList = new ArrayList<>();
        final ProductDatasource mProductDatasource = new ProductDatasource();
        arrayList = mProductDatasource.getList();
        mListView = (ListView) findViewById(R.id.productlist_listview);
        // Initialized ArrayAdapter
        ProductAdapter mProductAdapter = new ProductAdapter(context, arrayList);

        mListView.setAdapter(mProductAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product mProduct = arrayList.get(position);
                mProductDatasource.insert(mProduct);
                Toast.makeText(context, mProduct.getProduct_name(), Toast.LENGTH_SHORT).show();
            }
        });

        // Set ArrayAdapter to above ListView

    }
}
