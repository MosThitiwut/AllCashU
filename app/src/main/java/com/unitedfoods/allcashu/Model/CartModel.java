package com.unitedfoods.allcashu.Model;

public class CartModel {

    public String DocNO;
    public String CustomerID;
    public String OrderUom;
    public String ProductID;
    public Integer OrderQty;

    public String getDocNO() {
        return DocNO;
    }

    public void setDocNO(String docNO) {
        DocNO = docNO;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getOrderUom() {
        return OrderUom;
    }

    public void setOrderUom(String orderUom) {
        OrderUom = orderUom;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public Integer getOrderQty() {
        return OrderQty;
    }

    public void setOrderQty(Integer orderQty) {
        OrderQty = orderQty;
    }
}
