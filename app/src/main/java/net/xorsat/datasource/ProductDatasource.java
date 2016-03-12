package net.xorsat.datasource;

import net.xorsat.model.Product;

import java.util.ArrayList;

/**
 * Created by developer on 3/5/16.
 */
public class ProductDatasource {

    public ArrayList<Product> getList() {
        ArrayList<Product> arrayList = new ArrayList<Product>();

//        Product mProduct1 = new Product();
//        mProduct1.setProduct_id("1");
//        mProduct1.setProduct_name("Oops");
//        mProduct1.setProduct_description("object oriented programming");
//        mProduct1.setProduct_price("200 PKR");
//        mProduct1.setProduct_image("http://www.abc.com/image1.png");
//        arrayList.add(mProduct1);
//
//        Product mProduct2 = new Product();
//        mProduct2.setProduct_id("2");
//        mProduct2.setProduct_name("DBMS");
//        mProduct2.setProduct_description("Database syst");
//        mProduct2.setProduct_price("400 PKR");
//        mProduct2.setProduct_image("http://www.abc.com/image1.png");
//        arrayList.add(mProduct2);

        try {

            for (int i = 0; i <= 100; i++) {
                Product item = new Product();
                item.setProduct_id(i + "");
                item.setProduct_name("Product No:" + (i + 1));
                item.setProduct_price(i * 10 + " PKR");
                item.setProduct_description("Description of product " + i);
                item.setProduct_image("ic_stub.png");
                Thread.sleep(500);
                arrayList.add(item);
            }

            // get products data here
        } catch (Exception e) {


        }


        return arrayList;

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
