package com.unitedfoods.allcashu.Prepare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.unitedfoods.allcashu.ArCustomerShelfContract;
import com.unitedfoods.allcashu.BranchWarehouseContract;
import com.unitedfoods.allcashu.CustomerContract;
import com.unitedfoods.allcashu.DB;
import com.unitedfoods.allcashu.Model.ArCustomerShelfModel;
import com.unitedfoods.allcashu.Model.CustomerModel;
import com.unitedfoods.allcashu.Model.MessageModel;
import com.unitedfoods.allcashu.Model.PODetailModel;
import com.unitedfoods.allcashu.Model.SaleAreaModel;
import com.unitedfoods.allcashu.Model.StockModel;
import com.unitedfoods.allcashu.PODetailContract;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.SaleAreaContract;
import com.unitedfoods.allcashu.SettingContract;
import com.unitedfoods.allcashu.StockContract;
import com.unitedfoods.allcashu.Uitls.ApiPath;
import com.unitedfoods.allcashu.Uitls.GetWebService;
import com.unitedfoods.allcashu.Uitls.LoadingDialog;
import com.unitedfoods.allcashu.Uitls.MyDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class PrepareFragment extends Fragment {
    private static final String TAG = "PrepareFragment";
    DB database;
    TextView Title;
    DatePicker datePicker;
    Button btn_prepare;
    String WHID,TitleName,FristName;
    Toast toast;
    ApiPath apiPath;
    AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("เตรียมข้อมูล");
        View view = inflater.inflate(R.layout.fragment_prepare,container,false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        datePicker = getView().findViewById(R.id.datepicker);
        btn_prepare = getView().findViewById(R.id.btn_prepare);
        Title = getView().findViewById(R.id.Title);

        database = DB.getInstance(getActivity());
        SQLiteDatabase db = database.getReadableDatabase();


        String SQL = "SELECT Setting.cfgValue, BranchWarehouse.TitleName,BranchWarehouse.FirstName FROM Setting" +
                " LEFT JOIN BranchWarehouse on Setting.cfgValue =  BranchWarehouse.WHID" +
                " Where Setting.cfgName = 'WHID' ";
        Cursor cursor = db.rawQuery(SQL,null);
        while (cursor.moveToNext()){
            WHID = cursor.getString(0);
            TitleName = cursor.getString(1);
            FristName = cursor.getString(2);

        }
        Title.setText(WHID+" "+TitleName+" "+FristName);
        btn_prepare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int date = datePicker.getDayOfMonth();
                int month = datePicker.getMonth()+1;
                int year = datePicker.getYear();

                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
                String nowTime = sdf.format(now);
                final String datetime  = year + "-" +month+ "-" +date;
                Toast.makeText(getActivity(),datetime,Toast.LENGTH_LONG).show();
                String Serial = Build.SERIAL;
                Toast.makeText(getActivity(),Serial,Toast.LENGTH_LONG).show();


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.layout_dialog_refresh,null);

                final RadioButton radioButton_IN = mView.findViewById(R.id.radio_IN);
                final RadioButton radioButton_OUT = mView.findViewById(R.id.radio_OUT);

                radioButton_IN.setChecked(true);
                radioButton_IN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//
//                        final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
//                        loadingDialog.startLoadingDialog();
                        radioButton_IN.setChecked(true);
                        radioButton_OUT.setChecked(false);
                        toast  = Toast.makeText(getActivity(),"In",Toast.LENGTH_SHORT);
                        toast.show();

                        try {

                            Prepare(true,datetime,WHID);


                            Log.d(TAG, "onClick: Success");

                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                loadingDialog.dismissDialog();
//                                MyDialog myDialog = new MyDialog(getActivity());
//                                myDialog.normalDialog("Success","เตรียมข้อมูลสำเร็จ");
//                            }
//                        },5000);


                    }
                });

                radioButton_OUT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
//                        loadingDialog.startLoadingDialog();
                        radioButton_OUT.setChecked(true);
                        radioButton_IN.setChecked(false);
                        toast = Toast.makeText(getActivity(),"Out",Toast.LENGTH_SHORT);
                        toast.show();


                        try {

                            Prepare(false,datetime,WHID);

                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                loadingDialog.dismissDialog();
//                                MyDialog myDialog = new MyDialog(getActivity());
//                                myDialog.normalDialog("Success","เตรียมข้อมูลสำเร็จ");
//                            }
//                        },5000);

                    }
                });

                mBuilder.setView(mView);
                dialog = mBuilder.create();
                dialog.show();

            }
        });

    }

    private void Prepare(boolean stauts,String date,String WHID) throws InterruptedException, ExecutionException, JSONException {
        dialog.dismiss();

        if (stauts == true){

            SQLiteDatabase db = database.getReadableDatabase();

            String ip = "SELECT * FROM "+ SettingContract.TABLE_NAME
                    + " Where "+SettingContract.Columns.TASK_CFGNAME
                    + " = 'WebServiceHost'";
            Cursor cursor = db.rawQuery(ip,null);

            while (cursor.moveToNext()){
                ip = cursor.getString(1);
            }
            Log.d(TAG, "Refresh: "+ip);

            GetPrepare(ip,date,WHID);


        }
        else {

            SQLiteDatabase db = database.getReadableDatabase();

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
            GetPrepare(ip,date,WHID);

        }

    }

    private void GetPrepare(String ip, final String date, final String Whid) throws ExecutionException, InterruptedException, JSONException {


        final long startTime = System.nanoTime();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+ip+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiPath = retrofit.create(ApiPath.class);

        final GetWebService SendData = new GetWebService(getActivity());
        String URL = "http://"+ip+"/CU/SendData/SendData?WHID="+Whid+"&Date="+date;
        SendData.execute(URL);
        String check = SendData.get();

        JSONObject JO_Check = new JSONObject(check);

        String id = JO_Check.getString("id");
        final String message = JO_Check.getString("messageText");


        if (id.equals("1"))
        {

            MyDialog myDialog = new MyDialog(getActivity());
            myDialog.normalDialog("แจ้งเตือน",message);

        }
        else {

            final ProgressDialog progress;
            progress =  new ProgressDialog(getActivity());
            progress.setTitle("เตรียมข้อมูล");
            progress.setMessage("Please wait...");
            progress.show();

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {


            database.UpdateSaleDate(date);
            final SQLiteDatabase db = database.getWritableDatabase();


            //GetCustomer
            db.execSQL("DELETE FROM "+ CustomerContract.TABLE_NAME);
            Call<List<CustomerModel>> callCustomer = apiPath.getCustomer(Whid);
            callCustomer.enqueue(new Callback<List<CustomerModel>>() {
                @Override
                public void onResponse(Call<List<CustomerModel>> call, Response<List<CustomerModel>> response) {

                    List<CustomerModel> customerModels = response.body();
                    Log.d(TAG, "onResponse: "+customerModels);
                    for (CustomerModel customerModel : customerModels)
                    {
                        database.insertCustomer(customerModel);
                    }
                }

                @Override
                public void onFailure(Call<List<CustomerModel>> call, Throwable t) {

                }
            });

            //GetSaleArea
            db.execSQL("DELETE FROM "+ SaleAreaContract.TABLE_NAME);
            Call<List<SaleAreaModel>> callSaleArea = apiPath.getSaleArea(Whid);
            callSaleArea.enqueue(new Callback<List<SaleAreaModel>>() {
                @Override
                public void onResponse(Call<List<SaleAreaModel>> call, Response<List<SaleAreaModel>> response) {

                    List<SaleAreaModel> saleAreaModels = response.body();
                    for (SaleAreaModel saleAreaModel : saleAreaModels)
                    {
                        database.insertSaleArea(saleAreaModel);
                    }
                }

                @Override
                public void onFailure(Call<List<SaleAreaModel>> call, Throwable t) {

                }
            });


            //GetArCustomerShelf
            db.execSQL("DELETE FROM "+ ArCustomerShelfContract.TABLE_NAME);
            Call<List<ArCustomerShelfModel>> callArCustomerShelf = apiPath.getArCustomerShelf(Whid);
            callArCustomerShelf.enqueue(new Callback<List<ArCustomerShelfModel>>() {
                @Override
                public void onResponse(Call<List<ArCustomerShelfModel>> call, Response<List<ArCustomerShelfModel>> response) {

                    List<ArCustomerShelfModel> arCustomerShelfModels = response.body();
                    for ( ArCustomerShelfModel arCustomerShelfModel : arCustomerShelfModels)
                    {
                        database.insertArCustomerShelf(arCustomerShelfModel);
                    }
                }

                @Override
                public void onFailure(Call<List<ArCustomerShelfModel>> call, Throwable t) {

                }
            });

            //GetStock
            db.execSQL("DELETE FROM "+ StockContract.TABLE_NAME);
            Call<List<StockModel>> callStock = apiPath.getStock(Whid,date);
            callStock.enqueue(new Callback<List<StockModel>>() {
                @Override
                public void onResponse(Call<List<StockModel>> call, Response<List<StockModel>> response) {

                    List<StockModel> stockModels = response.body();

                    for (StockModel stockModel : stockModels)
                    {
                        database.insertStock(stockModel);
                    }
                }

                @Override
                public void onFailure(Call<List<StockModel>> call, Throwable t) {

                }
            });


            //GetTwolastbill
            db.execSQL("DELETE FROM "+ PODetailContract.TABLE_NAME);
            Call<List<PODetailModel>> callTwolastbill = apiPath.getTwolastbil(Whid);
            callTwolastbill.enqueue(new Callback<List<PODetailModel>>() {
                @Override
                public void onResponse(Call<List<PODetailModel>> call, Response<List<PODetailModel>> response) {

                    List<PODetailModel> poDetailModels = response.body();

                    for (PODetailModel poDetailModel : poDetailModels)
                    {
                        database.insertPODetail(poDetailModel);
                    }
                }

                @Override
                public void onFailure(Call<List<PODetailModel>> call, Throwable t) {

                }
            });




            long stopTime = System.nanoTime();
            String time = String.valueOf(stopTime - startTime);
            Double ms = (stopTime - startTime) * 1.0 * Math.pow(10,-6);
            Log.d(TAG, "GetPrepare: "+ms+" ms");
            progress.dismiss();

                }
            });
            thread.start();
            MyDialog myDialog = new MyDialog(getActivity());
            myDialog.normalDialog("แจ้งเตือน",message);
        }


    }

}
