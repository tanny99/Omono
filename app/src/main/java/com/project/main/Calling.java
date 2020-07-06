//Refer to MainActivity and Main2Activity for commenting references

package com.project.main;

import org.jsoup.Jsoup; //JSoup Library to Parse HTML
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Calling {

    //ArrayLists to store Objects
    ArrayList<Product> products = new ArrayList<>(); //(title, price before, discounted price, discount, image link, product link,tag )
    String link;


    public void call(int sNo,String websiteUrl,String productClass, String tagForLink, String titleClass, String discountedPriceClass, String originalPriceClass, String productImageClass,String productDiscount) throws IOException {

        Document doc = Jsoup.connect(websiteUrl).get(); //JSoup Element to get HTML Data from the corresponding website
        Elements links = doc.getElementsByClass(productClass); //JSoup Element to get HTML Data from the corresponding Class/Section
        link = websiteUrl;

        // 1- Flipkart         //6 - Grofers
        // 2- Shopclues        //7- Bigbasket
        // 3- Snapdeal
        // 4- Paytm
        //5- Amazon



        //Loop To Initialize Variables for storing required HTML Data
        for (Element link : links) {
            String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null, temp6 = null,temp7 = null;
            Elements eltitle, elproductimage;

            Elements elLink = link.getElementsByTag(tagForLink); //JSoup Element to get URL of the product

            if(sNo == 2){                                        //When ShopClues is being parsed,
                eltitle = link.getElementsByTag(titleClass);     //We are getting Title of Product using HTML Tag because there is no proper class identifying the title
            }else{                                               //When any other website is being parsed,
                eltitle = link.getElementsByClass(titleClass);   //We are getting Title of Product using HTML Class because there is class identifying the title
            }

            Elements elpricebefore = link.getElementsByClass(originalPriceClass); //JSoup Element to get Original Price Data from corresponding HTML Class

            Elements elpriceafter = link.getElementsByClass(discountedPriceClass);//JSoup Element to get Discounted Price Date from corresponding HTML Class

            if(sNo == 1){
                elproductimage = link.getElementsByTag(productImageClass);
            }
            if(sNo == 2 ||sNo == 3|| sNo == 4|| sNo == 6){                                         //When ShopClues and Paytm mall is being parsed,
                elproductimage = link.getElementsByTag(productImageClass);    //We are getting URL of Product Image by HTML Tag because there is no proper class identifying the image url
            }
            else {                                                           //When any other website is being Parsed,
                elproductimage = link.getElementsByClass(productImageClass);  //We are getting URL of Product image by HTML Class because there is a proper class identifying the image url
            }

            Elements discount = link.getElementsByClass(productDiscount);//JSoup element to parse Discount data from corresponding classes

            switch(sNo){
                case 1:
                    temp7 = "Flipkart";
                    break;
                case 2:
                    temp7 = "Shopclues";
                    break;
                case 3:
                    temp7 = "Snapdeal";
                    break;
                case 4:
                    temp7 = "Paytm";
                    break;
                case 5:
                    temp7 = "Amazon";
                    break;
            }


            //product title loop
            for (Element titleOfProduct : eltitle) {
                if(titleOfProduct == null){
                    temp1 = "";
                }
                else{
                    temp1 = titleOfProduct.text();
                }
            }

            //product original price loop
            for (Element priceOfProductBefore : elpricebefore) {
                if(priceOfProductBefore == null){
                    temp2 = "";
                }
                else {
                    temp2 = "Price before: " + priceOfProductBefore.text();
                }
            }

            //product discounted price loop
            for (Element priceOfProductAfter : elpriceafter) {
                if(priceOfProductAfter == null){
                    temp3 = "";
                }
                else {
                    temp3 = "Discounted price: " + priceOfProductAfter.text();
                }
            }

            //discount in number loop
            for (Element productdiscount : discount) {
                if(productDiscount == null){
                    temp4 = "";
                }
                else {
                    temp4 = "Discount: " + productdiscount.text();
                }
            }

            //image loop
            if(sNo == 3){
                for(Element elimage : elproductimage){
                    temp6 = elimage.attr("srcset");
                    break;
                }
            }
            if(sNo == 2){
                for(Element elimage : elproductimage){
                    temp6 = elimage.attr("data-img");
                }
            }
            if(sNo == 1 || sNo == 4 || sNo == 7){
                for(Element elimage : elproductimage){
                    temp6 = elimage.attr("src");
                }
            }
            if(sNo == 6){
                for(Element elimage: elproductimage){
                    temp6 = elimage.absUrl("src");
                    break;
                }
            }

            //produuct URL loop
            ArrayList<String> linkArray = new ArrayList<>();
            for (Element elementLink : elLink) {
                String MainLink = elementLink.attr("href");
                linkArray.add(MainLink);
            }

            //URL storing
            if(sNo == 4) {
                for (int j = 0; j < 1; j++) {
                    temp5 = "https://paytmmall.com" + linkArray.get(0);
                }
            }
            if(sNo == 2){
                for (int j = 0; j < 1; j++) {
                    temp5 = "https:" + linkArray.get(0);
                }
            }
            if(sNo == 1){
                for (int j = 0; j < 1; j++) {
                    temp5 = "https://www.flipkart.com" + linkArray.get(0);
                }
            }
            if(sNo == 7){
                for (int j = 0; j < 1; j++) {
                    temp5 = "https://bigbasket.com" + linkArray.get(0);
                }
            }
            if(sNo == 6){
                for (int j = 0; j < 1; j++) {
                    temp5 = "https://grofers.com"+ linkArray.get(0);
                }
            }
            if(sNo == 3){
                temp5 = linkArray.get(0);
            }

            //(title, price before, discounted price, discount, image link, product link,tag )
            products.add(new Product(temp1,temp2,temp3,temp4,temp6,temp5,temp7));

        }
    }
}
