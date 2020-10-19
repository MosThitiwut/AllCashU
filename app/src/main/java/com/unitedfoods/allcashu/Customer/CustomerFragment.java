package com.unitedfoods.allcashu.Customer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedfoods.allcashu.Customer.CustomerList.CustomerAdapter;
import com.unitedfoods.allcashu.CustomerContract;
import com.unitedfoods.allcashu.DB;
import com.unitedfoods.allcashu.Home.Home;
import com.unitedfoods.allcashu.Model.CustomerModel;
import com.unitedfoods.allcashu.Prepare.PrepareFragment;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.SaleAreaContract;
import com.unitedfoods.allcashu.SettingContract;
import com.unitedfoods.allcashu.Uitls.SalAreaSpinners.SalAreaAdapter;
import com.unitedfoods.allcashu.Uitls.SalAreaSpinners.SaleAreaSpinnerModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CustomerFragment extends Fragment {
    private static final String TAG = "CustomerFragment";
    public static CustomerAdapter customerAdapter;
    private DB database = DB.getInstance(getActivity());
    private ArrayList<SaleAreaSpinnerModel> areaModelArrayList;
    private ArrayList<CustomerModel> arrayList;
    private Spinner spinSaleArea;
    public static String area;
    private RecyclerView recyclerCustomer;
    TextView SALEDATE;
//    CustomerAdapter customerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("ข้อมูลร้านค้า");
        return inflater.inflate(R.layout.fragment_customer,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        spinSaleArea = getView().findViewById(R.id.spinSaleArea);
        recyclerCustomer = getView().findViewById(R.id.recyclerCustomer);
        SALEDATE = getView().findViewById(R.id.textView3);
        SalAreaSpinner();
        String SaleDate = null;
        String Date = null;
            SQLiteDatabase db = database.getReadableDatabase();
            String SQL_SaleDate = "SELECT "+ SettingContract.Columns.TASK_CFGVALUE+
                                    " FROM "+SettingContract.TABLE_NAME+
                                    " WHERE "+SettingContract.Columns.TASK_CFGNAME+" ='SaleDate'";

            Cursor cursor = db.rawQuery(SQL_SaleDate,null);
                while (cursor.moveToNext())
                {
                    SaleDate = cursor.getString(0);
                }
            if (!SaleDate.equals("null"))
            {


                String pattern = "dd MMMM yyyy";

                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                Date dateSale = null;
                try {
                    dateSale = format2.parse(SaleDate);

                    format2 = new SimpleDateFormat(pattern);
                    Date = format2.format(dateSale);

                    for (Locale locale: Locale.getAvailableLocales())
                    {
                        locale = new Locale("TH");
                        Locale.setDefault(locale);
                        String localeFmt = new SimpleDateFormat(pattern,locale).format(dateSale);
                        if (localeFmt.equals(Date)){

                            SALEDATE.setText(localeFmt);

                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                SimpleDateFormat sdf = new SimpleDateFormat("dd");
                String nowTime = sdf.format(dateSale);
                int d = Integer.valueOf(nowTime) - 2;

                if (d >= 2 & d < 25){

                    if (d == 24){
                        spinSaleArea.setSelection(d-1);
                    }
                    else {
                        spinSaleArea.setSelection(d);
                    }

                }
                else if (d >= 25){
                    spinSaleArea.setSelection(23);

                }
                else {
                    spinSaleArea.setSelection(0);
                }
            }
            else {
                Toast toast  = Toast.makeText(getContext(),"โปรดเตรียมข้อมูล",Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getActivity(), Home.class);
                intent.putExtra("page","Prepare");
                startActivity(intent);
            }


        spinSaleArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SaleAreaSpinnerModel saleAreaSpinnerModel  = (SaleAreaSpinnerModel) parent.getItemAtPosition(position);
                area = saleAreaSpinnerModel.getSalAreaID();
                arrayList = CustomersBySalArea(area);
                customerAdapter = new CustomerAdapter(arrayList,getContext());
                recyclerCustomer.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerCustomer.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
                recyclerCustomer.setAdapter(customerAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void SalAreaSpinner(){

        SQLiteDatabase db = database.getReadableDatabase();
        String SQL_SaleArea =  "SELECT * FROM "+ SaleAreaContract.TABLE_NAME;
        Cursor cursor = db.rawQuery(SQL_SaleArea,null);
        areaModelArrayList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            areaModelArrayList.add(new SaleAreaSpinnerModel(cursor.getString(1),cursor.getString(2),cursor.getString(4)));
        }

        SalAreaAdapter salAreaAdapter = new SalAreaAdapter(getActivity(),areaModelArrayList);
        spinSaleArea.setAdapter(salAreaAdapter);

    }

    private ArrayList<CustomerModel> CustomersBySalArea(String area){

        ArrayList<CustomerModel> List = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String SQL_Customer = "SELECT "+
                                CustomerContract.Columns.TASK_CUSTOMERID+","
                              + CustomerContract.Columns.TASK_CustName+","
                              + CustomerContract.Columns.TASK_BillTo+","
                              + CustomerContract.Columns.TASK_SALAREAID+","
                              + CustomerContract.Columns.TASK_Latitude+","
                              + CustomerContract.Columns.TASK_Longitude
                              +" FROM "+ CustomerContract.TABLE_NAME
                              +" Where "+CustomerContract.Columns.TASK_SALAREAID+" = '"+area
                              +"' ORDER BY Cast("+CustomerContract.Columns.TASK_CustSeq+" as Int)  ASC";
        Cursor cursor = db.rawQuery(SQL_Customer,null);
        while (cursor.moveToNext())
        {
            CustomerModel customerModel = new CustomerModel();
            customerModel.setCustomerID(cursor.getString(0));
            customerModel.setCustName(cursor.getString(1));
            customerModel.setBillTo(cursor.getString(2));
            customerModel.setSalAreaID(cursor.getString(3));
            customerModel.setLatitude(cursor.getString(4));
            customerModel.setLongitude(cursor.getString(5));
            List.add(customerModel);
        }

        return List;

    }
}
