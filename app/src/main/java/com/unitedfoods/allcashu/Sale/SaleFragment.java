package com.unitedfoods.allcashu.Sale;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedfoods.allcashu.CustomerContract;
import com.unitedfoods.allcashu.DB;
import com.unitedfoods.allcashu.Home.Home;
import com.unitedfoods.allcashu.POMasterContract;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.Sale.ShopList.ShopAdapter;
import com.unitedfoods.allcashu.Sale.ShopList.ShopModel;
import com.unitedfoods.allcashu.SaleAreaContract;
import com.unitedfoods.allcashu.SettingContract;
import com.unitedfoods.allcashu.Uitls.SalAreaSpinners.SalAreaAdapter;
import com.unitedfoods.allcashu.Uitls.SalAreaSpinners.SaleAreaSpinnerModel;
import com.unitedfoods.allcashu.VisitContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class SaleFragment extends Fragment {
    private static final String TAG = "SaleFragment";
    private DB database = DB.getInstance(getActivity());
    Spinner spinSaleArea;
    private ArrayList<SaleAreaSpinnerModel> areaModelArrayList;
    ArrayList<ShopModel> arrayList;
    ShopAdapter shopAdapter;
    RecyclerView recyclerSales;
    TextView SALEDATE;
    String area;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("เลือกร้านค้า");
        return inflater.inflate(R.layout.fragment_sales,container,false);

    }

    @Override
    public void onStart() {
        super.onStart();

        spinSaleArea = getView().findViewById(R.id.spinSaleArea);
        recyclerSales = getView().findViewById(R.id.recyclerSales);
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
            Date dateSale = new Date();
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
            int d = Integer.parseInt(nowTime) - 2;

            if (d >= 2 & d < 25){
                if (d == 24){
                    spinSaleArea.setSelection(d-1);
                }
                else {
                    spinSaleArea.setSelection(d);
                }
            }
            else if (d > 25){
                spinSaleArea.setSelection(23);

            }
            else {
                spinSaleArea.setSelection(0);
            }
        }
        else {

            Toast toast  = Toast.makeText(getContext(),"โปรดเตรียมข้อมูล",Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(getActivity(),Home.class);
            intent.putExtra("page","Prepare");
            startActivity(intent);
        }


        spinSaleArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SaleAreaSpinnerModel saleAreaSpinnerModel  = (SaleAreaSpinnerModel) parent.getItemAtPosition(position);
                area = saleAreaSpinnerModel.getSalAreaID();
                arrayList = CustomersBySalArea(area);
                shopAdapter = new ShopAdapter(arrayList,getContext());
                recyclerSales.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerSales.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
                recyclerSales.setAdapter(shopAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

        SalAreaAdapter salAreaAdapter = new SalAreaAdapter(Objects.requireNonNull(getActivity()),areaModelArrayList);
        spinSaleArea.setAdapter(salAreaAdapter);

    }

    private  ArrayList<ShopModel> CustomersBySalArea(String area){

        ArrayList<ShopModel> List = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();


        String SQL_Customer = "SELECT "+CustomerContract.TABLE_NAME+"."+CustomerContract.Columns.TASK_CUSTOMERID+",\n"+
                CustomerContract.TABLE_NAME+"."+CustomerContract.Columns.TASK_CustName+",\n"+
                CustomerContract.TABLE_NAME+"."+CustomerContract.Columns.TASK_Tel+",\n"+
                CustomerContract.TABLE_NAME+"."+CustomerContract.Columns.TASK_BillTo+",\n"+
                VisitContract.TABLE_NAME+"."+VisitContract.Columns.TASK_VISITID+",\n"+
                VisitContract.TABLE_NAME+"."+VisitContract.Columns.TASK_CAUSEID+",\n"+
                POMasterContract.TABLE_NAME+"."+POMasterContract.Columns.TASK_TOTALDUE+",\n"+
                POMasterContract.TABLE_NAME+"."+POMasterContract.Columns.TASK_DOCNO+",\n"+
                POMasterContract.TABLE_NAME+"."+POMasterContract.Columns.TASK_DOCSTATUS+",\n"+
                VisitContract.TABLE_NAME+"."+VisitContract.Columns.TASK_VISITSTATUS+
                " FROM "+CustomerContract.TABLE_NAME+
                "\n LEFT OUTER JOIN "+POMasterContract.TABLE_NAME+" ON "
                + POMasterContract.TABLE_NAME+"."+POMasterContract.Columns.TASK_CUSTOMERID+" = "+CustomerContract.TABLE_NAME+"."+CustomerContract.Columns.TASK_CUSTOMERID+
                " and "+POMasterContract.TABLE_NAME+"."+POMasterContract.Columns.TASK_CRDATE+" = (SELECT MAX("+POMasterContract.Columns.TASK_CRDATE+") FROM "+POMasterContract.TABLE_NAME+")\n"+
                " LEFT OUTER JOIN "+VisitContract.TABLE_NAME+" ON "
                +CustomerContract.TABLE_NAME+"."+CustomerContract.Columns.TASK_CUSTOMERID+" = "+VisitContract.TABLE_NAME+"."+VisitContract.Columns.TASK_CUSTOMERID+
                "\n WHERE "+CustomerContract.TABLE_NAME+"."+CustomerContract.Columns.TASK_SALAREAID+" = '"+area+"'"+
                "\n ORDER BY cast ("+CustomerContract.TABLE_NAME+"."+CustomerContract.Columns.TASK_CustSeq+" as Int) ASC";

        Log.d(TAG, "CustomersBySalArea: "+SQL_Customer);

        Cursor cursor = db.rawQuery(SQL_Customer, null);
        while (cursor.moveToNext())
        {
            ShopModel shopModel = new ShopModel();
            shopModel.setCustomerID(cursor.getString(0));
            shopModel.setCustomerName(cursor.getString(1));
            shopModel.setTel(cursor.getString(2));
            shopModel.setBillTo(cursor.getString(3));
            shopModel.setVisitID(cursor.getString(4));
            shopModel.setCauseID(cursor.getString(5));
            shopModel.setTotalDue(cursor.getString(6));
            shopModel.setDocNo(cursor.getString(7));
            shopModel.setDocStatus(cursor.getString(8));
            shopModel.setVisitStatus(cursor.getString(9));
            List.add(shopModel);

        }

        return List;
    }
}
