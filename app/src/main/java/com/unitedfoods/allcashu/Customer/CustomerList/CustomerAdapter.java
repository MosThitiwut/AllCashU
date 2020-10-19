package com.unitedfoods.allcashu.Customer.CustomerList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedfoods.allcashu.Customer.AddCustomerActivity;
import com.unitedfoods.allcashu.CustomerContract;
import com.unitedfoods.allcashu.DB;
import com.unitedfoods.allcashu.Model.CustomerModel;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.SaleAreaContract;
import com.unitedfoods.allcashu.Uitls.GPSTracker;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerHolder> implements Filterable {
    private static final String TAG = "CustomerAdapter";
    ArrayList<CustomerModel> customerModels,filterList;
    Context context;
    GPSTracker gpsTracker;
    String LatLonginPhone;
    CustomerFilter filter;
    public CustomerAdapter(ArrayList<CustomerModel> customerModels,Context context)
    {
        this.customerModels = customerModels;
        this.context = context;
        this.filterList = customerModels;
    }
    @NonNull
    @Override
    public CustomerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.customer_recy_layout,parent,false);
            final CustomerHolder customerHolder = new CustomerHolder(view);


        return customerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomerHolder holder, int position) {

        final CustomerModel customerModel = customerModels.get(position);
        DB database = DB.getInstance(context);
        SQLiteDatabase db = database.getReadableDatabase();
        String sql_SaleArea = "SELECT "+ SaleAreaContract.Columns.TASK_SALAREANAME+
                " FROM "+SaleAreaContract.TABLE_NAME+" WHERE "+SaleAreaContract.Columns.TASK_SALAREAID+" =?";
        Cursor cursor = db.rawQuery(sql_SaleArea,new String[]{customerModel.getSalAreaID()});
        while (cursor.moveToNext())
        {
            holder.txt_SaleArea.setText(cursor.getString(0));
        }
        holder.CustomerID.setText(customerModel.getCustomerID());
        holder.CustomerName.setText(customerModel.getCustName());
        holder.BillTO.setText(customerModel.getBillTo());

        final String Check  =  customerModel.getLatitude()+","+customerModel.getLongitude();
        if (Check.equals(""+","+"")|Check.equals("0"+","+"0")|Check.equals("null"+","+"null")){
            holder.LatAndLong.setText("X");
            holder.LatAndLong.setTextColor(Color.RED);
        }else {
            holder.LatAndLong.setText("√");
            holder.LatAndLong.setTextColor(Color.GREEN);

        }

        holder.Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher).setTitle("ยืนยันการแก้ไขพิกัดร้านค้า").setMessage("กดปุ่ม ตกลง เพื่อแก้ไขพิกัดร้านค้า").setPositiveButton("ตกลง",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DB database = DB.getInstance(context);
                                SQLiteDatabase db = database.getWritableDatabase();
                                double lat = 0;
                                double lg = 0;
                                gpsTracker = new GPSTracker(context);
                                if (gpsTracker.canGetLocation()){
                                    lat = gpsTracker.getLatitude();
                                    lg = gpsTracker.getLongitude();
                                    LatLonginPhone = lat+","+lg;

                                }else {
                                    gpsTracker.showSettingsAlert();
                                }

                                String SQL_UPDATE = "UPDATE "+ CustomerContract.TABLE_NAME+
                                        " SET "+CustomerContract.Columns.TASK_Latitude+" = '"+lat+"',"+
                                        " "+CustomerContract.Columns.TASK_Longitude+" = '"+lg+"'"+
                                        " Where "+CustomerContract.Columns.TASK_CUSTOMERID+" = '"+customerModel.getCustomerID()+"'";
                                db.execSQL(SQL_UPDATE);
                                customerModel.setLatitude(String.valueOf(lat));
                                customerModel.setLongitude(String.valueOf(lg));
                                Log.d(TAG, "onClick: "+LatLonginPhone);
                                holder.LatAndLong.setText("√");
                                holder.LatAndLong.setTextColor(Color.GREEN);
                            }
                        }).setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


            }
        });

        holder.GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Check.equals(""+","+"")|Check.equals("0"+","+"0")|Check.equals("null"+","+"null")){
                    new AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher).setTitle("แจ้งเตือน").setMessage("ไม่สามารถนำทางได้ เนื่องจากไม่มีการบันทึกตำแหน่งของร้านค้า").setPositiveButton("ตกลง",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }
                else {

                    double lat = 0;
                    double lg = 0;
                    gpsTracker = new GPSTracker(context);
                    if (gpsTracker.canGetLocation()){
                        lat = gpsTracker.getLatitude();
                        lg = gpsTracker.getLongitude();
                        LatLonginPhone = lat+","+lg;

                    }else {
                        gpsTracker.showSettingsAlert();
                    }

                    String url = "https://www.google.com/maps/dir/"+ LatLonginPhone+"/"+customerModel.getLatitude()+","+customerModel.getLongitude();
                    Intent intent1 = new Intent(Intent.ACTION_VIEW);
                    intent1.setData(Uri.parse(url));
                    context.startActivity(intent1);
                }

            }
        });

        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AddCustomerActivity.class);
                intent.putExtra("CustomerID",customerModel.getCustomerID());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return customerModels.size();
    }

    @Override
    public Filter getFilter() {

        if (filter == null)
        {
            filter = new CustomerFilter(filterList,this);
        }

        return filter;
    }



    static class CustomerHolder extends RecyclerView.ViewHolder {

        TextView CustomerID, CustomerName, BillTO, LatAndLong,txt_SaleArea;
        TextView Save, GPS,Edit;

        CustomerHolder(@NonNull View itemView) {
            super(itemView);

            CustomerID = itemView.findViewById(R.id.tvCustomerID);
            CustomerName = itemView.findViewById(R.id.tvCustomerName);
            BillTO = itemView.findViewById(R.id.tvBillTO);
            LatAndLong = itemView.findViewById(R.id.tvLatAndLong);
            Save = itemView.findViewById(R.id.btn_SaveLatAndLong);
            GPS = itemView.findViewById(R.id.btn_GPS);
            txt_SaleArea = itemView.findViewById(R.id.txt_SaleArea);
            Edit = itemView.findViewById(R.id.btn_Edit);


        }
    }
}
