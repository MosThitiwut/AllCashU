package com.unitedfoods.allcashu.Uitls.AreaSpinners;

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

public class AreaAdapter extends ArrayAdapter<AreaSpinnerModel> {

    public AreaAdapter(Context context, ArrayList<AreaSpinnerModel> areaSpinnerModels){
        super(context,0,areaSpinnerModels);
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

    private View initView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.district_spinners_row,parent,false
            );
        }

        TextView spinArea = convertView.findViewById(R.id.spinDistrict);
        AreaSpinnerModel areaSpinnerModel = getItem(position);
        spinArea.setText(areaSpinnerModel.getAreaName());

        return convertView;
    }
}
