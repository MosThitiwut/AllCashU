package com.unitedfoods.allcashu.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.unitedfoods.allcashu.BranchWarehouseContract;
import com.unitedfoods.allcashu.CauseContract;
import com.unitedfoods.allcashu.DB;
import com.unitedfoods.allcashu.Home.Home;
import com.unitedfoods.allcashu.Model.BranchWarehouseModel;
import com.unitedfoods.allcashu.Model.CauseModel;
import com.unitedfoods.allcashu.Model.PDAModel;
import com.unitedfoods.allcashu.Model.ProductModel;
import com.unitedfoods.allcashu.Model.SettingModel;
import com.unitedfoods.allcashu.Model.ShopTypeModel;
import com.unitedfoods.allcashu.PDAContract;
import com.unitedfoods.allcashu.ProductContract;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.SettingContract;
import com.unitedfoods.allcashu.ShopTypeContract;
import com.unitedfoods.allcashu.Uitls.ApiPath;
import com.unitedfoods.allcashu.Uitls.GetWebService;
import com.unitedfoods.allcashu.Uitls.MyDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    Button Btn_Refresh,Btn_Login;
    TextInputEditText Username,Password;
    AlertDialog dialog;
    Toast toast;
    DB database;
    String ip;
    ApiPath apiPath;
    Activity activity = this;
    String SaleDate = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = DB.getInstance(this);
        final SQLiteDatabase db = database.getReadableDatabase();
        Btn_Refresh = findViewById(R.id.btn_refresh);
        Btn_Login  = findViewById(R.id.btn_login);
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);

        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mUsername = Username.getText().toString();
                String mPassword = Password.getText().toString();
                
                Boolean check  = database.CheckLogin(mUsername,mPassword);
                if (check){
                    Cursor cursor = db.rawQuery("SELECT "+SettingContract.Columns.TASK_CFGVALUE+" FROM "+SettingContract.TABLE_NAME+" WHERE "+SettingContract.Columns.TASK_CFGNAME+" = ?",new String[]{"SaleDate"});

                    if (cursor.getCount() != 0)
                    {
                        while (cursor.moveToNext())
                        {
                            SaleDate = cursor.getString(0);
                        }
                    }
                    Intent intent = new Intent(activity, Home.class);
                    intent.putExtra("SaleDate",SaleDate);
                    startActivity(intent);
                }
                else {

                    MyDialog myDialog = new MyDialog(activity);
                    myDialog.normalDialog("Error","ตรวจสอบ Username และ Password");
                    Log.d(TAG, "onClick: Fail");
                }
            }
        });
        // Action Button refresh  data from in branch and out branch.
        Btn_Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Build AlertDialog
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);
                final View mView = getLayoutInflater().inflate(R.layout.layout_dialog_refresh,null);

                final RadioButton radioButton_IN = mView.findViewById(R.id.radio_IN);
                final RadioButton radioButton_OUT = mView.findViewById(R.id.radio_OUT);

                radioButton_IN.setChecked(true);
                radioButton_IN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                            radioButton_IN.setChecked(true);
                            radioButton_OUT.setChecked(false);
                            toast = Toast.makeText(activity, "In", Toast.LENGTH_SHORT);
                            toast.show();


                        try {
                            Refresh(true);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        }

                });

                radioButton_OUT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        radioButton_OUT.setChecked(true);
                        radioButton_IN.setChecked(false);
                        toast = Toast.makeText(activity,"Out",Toast.LENGTH_SHORT);
                        toast.show();


                        try {

                            Refresh(false);


                        } catch (ExecutionException | InterruptedException | JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

                mBuilder.setView(mView);
                dialog = mBuilder.create();
                dialog.show();
            }
        });
    }


    private void Refresh(boolean stauts) throws InterruptedException, ExecutionException, JSONException {
        GetWebService getWebService = new GetWebService(activity);
        getWebService.execute("http://192.168.1.200/CU/api/GetSettings");
        String setting = getWebService.get();

        JSONArray JA = new JSONArray(setting);
        for (int i = 0; i < JA.length(); i++)
        {
            JSONObject JO = (JSONObject)JA.get(i);
            SettingModel settingModel = new SettingModel(JO.getString("cfgName"),JO.getString("cfgValue"));
            database.insertSetting(settingModel);
        }

        dialog.cancel();


        SQLiteDatabase db = database.getReadableDatabase();
        if (stauts){

            ip = "SELECT * FROM "+ SettingContract.TABLE_NAME
                    + " Where "+SettingContract.Columns.TASK_CFGNAME
                    + " = 'WebServiceHost'";
            Cursor cursor = db.rawQuery(ip,null);

            while (cursor.moveToNext()){
                ip = cursor.getString(1);
            }
            Log.d(TAG, "Refresh: "+ip);
            GetRefresh(ip);

//           new  Reset().execute();


        }
        else {

            String ip = "SELECT * FROM "+ SettingContract.TABLE_NAME
                    + " Where "+SettingContract.Columns.TASK_CFGNAME
                    + " = 'WebServiceOnline'";
            Cursor cursor = db.rawQuery(ip,null);

            while (cursor.moveToNext()){
                ip = cursor.getString(1);
            }
            String port = "SELECT * FROM "+SettingContract.TABLE_NAME + " Where "+SettingContract.Columns.TASK_CFGNAME+ " = 'WebServiceOnlinePort'";
            cursor = db.rawQuery(port,null);

            while (cursor.moveToNext()){
                port = cursor.getString(1);
            }
            ip = ip + ":" + port;
            Log.d(TAG, "Refresh: "+ip);
            GetRefresh(ip);
        }

    }

    private void GetRefresh(final String ip){


         final long startTime = System.nanoTime();


        final SQLiteDatabase db = database.getWritableDatabase();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+ip+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         apiPath = retrofit.create(ApiPath.class);

        final ProgressDialog progress;
        progress =  new ProgressDialog(activity);
        progress.setTitle("เตรียมข้อมูล");
        progress.setMessage("Please wait...");
        progress.show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

        //Get Setting
        Call<List<SettingModel>> callSetting = apiPath.getSettings();
        callSetting.enqueue(new Callback<List<SettingModel>>() {
            @Override
            public void onResponse(Call<List<SettingModel>> call, Response<List<SettingModel>> response) {

                List<SettingModel> settingModels = response.body();
                Log.d(TAG, "onResponse: " + settingModels);
                Cursor cursor = db.rawQuery("SELECT "+SettingContract.Columns.TASK_CFGVALUE+" FROM "+SettingContract.TABLE_NAME+" WHERE "+SettingContract.Columns.TASK_CFGNAME+" = ?",new String[]{"SaleDate"});

                if (cursor.getCount() != 0)
                {
                    while (cursor.moveToNext())
                    {
                        SaleDate = cursor.getString(0);
                    }
                }
                Log.d(TAG, "SaleDate: "+cursor.getCount());
                db.execSQL("DELETE FROM "+ SettingContract.TABLE_NAME);
                for (SettingModel settingModel : settingModels)
                {
                    database.insertSetting(settingModel);
                }
                db.execSQL("INSERT INTO "+SettingContract.TABLE_NAME+
                        "("+SettingContract.Columns.TASK_CFGNAME+","+SettingContract.Columns.TASK_CFGVALUE+") VALUES ('SaleDate','"+SaleDate+"')");

            }

            @Override
            public void onFailure(Call<List<SettingModel>> call, Throwable t) {

            }
        });


        //Get ShopType
        db.execSQL("DELETE FROM "+ ShopTypeContract.TABLE_NAME);
        Call<List<ShopTypeModel>> callShopType = apiPath.getShopType();

        callShopType.enqueue(new Callback<List<ShopTypeModel>>() {
            @Override
            public void onResponse(Call<List<ShopTypeModel>> call, Response<List<ShopTypeModel>> response) {
                List<ShopTypeModel> shopTypeModels = response.body();
                Log.d(TAG, "onResponse: " + shopTypeModels);
                for (ShopTypeModel shopTypeModel : shopTypeModels)
                {
                    database.insertShopType(shopTypeModel);
                }

            }

            @Override
            public void onFailure(Call<List<ShopTypeModel>> call, Throwable t) {


            }
        });


        //Get Product
        db.execSQL("DELETE FROM "+ ProductContract.TABLE_NAME);


        Call<List<ProductModel>> callProduct = apiPath.getProduct();
        callProduct.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {

                List<ProductModel> productModels = response.body();
                Log.d(TAG, "onResponse: " + productModels);
                for (ProductModel productModel : productModels)
                {
                    database.insertProduct(productModel);
                }


            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });

        //Get Cause
        db.execSQL("DELETE FROM "+ CauseContract.TABLE_NAME);
        Call<List<CauseModel>> callCause =  apiPath.getCause();
        callCause.enqueue(new Callback<List<CauseModel>>() {
            @Override
            public void onResponse(Call<List<CauseModel>> call, Response<List<CauseModel>> response) {
                List<CauseModel> causeModels = response.body();
                Log.d(TAG, "onResponse: " + causeModels);

                for (CauseModel causeModel : causeModels)
                {
                    database.insertCause(causeModel);
                }

            }

            @Override
            public void onFailure(Call<List<CauseModel>> call, Throwable t) {

            }
        });

        //Get BranchWarehouse
        db.execSQL("DELETE FROM "+ BranchWarehouseContract.TABLE_NAME);
        Call<List<BranchWarehouseModel>> callBranchWarehouse =  apiPath.getBranchWarehouse();
        callBranchWarehouse.enqueue(new Callback<List<BranchWarehouseModel>>() {
            @Override
            public void onResponse(Call<List<BranchWarehouseModel>> call, Response<List<BranchWarehouseModel>> response) {
                List<BranchWarehouseModel> branchWarehouseModels = response.body();
                Log.d(TAG, "onResponse: "+ branchWarehouseModels);

                for (BranchWarehouseModel branchWarehouseModel : branchWarehouseModels)
                {
                    database.insertBranchWarehouse(branchWarehouseModel);
                }

            }

            @Override
            public void onFailure(Call<List<BranchWarehouseModel>> call, Throwable t) {

            }
        });

        //Get Province and Area and District
       db.execSQL("DELETE FROM "+ PDAContract.TABLE_NAME);
       Call<List<PDAModel>> callPDA = apiPath.getPDA();
       callPDA.enqueue(new Callback<List<PDAModel>>() {
           @Override
           public void onResponse(Call<List<PDAModel>> call, Response<List<PDAModel>> response) {

               List<PDAModel> pdaModels = response.body();
               Log.d(TAG, "onResponse: "+pdaModels);

               for (PDAModel pdaModel : pdaModels)
               {
                   database.insertPDA(pdaModel);
               }
           }

           @Override
           public void onFailure(Call<List<PDAModel>> call, Throwable t) {

           }
       });

                long stopTime = System.nanoTime();
                String time = String.valueOf(stopTime - startTime);
                double ms = (stopTime - startTime) * 1.0 * Math.pow(10,-6);
                Log.d(TAG, "GetRefresh: "+startTime);
                Log.d(TAG, "GetRefresh: "+stopTime);
                Log.d(TAG, "GetRefresh: " +ms+" ms");

                progress.dismiss();


            }
        });
        thread.start();
        DialogSuccess();

   }

   void DialogSuccess(){

       android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder( activity );
       builder.setCancelable( false );
       builder.setIcon( R.drawable.ic_alert );
       builder.setTitle( "Success" );
       builder.setMessage( "Reset ข้อมูลเสร็จ" );
       builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

               Intent intent = new Intent(activity,LoginActivity.class);
               startActivity(intent);

           }
       } );
       builder.show();
   }

}
