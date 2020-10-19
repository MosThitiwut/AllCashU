package com.unitedfoods.allcashu.Sale.ShopList;

public class ShopModel {

    public String CustomerID;
    public String CustomerName;
    public String BillTo;
    public String Tel;
    public String TotalDue;
    public String VisitID;
    public String VisitStatus;
    public String CauseID;
    public String DocNo;
    public String DocStatus;

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getBillTo() {
        return BillTo;
    }

    public void setBillTo(String billTo) {
        BillTo = billTo;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getTotalDue() {
        return TotalDue;
    }

    public void setTotalDue(String totalDue) {
        TotalDue = totalDue;
    }

    public String getVisitID() {
        return VisitID;
    }

    public void setVisitID(String visitID) {
        VisitID = visitID;
    }

    public String getCauseID() {
        return CauseID;
    }

    public void setCauseID(String causeID) {
        CauseID = causeID;
    }

    public String getDocNo() {
        return DocNo;
    }

    public void setDocNo(String docNo) {
        DocNo = docNo;
    }

    public String getDocStatus() {
        return DocStatus;
    }

    public void setDocStatus(String docStatus) {
        DocStatus = docStatus;
    }

    public String getVisitStatus() {
        return VisitStatus;
    }

    public void setVisitStatus(String visitStatus) {
        VisitStatus = visitStatus;
    }
}
