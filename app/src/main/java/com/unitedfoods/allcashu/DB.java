package com.unitedfoods.allcashu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.unitedfoods.allcashu.Model.ArCustomerShelfModel;
import com.unitedfoods.allcashu.Model.BranchWarehouseModel;
import com.unitedfoods.allcashu.Model.CauseModel;
import com.unitedfoods.allcashu.Model.CustomerModel;
import com.unitedfoods.allcashu.Model.PDAModel;
import com.unitedfoods.allcashu.Model.PODetailModel;
import com.unitedfoods.allcashu.Model.POMasterModel;
import com.unitedfoods.allcashu.Model.ProductModel;
import com.unitedfoods.allcashu.Model.SaleAreaModel;
import com.unitedfoods.allcashu.Model.SettingModel;
import com.unitedfoods.allcashu.Model.ShopTypeModel;
import com.unitedfoods.allcashu.Model.StockModel;
import com.unitedfoods.allcashu.Model.VisitModel;
import com.unitedfoods.allcashu.Model.VisitStockModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

public class DB extends SQLiteOpenHelper {
    private static final String TAG = "DB";
    public static final String DATABASE_NAME = "AllCashU";
    public static final int DATABASE_VERSION = 1;

    private static DB instance = null;
    private DB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d(TAG, "DB: constructor");
    }
    public static DB getInstance(Context context)
    {
        if (instance == null)
        {
            Log.d(TAG, "getInstance: creating new instance");
            instance = new DB(context);

        }

        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: starts");
        //Table Setting
        String tbl_Setting = "CREATE TABLE "+ SettingContract.TABLE_NAME+" ("
                +SettingContract.Columns.TASK_CFGNAME+" TEXT ,"
                +SettingContract.Columns.TASK_CFGVALUE+" TEXT )";
        Log.d(TAG, "onCreate: "+tbl_Setting);
        db.execSQL(tbl_Setting);

//        //Table ShopType
        String tbl_ShopType = "CREATE TABLE "+ ShopTypeContract.TABLE_NAME+"("
                +ShopTypeContract.Columns.TASK_SHOPTYPECODE + " INTEGER,"
                +ShopTypeContract.Columns.TASK_SHOPETYPENAME+" TEXT,"
                +ShopTypeContract.Columns.TASK_SHOPTYPEGROUPID+" INTEGER);";
        Log.d(TAG, "onCreate: "+tbl_ShopType);
        db.execSQL(tbl_ShopType);
//
        //Table Product
        String tbl_Product = "CREATE TABLE "+ProductContract.TABLE_NAME+"("
                +ProductContract.Columns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ProductContract.Columns.TASK_PRODUCTID+" TEXT,"
                +ProductContract.Columns.TASK_SEQ+" INTEGER,"
                +ProductContract.Columns.TASK_PRODUCTNAME+" NVARCHAR(1000),"
                +ProductContract.Columns.TASK_PRODUCTSHOTNAME+" NVARCHAR(1000),"
                +ProductContract.Columns.TASK_FLAVOUR+" TEXT,"
                +ProductContract.Columns.TASK_VATTYPE+" INTEGER,"
                +ProductContract.Columns.TASK_PRODUCTSUBGROUPID+" INTEGER,"
                +ProductContract.Columns.TASK_PRODUCTSUBGROUPNAME+" TEXT,"
                +ProductContract.Columns.TASK_PRODUCTGROUPID+" INTEGER,"
                +ProductContract.Columns.TASK_PRODUCTGROUPNAME+" TEXT,"
                +ProductContract.Columns.TASK_UOMSETID+" INTEGER,"
                +ProductContract.Columns.TASK_UOMSETNAME+" TEXT,"
                +ProductContract.Columns.TASK_BASEQTY+" INTEGER,"
                +ProductContract.Columns.TASK_SELLPRICE+" DECIMAL(15,2),"
                +ProductContract.Columns.TASK_SELLPRICEVAT+" DECIMAL(15,2),"
                +ProductContract.Columns.TASK_PRICEGROUPID+ " INTEGER);";
        Log.d(TAG, "onCreate: "+tbl_Product);
        db.execSQL(tbl_Product);

        //Table Cause
        String tbl_Cause = "CREATE TABLE "+CauseContract.TABLE_NAME+"("
                +CauseContract.Columns.TASK_CAUSEID+" TEXT,"
                +CauseContract.Columns.TASK_CAUSECODE+" TEXT,"
                +CauseContract.Columns.TASK_CAUSEGROUP+" TEXT,"
                +CauseContract.Columns.TASK_CAUSENAME+" TEXT);";
        Log.d(TAG, "onCreate: "+tbl_Cause);
        db.execSQL(tbl_Cause);

        //Table BranchWarehouse
        String tbl_BranchWarehouse = "CREATE TABLE "+BranchWarehouseContract.TABLE_NAME+"("
                +BranchWarehouseContract.Columns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +BranchWarehouseContract.Columns.TASK_WHID+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_BRANCHID+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_WHTYPE+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_WHNAME+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_WHSEQ+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_EMPID+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_TITLENAME+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_FIRSTNAME+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_LASTNAME+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_USERNAME+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_PASSWORD+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_POSNo+" TEXT,"
                +BranchWarehouseContract.Columns.TASK_EMPIDCARD+" TEXT);";
        Log.d(TAG, "onCreate: "+tbl_BranchWarehouse);
        db.execSQL(tbl_BranchWarehouse);


        //Table Customer
        String tbl_Customer = "CREATE TABLE "+CustomerContract.TABLE_NAME+"("
                +CustomerContract.Columns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CustomerContract.Columns.TASK_CUSTOMERID+" TEXT,"
                +CustomerContract.Columns.TASK_BRANCHID+" TEXT,"
                +CustomerContract.Columns.TASK_WHID+" TEXT,"
                +CustomerContract.Columns.TASK_EMPID+" TEXT,"
                +CustomerContract.Columns.TASK_SALAREAID+" TEXT,"
                +CustomerContract.Columns.TASK_SHOPTYPEID+" TEXT,"
                +CustomerContract.Columns.TASK_CustSeq+" TEXT,"
                +CustomerContract.Columns.TASK_CustTitle+" TEXT,"
                +CustomerContract.Columns.TASK_CustName+" TEXT,"
                +CustomerContract.Columns.TASK_CustShortName+" TEXT,"
                +CustomerContract.Columns.TASK_CustImage+" TEXT,"
                +CustomerContract.Columns.TASK_BillTo+" TEXT,"
                +CustomerContract.Columns.TASK_Tel+" TEXT,"
                +CustomerContract.Columns.TASK_Contact+" TEXT,"
                +CustomerContract.Columns.TASK_AddressNo+" TEXT,"
                +CustomerContract.Columns.TASK_Moo+" TEXT,"
                +CustomerContract.Columns.TASK_Lane+" TEXT,"
                +CustomerContract.Columns.TASK_Street+" TEXT,"
                +CustomerContract.Columns.TASK_AreaID+" TEXT,"
                +CustomerContract.Columns.TASK_DistrictID+" TEXT,"
                +CustomerContract.Columns.TASK_ProvinceID+" TEXT,"
                +CustomerContract.Columns.TASK_PostalCode+" TEXT,"
                +CustomerContract.Columns.TASK_Latitude+" TEXT,"
                +CustomerContract.Columns.TASK_Longitude+" TEXT,"
                +CustomerContract.Columns.TASK_GPSDate+" TEXT,"
                +CustomerContract.Columns.TASK_CreditDay+" TEXT,"
                +CustomerContract.Columns.TASK_FlagMember+" TEXT,"
                +CustomerContract.Columns.TASK_NetPoint+" TEXT,"
                +CustomerContract.Columns.TASK_FlagBill+" TEXT,"
                +CustomerContract.Columns.TASK_FlagNew+" TEXT,"
                +CustomerContract.Columns.TASK_FlagEdit+" TEXT,"
                +CustomerContract.Columns.TASK_FlagShelf+" TEXT);";
        Log.d(TAG, "onCreate: "+tbl_Customer);
        db.execSQL(tbl_Customer);

        //Table SaleArea
        String tbl_SaleArea = "CREATE TABLE "+SaleAreaContract.TABLE_NAME+"("
                +SaleAreaContract.Columns.TASK_BRANCHID+" TEXT,"
                +SaleAreaContract.Columns.TASK_SALAREAID+" TEXT,"
                +SaleAreaContract.Columns.TASK_SALAREANAME+" TEXT,"
                +SaleAreaContract.Columns.TASK_WHID+" TEXT,"
                +SaleAreaContract.Columns.TASK_SALAREASEQ+" TEXT)";
        Log.d(TAG, "onCreate: "+tbl_SaleArea);
        db.execSQL(tbl_SaleArea);

        //Table ArCustomerShelf
        String tbl_ArCustomerShelf = "CREATE TABLE "+ArCustomerShelfContract.TABLE_NAME+"("
                +ArCustomerShelfContract.Columns._ID+" INTEGER PRIMARY KEY,"
                +ArCustomerShelfContract.Columns.TASK_CUSTOMERID+" TEXT,"
                +ArCustomerShelfContract.Columns.TASK_SHELFID+" TEXT,"
                +ArCustomerShelfContract.Columns.TASK_FLAGNEW+" TEXT,"
                +ArCustomerShelfContract.Columns.TASK_FLAGEDIT+" TEXT,"
                +ArCustomerShelfContract.Columns.TASK_FLAGDEL+" TEXT"+")";
        Log.d(TAG, "onCreate: "+tbl_ArCustomerShelf);
        db.execSQL(tbl_ArCustomerShelf);

        //Table Stock
        String tbl_Stock = "CREATE TABLE "+StockContract.TABLE_NAME+"("
                +StockContract.Columns.TASK_BRANCHID+" TEXT,"
                +StockContract.Columns.TASK_PRODUCTID+" TEXT,"
                +StockContract.Columns.TASK_WHID+" TEXT,"
                +StockContract.Columns.TASK_STOCKQTY+" INTEGER,"
                +StockContract.Columns.TASK_STOCKDATE+" TEXT);";
        Log.d(TAG, "onCreate: "+tbl_Stock);
        db.execSQL(tbl_Stock);

        //Table POMaster
        String tbl_POMaster = "CREATE TABLE "+POMasterContract.TABLE_NAME+"("+
                              POMasterContract.Columns.TASK_DOCNO+" TEXT,"+
                              POMasterContract.Columns.TASK_DOCTYPECODE+" TEXT,"+
                              POMasterContract.Columns.TASK_DOCSTATUS+" TEXT,"+
                              POMasterContract.Columns.TASK_DOCDATE+" TEXT,"+
                              POMasterContract.Columns.TASK_BRANCHID+" TEXT,"+
                              POMasterContract.Columns.TASK_WHID+" TEXT,"+
                              POMasterContract.Columns.TASK_EMPID+" TEXT,"+
                              POMasterContract.Columns.TASK_SALAREALID+" TEXT,"+
                              POMasterContract.Columns.TASK_CUSTOMERID+" TEXT,"+
                              POMasterContract.Columns.TASK_AMOUNT+" REAL,"+
                              POMasterContract.Columns.TASK_EXCVAT+" REAL,"+
                              POMasterContract.Columns.TASK_INCVAT+" REAL,"+
                              POMasterContract.Columns.TASK_VATAMT+" REAL,"+
                              POMasterContract.Columns.TASK_TOTALDUE+" REAL,"+
                              POMasterContract.Columns.TASK_REVISIONNO+" TEXT,"+
                              POMasterContract.Columns.TASK_CRDATE+" TEXT,"+
                              POMasterContract.Columns.TASK_EDDATE+" TEXT,"+
                              POMasterContract.Columns.TASK_LATITUDE+" TEXT,"+
                              POMasterContract.Columns.TASK_LONGITUDE+" TEXT,"+
                              POMasterContract.Columns.TASK_DISCOUNTTYPE+" TEXT,"+
                              POMasterContract.Columns.TASK_DISCOUNT+" TEXT,"+
                              POMasterContract.Columns.TASK_FREIGHT+" TEXT,"+
                              POMasterContract.Columns.TASK_PAYTYPE+" TEXT,"+
                              POMasterContract.Columns.TASK_REMARK+" TEXT,"+
                              POMasterContract.Columns.TASK_COMMENT+" TEXT,"+
                              POMasterContract.Columns.TASK_DISCOUNTRATE+" TEXT)";
        Log.d(TAG, "onCreate: "+tbl_POMaster);
        db.execSQL(tbl_POMaster);

        //Table PODetail
        String tbl_PODetail = "CREATE TABLE "+PODetailContract.TABLE_NAME+"("
                +PODetailContract.Columns.TASK_DOCNO+" TEXT,"
                +PODetailContract.Columns.TASK_DOCTYPECODE+" TEXT,"
                +PODetailContract.Columns.TASK_DOCSTATUS+" TEXT,"
                +PODetailContract.Columns.TASK_DOCDATE+" TEXT,"
                +PODetailContract.Columns.TASK_CUSTOMERID+" TEXT,"
                +PODetailContract.Columns.TASK_LINE+" TEXT,"
                +PODetailContract.Columns.TASK_PRODUCTID+" TEXT,"
                +PODetailContract.Columns.TASK_ORDERUOM+" TEXT,"
                +PODetailContract.Columns.TASK_ORDERUNIT+" TEXT,"
                +PODetailContract.Columns.TASK_ORDERQTY+" TEXT,"
                +PODetailContract.Columns.TASK_UNITPRICE+" TEXT,"
                +PODetailContract.Columns.TASK_VATTYPE+" TEXT,"
                +PODetailContract.Columns.TASK_LINETOTAL+" TEXT,"
                +PODetailContract.Columns.TASK_LINEDISCOUNTRATE+" TEXT,"
                +PODetailContract.Columns.TASK_LINEDISCOUNT+" TEXT,"
                +PODetailContract.Columns.TASK_LINEREMARK+" TEXT,"
                +PODetailContract.Columns.TASK_FREEQTY+" TEXT,"
                +PODetailContract.Columns.TASK_FREEUOM+" TEXT,"
                +PODetailContract.Columns.TASK_FREEUNIT+" TEXT,"
                +PODetailContract.Columns.TASK_WHID+" TEXT);";
        Log.d(TAG, "onCreate: "+tbl_PODetail);
        db.execSQL(tbl_PODetail);


        String tbl_PDA = "CREATE TABLE "+PDAContract.TABLE_NAME+" ("+
                PDAContract.Columns.TASK_DISTRICTID+" TEXT,"+
                PDAContract.Columns.TASK_DISTRICTNAME+" TEXT,"+
                PDAContract.Columns.TASK_DISTRICTCODE+" TEXT,"+
                PDAContract.Columns.TASK_AREAID+" TEXT,"+
                PDAContract.Columns.TASK_AREANAME+" TEXT,"+
                PDAContract.Columns.TASK_PROVINCEID+" TEXT,"+
                PDAContract.Columns.TASK_PROVINCENAME+" TEXT,"+
                PDAContract.Columns.TASK_WHID+" TEXT)";
        Log.d(TAG, "onCreate: "+tbl_PDA);
        db.execSQL(tbl_PDA);

        //Table Visit
        String tbl_Visit = "CREATE TABLE "+VisitContract.TABLE_NAME+" ("+
                        VisitContract.Columns.TASK_BRANCHID+" TEXT,"+
                        VisitContract.Columns.TASK_WHID+" TEXT,"+
                        VisitContract.Columns.TASK_CUSTOMERID+" TEXT,"+
                        VisitContract.Columns.TASK_VISITID+" TEXT,"+
                        VisitContract.Columns.TASK_VISTITYPE+" TEXT,"+
                        VisitContract.Columns.TASK_VISITDATE+" TEXT,"+
                        VisitContract.Columns.TASK_VISITSTATUS+" TEXT,"+
                        VisitContract.Columns.TASK_CAUSEID+" TEXT,"+
                        VisitContract.Columns.TASK_CAUSEREMARK+" TEXT,"+
                        VisitContract.Columns.TASK_LATITUDE+" TEXT,"+
                        VisitContract.Columns.TASK_LONGITUDE+" TEXT,"+
                        VisitContract.Columns.TASK_CRDATE+" TEXT,"+
                        VisitContract.Columns.TASK_EDDATE+" TEXT)";
        Log.d(TAG, "onCreate: " + tbl_Visit);
        db.execSQL(tbl_Visit);

        //Table VisitStock
        String tbl_VisitStock = "CREATE TABLE "+VisitStockContract.TABLE_NAME+" ("+
                                VisitStockContract.Columns.TASK_BRNCHID+" TEXT,"+
                                VisitStockContract.Columns.TASK_WHID+" TEXT,"+
                                VisitStockContract.Columns.TASK_CUSTOMERID+" TEXT,"+
                                VisitStockContract.Columns.TASK_DOCNO+" TEXT,"+
                                VisitStockContract.Columns.TASK_VISITID+" TEXT,"+
                                VisitStockContract.Columns.TASK_VISITTYPE+" TEXT,"+
                                VisitStockContract.Columns.TASK_VISITDATE+" TEXT,"+
                                VisitStockContract.Columns.TASK_PRODUCTID+" TEXT,"+
                                VisitStockContract.Columns.TASK_STOCKOTY+" TEXT)";
        Log.d(TAG, "onCreate: " + tbl_VisitStock);
        db.execSQL(tbl_VisitStock);

        //Table Cart
        String tbl_Cart = "CREATE TABLE Cart (AutoID INTEGER PRIMARY KEY, DocNO TEXT, CustomerID TEXT, OrderUom TEXT, ProductID TEXT, OrderQty INTEGER)";
        Log.d(TAG, "onCreate: " + tbl_Cart);
        db.execSQL(tbl_Cart);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ShopTypeContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ProductContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+CauseContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+BranchWarehouseContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+SettingContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+CustomerContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+SaleAreaContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ArCustomerShelfContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+StockContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PODetailContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PDAContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS Cart");

        onCreate(db);

    }

    //Refresh data
    public void insertSetting(SettingModel settingModels){

        ContentValues con = new ContentValues();
        con.put(SettingContract.Columns.TASK_CFGNAME,settingModels.getCfgName());
        con.put(SettingContract.Columns.TASK_CFGVALUE,settingModels.getCfgValue());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow(SettingContract.TABLE_NAME,"",con);
    }

    //Refresh data
    public void insertShopType(ShopTypeModel shopTypeModel){

        ContentValues con = new ContentValues();
        con.put(ShopTypeContract.Columns.TASK_SHOPTYPECODE,shopTypeModel.getShopTypeCode());
        con.put(ShopTypeContract.Columns.TASK_SHOPETYPENAME,shopTypeModel.getShopTypeName());
        con.put(ShopTypeContract.Columns.TASK_SHOPTYPEGROUPID,shopTypeModel.getShopTypeGroupID());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ShopTypeContract.TABLE_NAME,"",con);

    }

    //Refresh data
    public void insertProduct(ProductModel productModel){

        ContentValues con = new ContentValues();
        con.put(ProductContract.Columns.TASK_PRODUCTID,productModel.getProductID());
        con.put(ProductContract.Columns.TASK_SEQ,productModel.getSep());
        con.put(ProductContract.Columns.TASK_PRODUCTNAME,productModel.getProductName());
        con.put(ProductContract.Columns.TASK_PRODUCTSHOTNAME,productModel.getProductShotName());
        con.put(ProductContract.Columns.TASK_FLAVOUR,productModel.getFlavour());
        con.put(ProductContract.Columns.TASK_VATTYPE,productModel.getVatType());
        con.put(ProductContract.Columns.TASK_PRODUCTSUBGROUPID,productModel.getProductSubGroupID());
        con.put(ProductContract.Columns.TASK_PRODUCTSUBGROUPNAME,productModel.getProductSubGroupName());
        con.put(ProductContract.Columns.TASK_PRODUCTGROUPID,productModel.getProductGroupID());
        con.put(ProductContract.Columns.TASK_PRODUCTGROUPNAME,productModel.getProductGroupName());
        con.put(ProductContract.Columns.TASK_UOMSETID,productModel.getUomSetID());
        con.put(ProductContract.Columns.TASK_UOMSETNAME,productModel.getUomSetName());
        con.put(ProductContract.Columns.TASK_BASEQTY,productModel.getBaseOty());
        con.put(ProductContract.Columns.TASK_SELLPRICE,productModel.getSellPrice());
        con.put(ProductContract.Columns.TASK_SELLPRICEVAT,productModel.getSellPriceVat());
        con.put(ProductContract.Columns.TASK_PRICEGROUPID,productModel.getPriceGroupID());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow(ProductContract.TABLE_NAME,"",con);


        }

    //Refresh data
    public void insertCause(CauseModel causeModel){

        ContentValues con = new ContentValues();
        con.put(CauseContract.Columns.TASK_CAUSEID,causeModel.getCauseID());
        con.put(CauseContract.Columns.TASK_CAUSECODE,causeModel.getCauseCode());
        con.put(CauseContract.Columns.TASK_CAUSENAME,causeModel.getCauseName());
        con.put(CauseContract.Columns.TASK_CAUSEGROUP,causeModel.getCauseGroup());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow(CauseContract.TABLE_NAME,"",con);
    }

    //Refresh data
    public void insertBranchWarehouse(BranchWarehouseModel branchWarehouseModel){

        ContentValues con = new ContentValues();
        con.put(BranchWarehouseContract.Columns.TASK_WHID,branchWarehouseModel.getWHID());
        con.put(BranchWarehouseContract.Columns.TASK_BRANCHID,branchWarehouseModel.getBranchID());
        con.put(BranchWarehouseContract.Columns.TASK_WHTYPE,branchWarehouseModel.getWHType());
        con.put(BranchWarehouseContract.Columns.TASK_WHNAME,branchWarehouseModel.getWHNAME());
        con.put(BranchWarehouseContract.Columns.TASK_WHSEQ,branchWarehouseModel.getWHSeq());
        con.put(BranchWarehouseContract.Columns.TASK_EMPID,branchWarehouseModel.getEmpID());
        con.put(BranchWarehouseContract.Columns.TASK_TITLENAME,branchWarehouseModel.getTitleName());
        con.put(BranchWarehouseContract.Columns.TASK_FIRSTNAME,branchWarehouseModel.getFirstName());
        con.put(BranchWarehouseContract.Columns.TASK_LASTNAME,branchWarehouseModel.getLastName());
        con.put(BranchWarehouseContract.Columns.TASK_USERNAME,branchWarehouseModel.getUsername());
        con.put(BranchWarehouseContract.Columns.TASK_PASSWORD,branchWarehouseModel.getPassword());
        con.put(BranchWarehouseContract.Columns.TASK_POSNo,branchWarehouseModel.getPOSNo());
        con.put(BranchWarehouseContract.Columns.TASK_EMPIDCARD,branchWarehouseModel.getEmpIDCard());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow(BranchWarehouseContract.TABLE_NAME,"",con);
    }

    //Prepare data
    public void insertCustomer(CustomerModel customerModel){

        ContentValues con = new ContentValues();
        con.put(CustomerContract.Columns.TASK_CUSTOMERID,customerModel.CustomerID);
        con.put(CustomerContract.Columns.TASK_BRANCHID,customerModel.BranchID);
        con.put(CustomerContract.Columns.TASK_WHID,customerModel.WHID);
        con.put(CustomerContract.Columns.TASK_EMPID,customerModel.EmpID);
        con.put(CustomerContract.Columns.TASK_SALAREAID,customerModel.SalAreaID);
        con.put(CustomerContract.Columns.TASK_SHOPTYPEID,customerModel.ShopTypeID);
        con.put(CustomerContract.Columns.TASK_CustSeq,customerModel.CustSeq);
        con.put(CustomerContract.Columns.TASK_CustTitle,customerModel.CustTitle);
        con.put(CustomerContract.Columns.TASK_CustName,customerModel.CustName);
        con.put(CustomerContract.Columns.TASK_CustShortName,customerModel.CustShortName);
        con.put(CustomerContract.Columns.TASK_CustImage,customerModel.CustImage);
        con.put(CustomerContract.Columns.TASK_BillTo,customerModel.BillTo);
        con.put(CustomerContract.Columns.TASK_Tel,customerModel.Tel);
        con.put(CustomerContract.Columns.TASK_Contact,customerModel.Contact);
        con.put(CustomerContract.Columns.TASK_AddressNo,customerModel.AddressNo);
        con.put(CustomerContract.Columns.TASK_Lane,customerModel.Lane);
        con.put(CustomerContract.Columns.TASK_Street,customerModel.Street);
        con.put(CustomerContract.Columns.TASK_AreaID,customerModel.AreaID);
        con.put(CustomerContract.Columns.TASK_DistrictID,customerModel.DistrictID);
        con.put(CustomerContract.Columns.TASK_ProvinceID,customerModel.ProvinceID);
        con.put(CustomerContract.Columns.TASK_PostalCode,customerModel.PostalCode);
        con.put(CustomerContract.Columns.TASK_Latitude,customerModel.Latitude);
        con.put(CustomerContract.Columns.TASK_Longitude,customerModel.Longitude);
        con.put(CustomerContract.Columns.TASK_GPSDate,customerModel.GPSDate);
        con.put(CustomerContract.Columns.TASK_CreditDay,customerModel.CreditDay);
        con.put(CustomerContract.Columns.TASK_FlagMember,customerModel.FlagMember);
        con.put(CustomerContract.Columns.TASK_NetPoint,customerModel.NetPoint);
        con.put(CustomerContract.Columns.TASK_FlagShelf,customerModel.FlagShelf);
        con.put(CustomerContract.Columns.TASK_FlagBill,customerModel.FlagBill);
        con.put(CustomerContract.Columns.TASK_FlagNew,customerModel.FlagNew);
        con.put(CustomerContract.Columns.TASK_FlagEdit,customerModel.FlagEdit);
        con.put(CustomerContract.Columns.TASK_Moo,customerModel.Moo);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(CustomerContract.TABLE_NAME,"",con);
    }
    //Prepare data
    public void insertSaleArea(SaleAreaModel saleAreaModel){
        ContentValues con = new ContentValues();
        con.put(SaleAreaContract.Columns.TASK_BRANCHID,saleAreaModel.getBranchID());
        con.put(SaleAreaContract.Columns.TASK_WHID,saleAreaModel.getWHID());
        con.put(SaleAreaContract.Columns.TASK_SALAREAID,saleAreaModel.getSalAreaID());
        con.put(SaleAreaContract.Columns.TASK_SALAREANAME,saleAreaModel.getSalAreaName());
        con.put(SaleAreaContract.Columns.TASK_SALAREASEQ,saleAreaModel.getSalAreaSeq());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(SaleAreaContract.TABLE_NAME,"",con);

    }
    //Prepare data
    public void insertArCustomerShelf(ArCustomerShelfModel arCustomerShelfModel){

        ContentValues con = new ContentValues();
        con.put(ArCustomerShelfContract.Columns.TASK_CUSTOMERID,arCustomerShelfModel.getCustomerID());
        con.put(ArCustomerShelfContract.Columns.TASK_SHELFID,arCustomerShelfModel.getShelfID());
        con.put(ArCustomerShelfContract.Columns.TASK_FLAGNEW,arCustomerShelfModel.getFlagNew());
        con.put(ArCustomerShelfContract.Columns.TASK_FLAGEDIT,arCustomerShelfModel.getFlagEdit());
        con.put(ArCustomerShelfContract.Columns.TASK_FLAGDEL,arCustomerShelfModel.getFlagDel());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ArCustomerShelfContract.TABLE_NAME,"",con);

    }
    //Prepare data
    public void insertStock(StockModel stockModel){
        ContentValues con = new ContentValues();
        con.put(StockContract.Columns.TASK_BRANCHID,stockModel.getBranchID());
        con.put(StockContract.Columns.TASK_PRODUCTID,stockModel.getProductID());
        con.put(StockContract.Columns.TASK_WHID,stockModel.getWHID());
        con.put(StockContract.Columns.TASK_STOCKQTY,stockModel.getStockQty());
        con.put(StockContract.Columns.TASK_STOCKDATE,stockModel.getStockDate());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(StockContract.TABLE_NAME,"",con);
    }
    //Prepare data
    public void insertPODetail(PODetailModel poDetailModel){

        ContentValues con = new ContentValues();
        con.put(PODetailContract.Columns.TASK_DOCNO,poDetailModel.getDocNo());
        con.put(PODetailContract.Columns.TASK_DOCTYPECODE,poDetailModel.getDocTypeCode());
        con.put(PODetailContract.Columns.TASK_DOCSTATUS,poDetailModel.getDocStatus());
        con.put(PODetailContract.Columns.TASK_CUSTOMERID,poDetailModel.getCustomerID());
        con.put(PODetailContract.Columns.TASK_LINE,poDetailModel.getLine());
        con.put(PODetailContract.Columns.TASK_PRODUCTID,poDetailModel.getProductID());
        con.put(PODetailContract.Columns.TASK_ORDERUOM,poDetailModel.getOrderUom());
        con.put(PODetailContract.Columns.TASK_ORDERUNIT,poDetailModel.getOrderUnit());
        con.put(PODetailContract.Columns.TASK_ORDERQTY,poDetailModel.getOrderQty()); //แก้ Task
        con.put(PODetailContract.Columns.TASK_UNITPRICE,poDetailModel.getUnitPrice());
        con.put(PODetailContract.Columns.TASK_VATTYPE,poDetailModel.getVatType());
        con.put(PODetailContract.Columns.TASK_LINETOTAL,poDetailModel.getLineTotal());
        con.put(PODetailContract.Columns.TASK_LINEDISCOUNTRATE,poDetailModel.getLineDiscountRate());
        con.put(PODetailContract.Columns.TASK_LINEDISCOUNT,poDetailModel.getLineDiscount());
        con.put(PODetailContract.Columns.TASK_LINEREMARK,poDetailModel.getLineRemark());
        con.put(PODetailContract.Columns.TASK_FREEQTY,poDetailModel.getFreeOty());
        con.put(PODetailContract.Columns.TASK_FREEUOM,poDetailModel.getFreeUom());
        con.put(PODetailContract.Columns.TASK_FREEUNIT,poDetailModel.getFreeUnit());
        con.put(PODetailContract.Columns.TASK_WHID,poDetailModel.getWHID());
        SQLiteDatabase db =  this.getWritableDatabase();
        db.insert(PODetailContract.TABLE_NAME,"",con);


    }
    public void insertPDA(PDAModel pdaModel)
    {
        ContentValues con = new ContentValues();
        con.put(PDAContract.Columns.TASK_DISTRICTID,pdaModel.getDistrictID());
        con.put(PDAContract.Columns.TASK_DISTRICTNAME,pdaModel.getDistrictName());
        con.put(PDAContract.Columns.TASK_AREAID,pdaModel.getAreaID());
        con.put(PDAContract.Columns.TASK_AREANAME,pdaModel.getAreaName());
        con.put(PDAContract.Columns.TASK_PROVINCEID,pdaModel.getProvinceID());
        con.put(PDAContract.Columns.TASK_PROVINCENAME,pdaModel.getProvinceName());
        con.put(PDAContract.Columns.TASK_WHID,pdaModel.getWHID());
        con.put(PDAContract.Columns.TASK_DISTRICTCODE,pdaModel.getDistrictCode());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(PDAContract.TABLE_NAME,"",con);
    }
    public void CreateCustomer(CustomerModel customerModel){
        SQLiteDatabase db = this.getReadableDatabase();
        String CustomerID = "";
        String SQL_CustomerID = "SELECT MAX("+CustomerContract.Columns.TASK_CUSTOMERID+")+1 AS Max FROM "
                                +CustomerContract.TABLE_NAME+" Where "+CustomerContract.Columns.TASK_DistrictID+" = ?";
        Cursor cursor = db.rawQuery(SQL_CustomerID,new String[]{customerModel.getDistrictID()});
        while (cursor.moveToNext()){
            CustomerID = cursor.getString(0);
        }

        if (CustomerID == null || CustomerID.equals(""))
        {
            String SQL_GenCustomer = "SELECT DISTINCT "+PDAContract.Columns.TASK_DISTRICTCODE+" FROM "+PDAContract.TABLE_NAME+" WHERE "+PDAContract.Columns.TASK_DISTRICTID+" =?";
            cursor = db.rawQuery(SQL_GenCustomer,new String[]{customerModel.getDistrictID()});
            while (cursor.moveToNext())
            {
                CustomerID = customerModel.getBranchID()+cursor.getString(0)+"001";
            }
        }

        ContentValues con = new ContentValues();
        con.put(CustomerContract.Columns.TASK_CUSTOMERID,CustomerID);
        con.put(CustomerContract.Columns.TASK_BRANCHID,customerModel.getBranchID());
        con.put(CustomerContract.Columns.TASK_WHID,customerModel.getWHID());
        con.put(CustomerContract.Columns.TASK_EMPID,customerModel.getEmpID());
        con.put(CustomerContract.Columns.TASK_SALAREAID,customerModel.getSalAreaID());
        con.put(CustomerContract.Columns.TASK_SHOPTYPEID,customerModel.getShopTypeID());
        con.put(CustomerContract.Columns.TASK_CustSeq,"0");
        con.put(CustomerContract.Columns.TASK_CustTitle,"คุณ");
        con.put(CustomerContract.Columns.TASK_CustName,customerModel.getCustName());
        con.put(CustomerContract.Columns.TASK_CustShortName,customerModel.getCustShortName());
        con.put(CustomerContract.Columns.TASK_CustImage,customerModel.getCustImage());
        con.put(CustomerContract.Columns.TASK_BillTo,customerModel.getBillTo());
        con.put(CustomerContract.Columns.TASK_Tel,customerModel.getTel());
        con.put(CustomerContract.Columns.TASK_Contact,customerModel.getContact());
        con.put(CustomerContract.Columns.TASK_AddressNo,customerModel.getAddressNo());
        con.put(CustomerContract.Columns.TASK_Moo,customerModel.getMoo());
        con.put(CustomerContract.Columns.TASK_AreaID,customerModel.getAreaID());
        con.put(CustomerContract.Columns.TASK_DistrictID,customerModel.getDistrictID());
        con.put(CustomerContract.Columns.TASK_ProvinceID,customerModel.getProvinceID());
        con.put(CustomerContract.Columns.TASK_GPSDate,customerModel.getGPSDate());
        con.put(CustomerContract.Columns.TASK_CreditDay,"0");
        con.put(CustomerContract.Columns.TASK_FlagMember,"False");
        con.put(CustomerContract.Columns.TASK_NetPoint,"0");
        con.put(CustomerContract.Columns.TASK_FlagShelf,"False");
        con.put(CustomerContract.Columns.TASK_FlagNew,customerModel.getFlagNew());
        con.put(CustomerContract.Columns.TASK_FlagEdit,customerModel.getFlagEdit());
        con.put(CustomerContract.Columns.TASK_FlagBill,customerModel.getFlagBill());
        db = this.getWritableDatabase();
        db.insert(CustomerContract.TABLE_NAME,"",con);

    }
    public void UpdateCustomer(CustomerModel customerModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(CustomerContract.Columns.TASK_CustName,customerModel.getCustName());
        con.put(CustomerContract.Columns.TASK_CustShortName,customerModel.getCustShortName());
        con.put(CustomerContract.Columns.TASK_CustImage,customerModel.getCustImage());
        con.put(CustomerContract.Columns.TASK_SHOPTYPEID,customerModel.getShopTypeID());
        con.put(CustomerContract.Columns.TASK_BillTo,customerModel.getBillTo());
        con.put(CustomerContract.Columns.TASK_Tel,customerModel.getTel());
        con.put(CustomerContract.Columns.TASK_Contact,customerModel.getContact());
        con.put(CustomerContract.Columns.TASK_AddressNo,customerModel.getAddressNo());
        con.put(CustomerContract.Columns.TASK_Moo,customerModel.getMoo());
        con.put(CustomerContract.Columns.TASK_AreaID,customerModel.getAreaID());
        con.put(CustomerContract.Columns.TASK_DistrictID,customerModel.getDistrictID());
        con.put(CustomerContract.Columns.TASK_ProvinceID,customerModel.getProvinceID());
        con.put(CustomerContract.Columns.TASK_FlagEdit,customerModel.getFlagEdit());
        con.put(CustomerContract.Columns.TASK_FlagBill,customerModel.getFlagBill());
        db.update(CustomerContract.TABLE_NAME,con,CustomerContract.Columns.TASK_CUSTOMERID+" ='"+customerModel.getCustomerID()+"'",null);

    }
    public void UpdateVisitStock(String VisitID, String ProductID,Integer StockQty){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con =  new ContentValues();
        con.put(VisitStockContract.Columns.TASK_STOCKOTY,StockQty);
        db.update(VisitStockContract.TABLE_NAME,con,VisitStockContract.Columns.TASK_VISITID+ " = '"+VisitID+"' and "+VisitStockContract.Columns.TASK_PRODUCTID+" = '"+ProductID+"'",null);

    }

    public void InsertCart(CauseModel causeModel)
    {


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void VisitShop(VisitModel visitModel){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        db.rawQuery("DELETE  FROM "+VisitContract.TABLE_NAME,null);
        db = this.getReadableDatabase();
        String Query = "SELECT * FROM "+VisitContract.TABLE_NAME+
                        " WHERE "+VisitContract.Columns.TASK_CUSTOMERID+" = ? ";
        Cursor cursor = db.rawQuery(Query,new String[]{visitModel.getCustomerID()});

        if (cursor.getCount() > 0){

            //ถ้ามีข้อมูลอยู่แล้วให้อัพเดท แล้ว Return ค่ากลับไปยัง Adapter
            db = this.getWritableDatabase();
            ContentValues con = new ContentValues();
            con.put(VisitContract.Columns.TASK_VISITSTATUS,visitModel.getVisitStatus());
            con.put(VisitContract.Columns.TASK_CAUSEID,visitModel.getCauseID());
            con.put(VisitContract.Columns.TASK_EDDATE,Calendar.getInstance().getTime().toString());
            db.update(VisitContract.TABLE_NAME, con, VisitContract.Columns.TASK_CUSTOMERID+" = "+"'"+visitModel.getCustomerID()+"'",null);

        }
        else {
            //ไม่มีให้ Insert ใหม่
            db = this.getReadableDatabase();
            String Check = "SELECT * FROM "+VisitContract.TABLE_NAME;

            Cursor GenVisitID = db.rawQuery(Check, null );

            if (GenVisitID.getCount() > 0)
            {
                Check = "SELECT  MAX("+VisitContract.Columns.TASK_VISITID+")+1 as VisitID FROM "+VisitContract.TABLE_NAME;
                GenVisitID = db.rawQuery(Check, null );
                while (GenVisitID.moveToNext())
                {
                    visitModel.setVisitID(GenVisitID.getString(0));
                }

            }
            else {

                int year = Calendar.getInstance().get(Calendar.YEAR)+543;
                int mouth = Calendar.getInstance().get(Calendar.MONTH)+1;
                int day = Calendar.getInstance().get(Calendar.DATE);
                String VisitID = visitModel.getBranchID()+visitModel.getWHID().substring(4,6)+String.valueOf(year).substring(2,4)+mouth+day+"001";
                visitModel.setVisitID(VisitID);

            }

            db = this.getWritableDatabase();
            ContentValues con = new ContentValues();
            con.put(VisitContract.Columns.TASK_BRANCHID,visitModel.getBranchID());
            con.put(VisitContract.Columns.TASK_WHID,visitModel.getWHID());
            con.put(VisitContract.Columns.TASK_CUSTOMERID,visitModel.getCustomerID());
            con.put(VisitContract.Columns.TASK_VISITID,visitModel.getVisitID());
            con.put(VisitContract.Columns.TASK_VISTITYPE,visitModel.getVisitType());
            con.put(VisitContract.Columns.TASK_VISITDATE,visitModel.getVisitDate());
            con.put(VisitContract.Columns.TASK_VISITSTATUS,visitModel.getVisitStatus());
            con.put(VisitContract.Columns.TASK_CAUSEID,visitModel.getCauseID());
            con.put(VisitContract.Columns.TASK_CAUSEREMARK,visitModel.getCauseRemark());
            con.put(VisitContract.Columns.TASK_LATITUDE,visitModel.getLatitude());
            con.put(VisitContract.Columns.TASK_LONGITUDE,visitModel.getLongitude());
            con.put(VisitContract.Columns.TASK_CRDATE, Calendar.getInstance().getTime().toString());
            con.put(VisitContract.Columns.TASK_EDDATE,Calendar.getInstance().getTime().toString());
            db.insert(VisitContract.TABLE_NAME,"",con);

        }

    }

    public void CreateVisitStock(VisitStockModel stockModel){

        SQLiteDatabase db = this.getReadableDatabase();

        String SQL_VisitStock = "SELECT * FROM "+VisitStockContract.TABLE_NAME+" WHERE "+VisitStockContract.Columns.TASK_CUSTOMERID+" = ? and "+VisitStockContract.Columns.TASK_PRODUCTID+" = ?";
        Cursor cursor = db.rawQuery(SQL_VisitStock, new String[]{stockModel.getCustomerID(),stockModel.getProductID()});

        if (cursor.getCount() > 0){
            Log.d(TAG, "CreateVisitStock: HAS");
        }
        else {
            ContentValues con = new ContentValues();
            con.put(VisitStockContract.Columns.TASK_BRNCHID,stockModel.getBranchID());
            con.put(VisitStockContract.Columns.TASK_WHID,stockModel.getWHID());
            con.put(VisitStockContract.Columns.TASK_DOCNO,stockModel.getDocNo());
            con.put(VisitStockContract.Columns.TASK_VISITID,stockModel.getVisitID());
            con.put(VisitStockContract.Columns.TASK_VISITTYPE,stockModel.getVisitType());
            con.put(VisitContract.Columns.TASK_CUSTOMERID,stockModel.getCustomerID());
            con.put(VisitStockContract.Columns.TASK_VISITDATE,stockModel.getVisitDate());
            con.put(VisitStockContract.Columns.TASK_PRODUCTID,stockModel.getProductID());
            con.put(VisitStockContract.Columns.TASK_STOCKOTY,stockModel.getStockQty());
            db.insert(VisitStockContract.TABLE_NAME,"",con);

        }

    }
    public void CreatePOMaster(POMasterModel mPOMasterModel)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String SQL_PO = "SELECT * FROM "+POMasterContract.TABLE_NAME+" WHERE "+POMasterContract.Columns.TASK_CUSTOMERID+" = ? and "+POMasterContract.Columns.TASK_DOCSTATUS+" = ?";
        Cursor cursor = db.rawQuery(SQL_PO, new String[]{mPOMasterModel.getCustomerID(),"4"});

        if (cursor.getCount() > 0){
            Log.d(TAG, "CreatePOMaster: HAS");
        }
        else {
            db = this.getWritableDatabase();
            ContentValues con = new ContentValues();
            con.put(POMasterContract.Columns.TASK_DOCNO,mPOMasterModel.getDocNo());
            con.put(POMasterContract.Columns.TASK_DOCTYPECODE,mPOMasterModel.getDocTypeCode());
            con.put(POMasterContract.Columns.TASK_DOCSTATUS,mPOMasterModel.getDocStatus());
            con.put(POMasterContract.Columns.TASK_DOCDATE,mPOMasterModel.getDocDate());
            con.put(POMasterContract.Columns.TASK_BRANCHID,mPOMasterModel.getBranchID());
            con.put(POMasterContract.Columns.TASK_WHID,mPOMasterModel.getWHID());
            con.put(POMasterContract.Columns.TASK_EMPID,mPOMasterModel.getEmpID());
            con.put(POMasterContract.Columns.TASK_SALAREALID,mPOMasterModel.getSalAreaID());
            con.put(POMasterContract.Columns.TASK_CUSTOMERID,mPOMasterModel.getCustomerID());
            con.put(POMasterContract.Columns.TASK_AMOUNT,mPOMasterModel.getAmount());
            con.put(POMasterContract.Columns.TASK_EXCVAT,mPOMasterModel.getExcVat());
            con.put(POMasterContract.Columns.TASK_INCVAT,mPOMasterModel.getIncVat());
            con.put(POMasterContract.Columns.TASK_VATAMT,mPOMasterModel.getVatAmt());
            con.put(POMasterContract.Columns.TASK_TOTALDUE,mPOMasterModel.getTotalDue());
            con.put(POMasterContract.Columns.TASK_REVISIONNO,mPOMasterModel.getRevisionNo());
            con.put(POMasterContract.Columns.TASK_CRDATE,mPOMasterModel.getCrDate());
            con.put(POMasterContract.Columns.TASK_EDDATE,mPOMasterModel.getEndDate());
            con.put(POMasterContract.Columns.TASK_LATITUDE,mPOMasterModel.getLatitude());
            con.put(POMasterContract.Columns.TASK_LONGITUDE,mPOMasterModel.getLongitude());
            con.put(POMasterContract.Columns.TASK_DISCOUNTTYPE,mPOMasterModel.getDiscountType());
            con.put(POMasterContract.Columns.TASK_DISCOUNT,mPOMasterModel.getDiscount());
            con.put(POMasterContract.Columns.TASK_FREIGHT,mPOMasterModel.getFreight());
            con.put(POMasterContract.Columns.TASK_PAYTYPE,mPOMasterModel.getPayType());
            con.put(POMasterContract.Columns.TASK_REMARK,mPOMasterModel.getRemark());
            con.put(POMasterContract.Columns.TASK_COMMENT,mPOMasterModel.getComment());
            con.put(POMasterContract.Columns.TASK_DISCOUNTRATE,mPOMasterModel.getDiscountRate());
            db.insert(POMasterContract.TABLE_NAME,"",con);
        }

    }

    public Cursor GetProduct(String CustomerID, String ProductGroupID){
        SQLiteDatabase db = this.getReadableDatabase();
        String SQL_Product = "\n" +
                "SELECT \n" +
                "distinct VisitStock.ProductID,\n" +
                "Product.ProductName,\n" +
                "Product.ProductGroupID,\n" +
                "VisitStock.StockQty,\n" +
                "Cart.OrderQty,\n" +
                "(SELECT \n" +
                "CASE WHEN StockQty = NULL THEN \n" +
                "         0   \n" +
                "     ELSE \n" +
                "        StockQty \n" +
                "     END\n" +
                "from VisitStock WHERE ProductID = VisitStock.ProductID and CustomerID = VisitStock.CustomerID and strftime('%m', VisitDate) = strftime('%m', 'now')-2 ) as StockBillOne,\n" +
                "(SELECT \n" +
                "CASE WHEN OrderQty = NULL THEN \n" +
                "         0   \n" +
                "     ELSE \n" +
                "        OrderQty \n" +
                "     END\n" +
                "from PODetail WHERE ProductID = VisitStock.ProductID and CustomerID = VisitStock.CustomerID and strftime('%m', DocDate) = strftime('%m', 'now')-2 ) as POBillOne,\n" +
                "(SELECT \n" +
                "CASE WHEN StockQty = NULL THEN \n" +
                "         0   \n" +
                "     ELSE \n" +
                "        StockQty \n" +
                "     END\n" +
                "from VisitStock WHERE ProductID = VisitStock.ProductID and CustomerID = VisitStock.CustomerID and strftime('%m', VisitDate) = strftime('%m', 'now')-1 ) as StockBillTwo,\n" +
                "(SELECT \n" +
                "CASE WHEN OrderQty = NULL THEN \n" +
                "         0   \n" +
                "     ELSE \n" +
                "        OrderQty \n" +
                "     END\n" +
                "from PODetail WHERE ProductID = VisitStock.ProductID and CustomerID = VisitStock.CustomerID and strftime('%m', DocDate) = strftime('%m', 'now')-1 ) as POBillTwo\n" +
                "FROM VisitStock \n" +
                "LEFT JOIN Product on VisitStock.ProductID = Product.ProductID \n" +
                "LEFT JOIN Cart on VisitStock.ProductID = Cart.ProductID and VisitStock.CustomerID  = Cart.CustomerID\n" +
                "WHERE VisitStock.CustomerID = "+CustomerID+" and Product.ProductGroupID = "+ProductGroupID+" ";
        Cursor cursor = db.rawQuery(SQL_Product,null);
        Log.d(TAG, "GetProduct: "+SQL_Product);
        return cursor;
    }

    public Boolean CheckLogin(String Username, String Password){

        SQLiteDatabase db = this.getReadableDatabase();
        String Check = "SELECT * FROM "+BranchWarehouseContract.TABLE_NAME+
                       " WHERE lower("+BranchWarehouseContract.Columns.TASK_USERNAME+ ") = ? " +
                       "and lower("+BranchWarehouseContract.Columns.TASK_PASSWORD+") =?";
        Cursor cursor  = db.rawQuery(Check,new String[]{Username,Password});
        String WHID = null;
        while (cursor.moveToNext()){
            WHID = cursor.getString(1);
        }

        if (WHID != null){

            db = this.getWritableDatabase();
            ContentValues con = new ContentValues();
            con.put(SettingContract.Columns.TASK_CFGVALUE,WHID);
            db.update(SettingContract.TABLE_NAME,con,SettingContract.Columns.TASK_CFGNAME+" = 'WHID'",null);

            return true;
        }
        else {
            return false;
        }
    }


    public void UpdateSaleDate(String SaleDate){

        ContentValues con = new ContentValues();
        con.put(SettingContract.Columns.TASK_CFGVALUE,SaleDate);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(SettingContract.TABLE_NAME,con,SettingContract.Columns.TASK_CFGNAME+" = 'SaleDate'",null);

    }

    public void UpdateIP(String IP){

        ContentValues con = new ContentValues();
        con.put(SettingContract.Columns.TASK_CFGVALUE,IP);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(SettingContract.TABLE_NAME,con,SettingContract.Columns.TASK_CFGNAME+" = 'WebServiceHost'",null);
    }

    public void UpdateDNS(String DNS){

        ContentValues con = new ContentValues();
        con.put(SettingContract.Columns.TASK_CFGVALUE,DNS);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(SettingContract.TABLE_NAME,con,SettingContract.Columns.TASK_CFGNAME+" = 'WebServiceOnline'",null);

    }

    public void UpdatePortDNS(String PortDNS){

        ContentValues con = new ContentValues();
        con.put(SettingContract.Columns.TASK_CFGVALUE,PortDNS);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(SettingContract.TABLE_NAME,con,SettingContract.Columns.TASK_CFGNAME+" ='WebServiceOnlinePort'",null);
    }


}
