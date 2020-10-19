package com.unitedfoods.allcashu.Uitls.DistrictSpinners;

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

public class DistrictAdapter extends ArrayAdapter<DistrictSpinnerModel> {

    public DistrictAdapter(Context context, ArrayList<DistrictSpinnerModel> districtSpinnerModels){
        super(context,0,districtSpinnerModels);
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

    private View initView(int position,View convertView,ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.district_spinners_row,parent,false
            );
        }

        TextView spinDistrict = convertView.findViewById(R.id.spinDistrict);
        DistrictSpinnerModel districtSpinnerModel = getItem(position);
        spinDistrict.setText(districtSpinnerModel.getDistrictName());

        return convertView;
    }
}
