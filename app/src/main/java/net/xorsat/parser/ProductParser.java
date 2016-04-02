package net.xorsat.parser;

import net.xorsat.model.Product;
import net.xorsat.mybook.C;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by xorsat on 3/27/16.
 */
public class ProductParser {


    public ArrayList<Product> getListFromServer() {
        ArrayList<Product> arrayList = new ArrayList<>();
        String strResponse = httpGet(C.URL_JSON);

        try {

            JSONObject mJSONObjectMain = new JSONObject(strResponse) ;

            String strStatus = mJSONObjectMain.getString("status");
            JSONArray mJSONArray = mJSONObjectMain.getJSONArray("data");

            //JSONArray mJSONArray = new JSONArray(strResponse);

            for (int i = 0; i < mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                Product mProduct = new Product();
                mProduct.setProduct_id(mJSONObject.getString("product_id"));
                mProduct.setProduct_name(mJSONObject.getString("product_name"));
                mProduct.setProduct_description(mJSONObject.getString("product_description"));
                mProduct.setProduct_price(mJSONObject.getString("product_price"));
                mProduct.setProduct_image("product_image");
                arrayList.add(mProduct);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }


    private String httpGet(String url) {
        String result = "";
        HttpClient mHttpClient = new DefaultHttpClient();
        HttpGet mHttpGet = new HttpGet(url);
        try {
            HttpResponse mHttpResponse = mHttpClient.execute
                    (mHttpGet);
            result = EntityUtils.toString(mHttpResponse.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
