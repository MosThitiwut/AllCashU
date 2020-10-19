package com.unitedfoods.allcashu.Uitls.SalAreaSpinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unitedfoods.allcashu.Model.SaleAreaModel;
import com.unitedfoods.allcashu.R;

import java.util.ArrayList;

public class SalAreaAdapter extends ArrayAdapter<SaleAreaSpinnerModel> {

    public SalAreaAdapter(@NonNull Context context, ArrayList<SaleAreaSpinnerModel> saleAreaModels){
        super(context,0,saleAreaModels);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position,View convertView,ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.salearea_spinners_rows,parent,false
            );
        }
        TextView spinSaleArea = convertView.findViewById(R.id.spinSaleAreaName);
        SaleAreaSpinnerModel saleAreaModel = getItem(position);
        spinSaleArea.setText(saleAreaModel.getSalAreaName());
        return convertView;
    }
}
