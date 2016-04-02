package net.xorsat.datasource;

import net.xorsat.model.Product;
import net.xorsat.parser.ProductParser;

import java.util.ArrayList;

/**
 * Created by developer on 3/5/16.
 */
public class ProductDatasource {

    public ArrayList<Product> getList() {
        ProductParser mProductParser = new ProductParser();
        return mProductParser.getListFromServer();
    }

    public long insert(Product product) {
        return 0;

    }

    public long update(Product product) {
        return 0;

    }

    public long delete(Product product) {
        return 0;

    }
}
