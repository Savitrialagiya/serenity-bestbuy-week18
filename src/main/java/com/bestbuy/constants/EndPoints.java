package com.bestbuy.constants;

import org.yecht.Data;

public class EndPoints {
    //This is Endpoints of PRODUCTS

    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String UPDATE_PRODUCT_BY_ID = "/{id}";
    public static final String DELETE_PRODUCT_BY_ID = "/{id}";
    public static final String GET_PRODUCT_BY_ID = "/{id}";
//    public static final String GET_ALL_PRODUCTS = "/products";
//    public static final String GET_PRODUCT_BY_ID = "/products/{productID}";
//    public static final String UPDATE_PRODUCT_BY_ID = "/products/{productID}";
//    public static final String DELETE_PRODUCT_BY_ID = "/products/{productID}";

    //This is Endpoints of STORES
    public static final String GET_ALL_STORES = "/stores";
    public static final String GET_STORES_BY_ID = "/{storeID}";
    public static final String UPDATE_STORES_BY_ID = "/{storeID}";
    public static final String DELETE_STORES_BY_ID = "/{storeID}";
}
