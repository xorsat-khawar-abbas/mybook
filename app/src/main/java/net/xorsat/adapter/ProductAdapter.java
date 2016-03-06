package net.xorsat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.xorsat.model.Product;
import net.xorsat.mybook.R;

import java.util.ArrayList;

/**
 * Created by developer on 3/6/16.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    Context context;
    ArrayList<Product> arrayList;

    public ProductAdapter(Context _context, ArrayList<Product> objects) {
        super(_context, R.layout.row_product_list, objects);
        this.context = _context;
        this.arrayList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product mProduct = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_product_list, parent, false);

        TextView mTextViewName = (TextView) rowView.findViewById(R.id.row_product_name);
        TextView mTextViewPrice = (TextView) rowView.findViewById(R.id.row_product_price);

        mTextViewName.setText(mProduct.getProduct_name().toString());
        mTextViewPrice.setText(mProduct.getProduct_price().toString());

        return rowView;

    }
}
