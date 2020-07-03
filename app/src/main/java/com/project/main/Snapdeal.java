//Refer to MainActivity, Main2Activity and Calling for commenting references

package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Snapdeal extends AsyncTask<String, Void, ArrayList<String>> implements arraySave{

    //ArrayList for corresponding objects
    ArrayList<String> temptitlestore = new ArrayList<>();
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> tempimageurl = new ArrayList<>();
    ArrayList<String> tempdiscount = new ArrayList<>();
    ArrayList<String> temppricebefore = new ArrayList<>();
    ArrayList<String> tag = new ArrayList<>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<String> s) {
        super.onPostExecute(s);
        arraySave.discountedprice.addAll(s);
        arraySave.discount.addAll(tempdiscount);
        arraySave.pricebefore.addAll(temppricebefore);
        arraySave.titleallproducts.addAll(temptitlestore);
        arraySave.producturl.addAll(tempurlstore);
        arraySave.imageurls.addAll(tempimageurl);
        arraySave.tag.addAll(tag);

    }



    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        try{
            System.out.println("Running snapdeal on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(3,strings[0],"col-xs-6  favDp product-tuple-listing js-tuple ","a","product-title",
                    "lfloat product-price","lfloat product-desc-price strike ","source","product-discount");
            //initialising and referencing Calling method variables
            temptitlestore = calling.temptitlestore;
            tempimageurl = calling.tempimageurl;
            tempurlstore = calling.tempurlstore;
            tag = calling.tag;
            ArrayList<String> mainlist= calling.temppriceafter;
            System.out.println("Snapdeal ended");
            return mainlist;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Snapdeal not working" + e);
            return null;
        }
    }
}