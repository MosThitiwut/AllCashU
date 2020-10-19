package com.unitedfoods.allcashu.Uitls;

import com.unitedfoods.allcashu.Model.ArCustomerShelfModel;
import com.unitedfoods.allcashu.Model.BranchWarehouseModel;
import com.unitedfoods.allcashu.Model.CauseModel;
import com.unitedfoods.allcashu.Model.CustomerModel;
import com.unitedfoods.allcashu.Model.MessageModel;
import com.unitedfoods.allcashu.Model.PDAModel;
import com.unitedfoods.allcashu.Model.PODetailModel;
import com.unitedfoods.allcashu.Model.ProductModel;
import com.unitedfoods.allcashu.Model.SaleAreaModel;
import com.unitedfoods.allcashu.Model.SettingModel;
import com.unitedfoods.allcashu.Model.ShopTypeModel;
import com.unitedfoods.allcashu.Model.StockModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiPath {

    @GET("CU/api/GetSettings")
    Call<List<SettingModel>> getSettings();

    @GET("CU/api/tbl_ShopType")
    Call<List<ShopTypeModel>> getShopType();

    @GET("CU/api/GetProducts")
    Call<List<ProductModel>> getProduct();

    @GET("CU/api/tbl_Cause")
    Call<List<CauseModel>> getCause();

    @GET("CU/api/GetBranchWarehouses")
    Call<List<BranchWarehouseModel>> getBranchWarehouse();

    @GET("CU/api/GetProvinceAndDistricts")
    Call<List<PDAModel>> getPDA();

    @GET("CU/SendData/SendData")
    Call<List<MessageModel>> getSendData(@Query("WHID") String WHID,
                                         @Query("Date") String Date);

    @GET("CU/SendData/GetArCustomer")
    Call<List<CustomerModel>> getCustomer(@Query("WHID") String WHID);

    @GET("CU/SendData/GetSaleArea")
    Call<List<SaleAreaModel>> getSaleArea(@Query("WHID") String WHID);

    @GET("CU/SendData/GetArCustomerShelf")
    Call<List<ArCustomerShelfModel>> getArCustomerShelf(@Query("WHID") String WHID);

    @GET("CU/SendData/GetStock")
    Call<List<StockModel>> getStock(@Query("WHID") String WHID,
                                    @Query("Date") String Date);

    @GET("CU/SendData/GetTwolastbill")
    Call<List<PODetailModel>> getTwolastbil(@Query("WHID") String WHID);
}
