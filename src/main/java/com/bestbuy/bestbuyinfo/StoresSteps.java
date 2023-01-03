package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StoresPojo;
import cucumber.api.java.hu.Ha;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.yecht.Data;

import java.util.HashMap;

public class StoresSteps {

    @Step("Creating store with name : {0},type : {1}, address : {2}, address2 : {3}, city : {4}, state : {5}, zip : {6}, lat : {7},lng : {8},hours : {9}")
    public ValidatableResponse createNewStore(String name,String type, String address, String address2, String city,String state,
                                              String zip,int lat,int lng,String hours){
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(storesPojo)
                .post(EndPoints.GET_ALL_STORES)
                .then();
    }

    @Step("Getting the store information with name: {0}")
    public HashMap<String, Object> getStoreInfoByName(String name){
        String p1 = "findAll{it.firstName == '";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);

    }
    @Step("Updating store information with storeId: {0}, name : {1},type : {2}, address : {3}, address2 : {4}, city : {5}, state : {6}, zip : {7}, lat : {8},lng : {9},hours : {10}")
    public ValidatableResponse updateStore(int storeId,String name,String type, String address, String address2, String city,String state,
                                             String zip,int lat,int lng,String hours){
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        return SerenityRest.given().log().all()
                //.header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .pathParam("storeID", storeId)
                .body(storesPojo)
                .when()
                .put(EndPoints.UPDATE_STORES_BY_ID)
                .then();
    }
    @Step("Deleting store information with storeId: {0}")
    public ValidatableResponse deleteStore(int storeId){
        return SerenityRest
                .given()
                .pathParam("storeId", storeId)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then();
    }
    @Step("Fatching store information with storeId: {0}")
    public ValidatableResponse getStoreById(int storeId){
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .pathParam("storeId", storeId)
                .when()
                .get(EndPoints.GET_STORES_BY_ID)
                .then();
    }

}
