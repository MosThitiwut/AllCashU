package com.unitedfoods.allcashu;

import android.provider.BaseColumns;

public class ArCustomerShelfContract {

    public static final String TABLE_NAME = "ArCustomerShelf";

    public static class Columns{

        public static final String _ID = BaseColumns._ID;
        public static final String TASK_CUSTOMERID = "CustomerID";
        public static final String TASK_SHELFID = "ShelfID";
        public static final String TASK_FLAGNEW = "FlagNew";
        public static final String TASK_FLAGEDIT = "FlagEdit";
        public static final String TASK_FLAGDEL = "FlagDel";


        private Columns(){}


    }
}
