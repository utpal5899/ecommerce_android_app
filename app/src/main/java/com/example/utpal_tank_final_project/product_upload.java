package com.example.utpal_tank_final_project;

public class product_upload {

    private String product_name,Product_description,Product_price;



    private int quantity;
    private Double vaal;

    private  String product_image;




    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public Double getVaal() {
        return vaal;
    }

    public void setVaal(Double vaal) {
        this.vaal = vaal;
    }


    public product_upload() {
    }

    public product_upload(String product_name, String product_description, String product_price, String product_image, Double vaal, Integer quantity )  {
        this.product_name = product_name;
        Product_description = product_description;
        Product_price = product_price;
        this.product_image = product_image;
        this.vaal=vaal;
        this.quantity=quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return Product_description;
    }

    public void setProduct_description(String product_description) {
        Product_description = product_description;
    }

    public String getProduct_price() {
        return Product_price;
    }

    public void setProduct_price(String product_price) {
        Product_price = product_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}
