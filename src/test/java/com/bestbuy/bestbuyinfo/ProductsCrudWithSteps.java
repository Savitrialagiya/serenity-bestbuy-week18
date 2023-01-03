package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

//@RunWith(SerenityRunner.class)
public class ProductsCrudWithSteps extends TestBase {
    static String name = "Duracell - AA Batteries (8-Pack)" + TestUtils.getRandomValue();
    static String type = "Battery" + TestUtils.getRandomValue();
    static int price = 5;
    static int shipping = 10;
    static String upc = "041333424019";
    static String description = "Compatible with select electronic devices";
    static String manufacturer = "Duracell";
    static String model = TestUtils.getRandomValue();
    static String url = "https://www.bestbuy.com/site/Energizer";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

    static int productId;
    @Steps
    ProductsSteps productsSteps;

    @Title("This will create new product")
    @Test
    public void test001(){
        ValidatableResponse response = productsSteps.createProduct(name,type,price,shipping,upc,description,
                manufacturer,model,url,image);
        response.log().all().statusCode(201);
    }

    @Title("Verify if the product was added")
    @Test
    public void test002() {

        HashMap<String, Object> productMap = productsSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
        productId = (int) productMap.get("id");
       // System.out.println("Created Product ID: " + productId);
    }

//    @Title("Verify if the Product was added to the application")
//    @Test
//    public void test002() {
//        HashMap<String, ?> productMap = productsSteps.getProductInfoByName(name);
//        Assert.assertThat(productMap, hasValue(name));
//        System.out.println(productMap);
//    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        productsSteps.updateProduct(productId,name,type,price,shipping,upc,description,
                manufacturer,model,url,image)
                .log().all().statusCode(200);

        HashMap<String, Object> productMap = productsSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));

    }

    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test004() {
        productsSteps.deleteProduct(productId).statusCode(200);
        productsSteps.getProductById(productId).statusCode(404);

    }

}
