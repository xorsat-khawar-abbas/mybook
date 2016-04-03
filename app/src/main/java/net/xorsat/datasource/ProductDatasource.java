package net.xorsat.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import net.xorsat.contract.ProductContract;
import net.xorsat.contract.ProductContract.ProductEntry;
import net.xorsat.model.Product;
import net.xorsat.parser.ProductParser;

import java.util.ArrayList;

/**
 * Created by developer on 3/5/16.
 */
public class ProductDatasource {
    XorsatDbHelper mXorsatDbHelper;
    SQLiteDatabase mSQLiteDatabase;

    public ProductDatasource(Context context) {
        mXorsatDbHelper = new XorsatDbHelper(context);
        mSQLiteDatabase = mXorsatDbHelper.getWritableDatabase();
    }

    public ArrayList<Product> getList() {
        try {
            ProductParser mProductParser = new ProductParser();
            ArrayList<Product> arrayList = mProductParser.getListFromServer();
            if (arrayList != null && arrayList.size() > 0) {
                deleteAll();
                bulkInsert(arrayList);
            }

        } catch (Exception e) {
            //getListFromSQLite();
        }
        return getListFromSQLite();
    }

    public ArrayList<Product> getListFromSQLite() {
        ArrayList<Product> arrayList = new ArrayList<>();
        try {
            Cursor mCursor = mSQLiteDatabase.rawQuery(ProductContract.SQL_SELECT_ALL, null);
            while (mCursor.moveToNext()) {
                Product mProduct = new Product();
                mProduct.setProduct_id(mCursor.getString(mCursor.getColumnIndex(ProductEntry.COLUMN_NAME_PRODUCT_ID)));
                mProduct.setProduct_name(mCursor.getString(mCursor.getColumnIndex(ProductEntry.COLUMN_NAME_PRODUCT_NAME)));
                mProduct.setProduct_description(mCursor.getString(mCursor.getColumnIndex(ProductEntry.COLUMN_NAME_PRODUCT_DESCRIPTION)));
                mProduct.setProduct_price(mCursor.getString(mCursor.getColumnIndex(ProductEntry.COLUMN_NAME_PRODUCT_PRICE)));
                mProduct.setProduct_image(mCursor.getString(mCursor.getColumnIndex(ProductEntry.COLUMN_NAME_PRODUCT_IMAGE)));
                arrayList.add(mProduct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public long insert(Product product) {
        long result = 0;
        try {
            ContentValues mContentValues = new ContentValues();
            mContentValues.put(ProductEntry.COLUMN_NAME_PRODUCT_ID, product.getProduct_id());
            mContentValues.put(ProductEntry.COLUMN_NAME_PRODUCT_NAME, product.getProduct_name());
            mContentValues.put(ProductEntry.COLUMN_NAME_PRODUCT_DESCRIPTION, product.getProduct_description());
            mContentValues.put(ProductEntry.COLUMN_NAME_PRODUCT_PRICE, product.getProduct_price());
            mContentValues.put(ProductEntry.COLUMN_NAME_PRODUCT_IMAGE, product.getProduct_image());
            result = mSQLiteDatabase.insert(ProductEntry.TABLE_NAME, null, mContentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public void bulkInsert(ArrayList<Product> arrayList) {
        for (Product item : arrayList) {
            insert(item);
        }
    }

    public long update(Product product) {
        long result = 0;
        try {
            ContentValues mContentValues = new ContentValues();
            mContentValues.put(ProductEntry.COLUMN_NAME_PRODUCT_ID, product.getProduct_id());
            mContentValues.put(ProductEntry.COLUMN_NAME_PRODUCT_NAME, product.getProduct_name());
            mContentValues.put(ProductEntry.COLUMN_NAME_PRODUCT_DESCRIPTION, product.getProduct_description());
            mContentValues.put(ProductEntry.COLUMN_NAME_PRODUCT_PRICE, product.getProduct_price());
            mContentValues.put(ProductEntry.COLUMN_NAME_PRODUCT_IMAGE, product.getProduct_image());
            result = mSQLiteDatabase.update(ProductEntry.TABLE_NAME, mContentValues, ProductEntry.COLUMN_NAME_PRODUCT_ID + "  =  " + product.getProduct_id(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public long delete(Product product) {
        mSQLiteDatabase.execSQL(ProductContract.SQL_DELETE_ALL + " WHERE " + ProductEntry.COLUMN_NAME_PRODUCT_ID + " = " + product.getProduct_id());
        return 0;

    }

    public long deleteAll() {
        try {
            mSQLiteDatabase.execSQL(ProductContract.SQL_DELETE_ALL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }
}
