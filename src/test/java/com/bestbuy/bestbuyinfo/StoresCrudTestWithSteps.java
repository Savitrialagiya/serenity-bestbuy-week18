package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBaseStores;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

//@RunWith(SerenityRunner.class)
public class StoresCrudTestWithSteps extends TestBaseStores {
    static String name = "Next" + TestUtils.getRandomValue();
    static String type = "BigBox"+ TestUtils.getRandomValue();
    static String address = "1231 Ridgedale Drive";
    static String address2 = "Newway";
    static String city = "norway";
    static String state = "NM";
    static String zip = "554235";
    static int lat = 10;
    static int lng = 10;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8" ;

    static int storeId;

    @Steps
    StoresSteps storesSteps;

    @Title("This will create new store")
    @Test
    public void test001() {
        storesSteps.createNewStore(name,type,address,address2,city,state,zip,lat,lng,hours)
                .log().all().statusCode(201);

    }
    @Title("Verify if the new store was added")
    @Test
    public void test002(){
        HashMap<String ,Object> storeMap = storesSteps.getStoreInfoByName(name);
        Assert.assertThat(storeMap, hasValue(name));
        storeId = (int) storeMap.get("id");
    }

    @Title("Update the store information and verify the updated information")
    @Test
    public void test003() {
        name = name + "(updated)";
        storesSteps.updateStore(storeId,name,type,address,address2,city,state,zip,lat,lng,hours)
                .log().all().statusCode(200);

        HashMap<String ,Object> storeMap = storesSteps.getStoreInfoByName(name);
        Assert.assertThat(storeMap, hasValue(name));

    }

    @Title("Delete the store and verify if the store is deleted!")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeId).statusCode(200);
        storesSteps.getStoreById(storeId).statusCode(404);

    }

}
