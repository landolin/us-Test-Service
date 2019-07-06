package com.nucleoti.alpes.ti.controller;

import com.nucleoti.alpes.ti.model.ProductBean;

import java.util.List;

public class ProductList {
    private List<ProductBean> lstProduct;

    public List<ProductBean> getLstProduct() {
        return lstProduct;
    }

    public void setLstProduct(List<ProductBean> lstProduct) {
        this.lstProduct = lstProduct;
    }
}
