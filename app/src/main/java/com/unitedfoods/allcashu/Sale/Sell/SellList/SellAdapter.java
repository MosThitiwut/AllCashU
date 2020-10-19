package com.unitedfoods.allcashu.Sale.Sell.SellList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedfoods.allcashu.R;

import java.util.ArrayList;

public class SellAdapter extends RecyclerView.Adapter<SellAdapter.SellHolder>{

    Context context;
    ArrayList<SellModel> arrayList;
    public SellAdapter(Context context, ArrayList<SellModel> arrayList){

        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public SellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final LayoutInflater layoutInflater =  LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.shop_recy_layout,parent,false);
        SellHolder sellHolder = new SellHolder(view);
        return sellHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SellHolder holder, int position) {

        SellModel sellModel = arrayList.get(position);
        holder.ProductID.setText(sellModel.getProductID());
        holder.ProductName.setText(sellModel.getProductName());
        holder.tv_stock_bill_one.setText("สต็อค : "+sellModel.getStockBillOne().toString());
        holder.tv_sell_bill_one.setText("บิลซื้อ : "+sellModel.getPOBillOne().toString());
        holder.tv_stock_bill_two.setText("สต็อค : "+sellModel.getStockBillTwo().toString());
        holder.tv_sell_bill_two.setText("บิลซื้อ : "+sellModel.getPOBillTwo().toString());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class SellHolder extends RecyclerView.ViewHolder
    {
        TextView ProductID,ProductName,tv_stock_bill_one,tv_sell_bill_one,tv_stock_bill_two,tv_sell_bill_two;
        Button btn_CheckStock,btn_Sell;
        public SellHolder(@NonNull View itemView) {
            super(itemView);

            ProductID = itemView.findViewById(R.id.ProductID);
            ProductName = itemView.findViewById(R.id.ProductName);
            tv_stock_bill_one  = itemView.findViewById(R.id.tv_stock_bill_one);
            tv_sell_bill_one = itemView.findViewById(R.id.tv_sell_bill_one);
            tv_stock_bill_two = itemView.findViewById(R.id.tv_stock_bill_two);
            tv_sell_bill_two = itemView.findViewById(R.id.tv_sell_bill_two);
            btn_Sell = itemView.findViewById(R.id.btn_Sell);
            btn_CheckStock = itemView.findViewById(R.id.btn_CheckStock);
        }
    }
}
