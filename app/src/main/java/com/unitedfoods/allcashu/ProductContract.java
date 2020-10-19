package com.unitedfoods.allcashu;

public class ProductContract {

   public static final String TABLE_NAME = "Product";

    public static class Columns {

        public static final String _ID = "Number";
        public static final String TASK_PRODUCTID = "ProductID";
        public static final String TASK_SEQ = "Sep";
        public static final String TASK_PRODUCTNAME = "ProductName";
        public static final String TASK_PRODUCTSHOTNAME = "ProductShotName";
        public static final String TASK_FLAVOUR = "Flavour";
        public static final String TASK_VATTYPE = "VatType";
        public static final String TASK_PRODUCTSUBGROUPID = "ProductSubGroupID";
        public static final String TASK_PRODUCTSUBGROUPNAME = "ProductSubGroupName";
        public static final String TASK_PRODUCTGROUPID = "ProductGroupID";
        public static final String TASK_PRODUCTGROUPNAME = "ProductGroupName";
        public static final String TASK_UOMSETID = "UomSetID";
        public static final String TASK_UOMSETNAME = "UomSetName";
        public static final String TASK_BASEQTY = "BaseOty";
        public static final String TASK_SELLPRICE = "SellPrice";
        public static final String TASK_SELLPRICEVAT = "SellPriceVat";
        public static final String TASK_PRICEGROUPID = "PriceGroupID";

        private Columns(){

        }
    }
}
