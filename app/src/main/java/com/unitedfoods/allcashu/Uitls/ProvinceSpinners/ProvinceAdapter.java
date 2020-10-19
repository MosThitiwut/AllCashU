package com.unitedfoods.allcashu.Uitls.ProvinceSpinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unitedfoods.allcashu.R;

import java.util.ArrayList;

public class ProvinceAdapter extends ArrayAdapter<ProvinceSpinnerModel> {

    public ProvinceAdapter(Context context, ArrayList<ProvinceSpinnerModel> provinceSpinnerModels)
    {   super(context,0,provinceSpinnerModels);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position,View convertView, ViewGroup parent){

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.province_spinners_row,parent,false
            );
        }

        TextView spinProvince = convertView.findViewById(R.id.spinProvince);
        ProvinceSpinnerModel provinceSpinnerModel = getItem(position);
        spinProvince.setText(provinceSpinnerModel.getProvinceName());
        return convertView;
    }
}
