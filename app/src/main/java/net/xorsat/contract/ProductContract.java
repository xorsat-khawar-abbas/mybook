package net.xorsat.contract;

import android.provider.BaseColumns;

/**
 * Created by xorsat on 4/2/16.
 */
public class ProductContract {
    private ProductContract() {
    }


    public static abstract class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbl_product";
        public static final String COLUMN_NAME_PRODUCT_ID = "product_id";
        public static final String COLUMN_NAME_PRODUCT_NAME = "product_name";
        public static final String COLUMN_NAME_PRODUCT_PRICE = "product_price";
        public static final String COLUMN_NAME_PRODUCT_DESCRIPTION = "product_description";
        public static final String COLUMN_NAME_PRODUCT_IMAGE = "product_image";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE = "CREATE TABLE " + ProductEntry.TABLE_NAME + " (" + ProductEntry._ID
            + " INTEGER PRIMARY KEY," + ProductEntry.COLUMN_NAME_PRODUCT_ID
            + TEXT_TYPE + COMMA_SEP + ProductEntry.COLUMN_NAME_PRODUCT_NAME
            + TEXT_TYPE + COMMA_SEP + ProductEntry.COLUMN_NAME_PRODUCT_PRICE
            + TEXT_TYPE + COMMA_SEP + ProductEntry.COLUMN_NAME_PRODUCT_DESCRIPTION
            + TEXT_TYPE + COMMA_SEP + ProductEntry.COLUMN_NAME_PRODUCT_IMAGE
            + " )";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME;
    public static final String SQL_SELECT_ALL = "SELECT * FROM " + ProductEntry.TABLE_NAME;
    public static final String SQL_DELETE_ALL = "DELETE FROM " + ProductEntry.TABLE_NAME;
}
