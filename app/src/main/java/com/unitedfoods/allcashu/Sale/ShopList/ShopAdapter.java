package com.unitedfoods.allcashu.Sale.ShopList;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedfoods.allcashu.CauseContract;
import com.unitedfoods.allcashu.DB;
import com.unitedfoods.allcashu.Model.POMasterModel;
import com.unitedfoods.allcashu.Model.VisitModel;
import com.unitedfoods.allcashu.Model.VisitStockModel;
import com.unitedfoods.allcashu.POMasterContract;
import com.unitedfoods.allcashu.ProductContract;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.Sale.Sell.SellActivity;
import com.unitedfoods.allcashu.SettingContract;
import com.unitedfoods.allcashu.Uitls.CauseSpinners.CauseAdapter;
import com.unitedfoods.allcashu.Uitls.CauseSpinners.CauseSpinnerModel;
import com.unitedfoods.allcashu.Uitls.GPSTracker;
import com.unitedfoods.allcashu.VisitContract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopHolder>{
    private static final String TAG = "ShopAdapter";
    Context context;
    ArrayList<ShopModel> shopModels;
    ArrayList<CauseSpinnerModel> causeSpinnerModelArrayList;
    String Cause;

    public ShopAdapter(ArrayList<ShopModel> shopModels, Context context){

        this.context = context;
        this.shopModels = shopModels;
    }
    @NonNull
    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sales_recy_layout, parent, false);
        ShopHolder shopHolder = new ShopHolder(view);

        return shopHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopHolder holder, int position) {

        final ShopModel shopModel = shopModels.get(position);
        holder.CustomerName.setText(shopModel.getCustomerName());
        holder.Tel.setText(shopModel.getTel());
        holder.BillTO.setText(shopModel.getBillTo());
        final GPSTracker gpsTracker = new GPSTracker(context);
        CauseSpinner(holder.spinCause);
        if (shopModel.getTotalDue() == null || Integer.parseInt(shopModel.getTotalDue()) == 0)
        {
            holder.Total.setText("0");
        }
        else {
            holder.Total.setText(shopModel.getTotalDue());
        }

//        if (shopModel.getDocStatus() != null)
//        {
//            if (shopModel.getDocStatus().equals("4"))
//            {
//                holder.btn_Sales.setVisibility(View.INVISIBLE);
//            }
//            if (shopModel.getDocStatus().equals("5"))
//            {
//                holder.btn_Sales.setVisibility(View.VISIBLE);
//            }
//        }
//        else {
//
//            Log.d(TAG, "onBindViewHolder: Null");
//
//        }

        if (shopModel.getCauseID() == null || shopModel.getCauseID().equals(" "))
        {
           holder.spinCause.setSelection(0);
        }
        else {
            for (int i = 0; i < causeSpinnerModelArrayList.size(); i++)
            {
                if (shopModel.getCauseID().equals(causeSpinnerModelArrayList.get(i).getCauseID()))
                {
                    holder.spinCause.setSelection(i);
                    break;
                }
            }
        }

        holder.spinCause.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CauseSpinnerModel causeSpinnerModel = (CauseSpinnerModel) parent.getItemAtPosition(position);
                shopModel.setCauseID(causeSpinnerModel.CauseID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (shopModel.getVisitStatus() == null||shopModel.getVisitStatus().equals("0"))
        {
            holder.rdNotBuy.setChecked(true);
            holder.rdBuy.setChecked(false);
            holder.btn_Sales.setText("เช็คสต๊อก");
            holder.btn_Sales.setBackgroundColor(Color.parseColor("#F59026"));

            if (String.valueOf(shopModel.getVisitStatus()).equals("0"))
            {
                holder.star_mark.setVisibility(View.INVISIBLE);
            }
            else{
                holder.star_mark.setVisibility(View.VISIBLE);
            }

        }
        else {
            holder.rdBuy.setChecked(true);
            holder.rdNotBuy.setChecked(false);
            holder.spinCause.setEnabled(false);
            holder.btn_Sales.setText("ขาย");
            holder.star_mark.setVisibility(View.INVISIBLE);
            holder.btn_Sales.setBackgroundColor(Color.parseColor("#F52626"));
        }

        holder.rdNotBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rdBuy.setChecked(false);
                holder.rdNotBuy.setChecked(true);
                holder.spinCause.setEnabled(true);
                shopModel.setVisitID("1");
            }
        });

        holder.rdBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rdBuy.setChecked(true);
                holder.rdNotBuy.setChecked(false);
                holder.spinCause.setEnabled(false);
                holder.spinCause.setSelection(0);
                shopModel.setVisitID("0");

            }
        });

        holder.btn_saveVisit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // Check DocStatus
                if (shopModel.getDocStatus() != null) {

                    // if DocStatus == 4 Can't Sell
                    if (shopModel.getDocStatus().equals("4"))
                    {
                       // holder.btn_Sales.setVisibility(View.INVISIBLE);
                    }
                    // if DocStatus == 5 Can Sell Or Check Stock
                    if (shopModel.getDocStatus().equals("5"))
                    {
//                        holder.btn_Sales.setVisibility(View.VISIBLE);
                        DB database = DB.getInstance(context);
                        SQLiteDatabase db = database.getReadableDatabase();
                        String SQL_WHID = "SELECT * FROM " + SettingContract.TABLE_NAME + " WHERE " + SettingContract.Columns.TASK_CFGNAME + " = 'WHID'";
                        Cursor cursor = db.rawQuery(SQL_WHID, null);
                        String WHID = "";
                        while (cursor.moveToNext()) {
                            WHID = cursor.getString(1);
                        }
                        Log.d(TAG, "onClick: " + WHID.substring(4, 6));
                        VisitModel visitModel = new VisitModel();
                        visitModel.setBranchID(WHID.substring(0, 3));
                        visitModel.setWHID(WHID);
                        visitModel.setCustomerID(shopModel.getCustomerID());
                        visitModel.setVisitType("VS");
                        visitModel.setVisitDate(Calendar.getInstance().getTime().toString());
                        if (holder.rdBuy.isChecked()) {
                            visitModel.setVisitStatus("1");
                            visitModel.setCauseID("0");
                            holder.btn_Sales.setText("ขาย");
                            holder.btn_Sales.setBackgroundColor(Color.parseColor("#F52626"));
                        } else {
                            visitModel.setVisitStatus("0");
                            visitModel.setCauseID(shopModel.getCauseID());
                            holder.btn_Sales.setText("เช็คสต๊อก");
                            holder.btn_Sales.setBackgroundColor(Color.parseColor("#F59026"));
                        }
                        visitModel.setCauseRemark("TABLET");
                        visitModel.setLatitude(String.valueOf(gpsTracker.getLatitude()));
                        visitModel.setLongitude(String.valueOf(gpsTracker.getLongitude()));
                        database.VisitShop(visitModel);
                        Toast.makeText(context, "บันทึกการเยี่ยมร้านค้าสำเร็จ", Toast.LENGTH_LONG).show();
                    }

                }
                    // Gen Visit
                else
                    {
                            holder.star_mark.setVisibility(View.INVISIBLE);
                            DB database = DB.getInstance(context);
                            SQLiteDatabase db = database.getReadableDatabase();
                            String SQL_WHID = "SELECT * FROM " + SettingContract.TABLE_NAME + " WHERE " + SettingContract.Columns.TASK_CFGNAME + " = 'WHID'";
                            Cursor cursor = db.rawQuery(SQL_WHID, null);
                            String WHID = "";
                            while (cursor.moveToNext()) {
                                WHID = cursor.getString(1);
                            }
                            Log.d(TAG, "onClick: " + WHID.substring(4, 6));
                            VisitModel visitModel = new VisitModel();
                            visitModel.setBranchID(WHID.substring(0, 3));
                            visitModel.setWHID(WHID);
                            visitModel.setCustomerID(shopModel.getCustomerID());
                            visitModel.setVisitType("VS");
                            visitModel.setVisitDate(Calendar.getInstance().getTime().toString());
                            if (holder.rdBuy.isChecked()) {
                                shopModel.setVisitStatus("1");
                                visitModel.setVisitStatus("1");
                                visitModel.setCauseID("0");
                                holder.btn_Sales.setText("ขาย");
                                holder.btn_Sales.setBackgroundColor(Color.parseColor("#F52626"));
                            } else {
                                shopModel.setVisitStatus("0");
                                visitModel.setVisitStatus("0");
                                visitModel.setCauseID(shopModel.getCauseID());
                                holder.btn_Sales.setText("เช็คสต๊อก");
                                holder.btn_Sales.setBackgroundColor(Color.parseColor("#F59026"));
                            }
                            visitModel.setCauseRemark("TABLET");
                            visitModel.setLatitude(String.valueOf(gpsTracker.getLatitude()));
                            visitModel.setLongitude(String.valueOf(gpsTracker.getLongitude()));
                            database.VisitShop(visitModel);
                            Toast.makeText(context, "บันทึกการเยี่ยมร้านค้าสำเร็จ", Toast.LENGTH_LONG).show();
                }

            }
        });
        holder.btn_Sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (shopModel.getDocStatus() != null)
                {
                    if (shopModel.getDocStatus().equals("5"))
                    {
                        Log.d(TAG, "onClick: HAS");

                        if(shopModel.getVisitID().equals("0"))
                        {
                            //Check Visit Stock

                        }
                        else if (shopModel.getVisitID().equals("1"))
                        {
                            //Check Visit Stock and Gen New IV


                        }
                    }
                    else if(shopModel.getDocStatus().equals("4"))
                    {
                        Toast.makeText(context,"มีการขายแล้ว",Toast.LENGTH_LONG).show();
                        //Return Null
                    }
                }

                else {

                    //Gen New IV
                    DB database = DB.getInstance(context);
                    SQLiteDatabase db =  database.getReadableDatabase();

                    //TODO GetWHID
                    String SQL_WHID = "SELECT * FROM " + SettingContract.TABLE_NAME + " WHERE " + SettingContract.Columns.TASK_CFGNAME + " = 'WHID'";
                    Cursor WH = db.rawQuery(SQL_WHID, null);
                    String WHID = "";

                    while (WH.moveToNext()) {
                        WHID = WH.getString(1);

                    }

                    //TODO GenDocNo
                    String Check = "SELECT * FROM "+ POMasterContract.TABLE_NAME;
                    Cursor GenDocNo = db.rawQuery(Check, null );
                    String DocNo = "";
                    if (GenDocNo.getCount() > 0)
                    {
                        String gen = "SELECT  MAX("+ POMasterContract.Columns.TASK_DOCNO+")+1 as DocNo FROM "+POMasterContract.TABLE_NAME;
                        Cursor data = db.rawQuery(gen, null );
                        while (data.moveToNext())
                        {
                            DocNo = data.getString(0);
                        }

                    }
                    else {

                        int year = Calendar.getInstance().get(Calendar.YEAR)+543;
                        int mouth = Calendar.getInstance().get(Calendar.MONTH)+1;
                        int day = Calendar.getInstance().get(Calendar.DATE);
                        DocNo = WHID.substring(0,3)+WHID.substring(4,6)+String.valueOf(year).substring(2,4)+mouth+day+"001";


                    }

                    //TODO GetVisitID
                    String SQL_Visit = "SELECT "+ VisitContract.Columns.TASK_VISITID +" FROM "+VisitContract.TABLE_NAME+" WHERE "+VisitContract.Columns.TASK_CUSTOMERID+"=?";
                    Cursor cursor = db.rawQuery(SQL_Visit,new String[]{shopModel.getCustomerID()});
                    String VisitID = "";
                    while (cursor.moveToNext())
                    {
                        VisitID = cursor.getString(0);

                    }

                    //TODO Get Product
                    String SQL_Product = "select distinct "+ ProductContract.Columns.TASK_PRODUCTID +" from "+ProductContract.TABLE_NAME+"  where "+ProductContract.Columns.TASK_PRODUCTID+" between '3%' and '5%'";
                    Cursor product = db.rawQuery(SQL_Product,null);

                    if (shopModel.getVisitStatus() != null) {
                        if (shopModel.getVisitStatus().equals("0")) {
                            while (product.moveToNext()) {
                                VisitStockModel visitStockModel = new VisitStockModel();
                                visitStockModel.setProductID(product.getString(0));
                                visitStockModel.setVisitID(VisitID);
                                visitStockModel.setCustomerID(shopModel.getCustomerID());
                                visitStockModel.setBranchID(WHID.substring(0, 3));
                                visitStockModel.setWHID(WHID);
                                visitStockModel.setDocNo(DocNo);
                                visitStockModel.setVisitType("VS");
                                Date datetime = Calendar.getInstance().getTime();
                                visitStockModel.setVisitDate(datetime.toString());
                                visitStockModel.setStockQty(0);
                                //TODO Create VisitStock
                                database.CreateVisitStock(visitStockModel);
                            }

                            Intent intent = new Intent(context, SellActivity.class);
                            intent.putExtra("DocNo",DocNo);
                            intent.putExtra("CustomerID",shopModel.getCustomerID());
                            context.startActivity(intent);


                        } else {

                            while (product.moveToNext()) {
                                VisitStockModel visitStockModel = new VisitStockModel();
                                visitStockModel.setProductID(product.getString(0));
                                visitStockModel.setVisitID(VisitID);
                                visitStockModel.setCustomerID(shopModel.getCustomerID());
                                visitStockModel.setBranchID(WHID.substring(0, 3));
                                visitStockModel.setDocNo(DocNo);
                                visitStockModel.setWHID(WHID);
                                visitStockModel.setVisitType("VS");
                                Date datetime = Calendar.getInstance().getTime();
                                visitStockModel.setVisitDate(datetime.toString());
                                visitStockModel.setStockQty(0);
                                //TODO Create VisitStock
                                database.CreateVisitStock(visitStockModel);
                            }

                            POMasterModel poMasterModel = new POMasterModel();
                            poMasterModel.setDocNo(DocNo);
                            poMasterModel.setDocTypeCode("IV");
                            poMasterModel.setDocStatus("4");
                            Date date = Calendar.getInstance().getTime();
                            poMasterModel.setDocDate(date.toString());
                            poMasterModel.setBranchID(WHID.substring(0, 3));
                            poMasterModel.setWHID(WHID);
                            poMasterModel.setEmpID("");
                            poMasterModel.setSalAreaID("");
                            poMasterModel.setCustomerID(shopModel.getCustomerID());
                            poMasterModel.setAmount(0.00);
                            poMasterModel.setExcVat(0);
                            poMasterModel.setIncVat(0.00);
                            poMasterModel.setVatAmt(0.00);
                            poMasterModel.setTotalDue(0.00);
                            poMasterModel.setRevisionNo("0");
                            poMasterModel.setCrDate(date.toString());
                            poMasterModel.setEndDate(date.toString());
                            poMasterModel.setLatitude(String.valueOf(gpsTracker.getLatitude()));
                            poMasterModel.setLongitude(String.valueOf(gpsTracker.getLongitude()));
                            poMasterModel.setDiscountType("");
                            poMasterModel.setDiscount("");
                            poMasterModel.setFreight("");
                            poMasterModel.setPayType("0");
                            poMasterModel.setRemark("");
                            poMasterModel.setComment("");
                            poMasterModel.setDiscountRate("");
                            //TODO Create POMaster
                            database.CreatePOMaster(poMasterModel);

                            Intent intent = new Intent(context, SellActivity.class);
                            intent.putExtra("DocNo",DocNo);
                            intent.putExtra("CustomerID",shopModel.getCustomerID());
                            context.startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(context,"กรุณากดบันทึกการเยี่ยมก่อน",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void CauseSpinner(Spinner spinner){
        DB database = DB.getInstance(context);
        SQLiteDatabase db = database.getReadableDatabase();
        String SQL_Cause = "SELECT * FROM "+ CauseContract.TABLE_NAME;
        Cursor cursor = db.rawQuery(SQL_Cause, null);
        causeSpinnerModelArrayList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            causeSpinnerModelArrayList.add(new CauseSpinnerModel(cursor.getString(0), cursor.getString(3)));
        }
        CauseAdapter causeAdapter = new CauseAdapter(context,causeSpinnerModelArrayList);
        spinner.setAdapter(causeAdapter);
    }

    @Override
    public int getItemCount() {
        return shopModels.size();
    }

    static class ShopHolder extends RecyclerView.ViewHolder
    {
        TextView CustomerName,Tel,BillTO,Total;
        RadioButton rdBuy,rdNotBuy;
        Spinner spinCause;
        TextView btn_Sales,btn_saveVisit;
        AppCompatImageView star_mark,size_mark;

        ShopHolder(@NonNull View itemView) {
            super(itemView);

            CustomerName = itemView.findViewById(R.id.txt_CustomerName);
            Tel = itemView.findViewById(R.id.txt_Tel);
            star_mark = itemView.findViewById(R.id.star_mark);
            size_mark = itemView.findViewById(R.id.size_mark);
            BillTO = itemView.findViewById(R.id.txt_Address);
            Total = itemView.findViewById(R.id.txt_SumBill);
            rdBuy = itemView.findViewById(R.id.rdo_Buy);
            rdNotBuy = itemView.findViewById(R.id.rdo_NotBuy);
            spinCause = itemView.findViewById(R.id.spinCause);
            btn_Sales = itemView.findViewById(R.id.btn_Sales);
            btn_saveVisit = itemView.findViewById(R.id.btn_saveVisit);
        }
    }
}
