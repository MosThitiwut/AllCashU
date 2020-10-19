package com.unitedfoods.allcashu.Uitls.CauseSpinners;

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

public class CauseAdapter extends ArrayAdapter<CauseSpinnerModel>
{

    public CauseAdapter(Context context, ArrayList<CauseSpinnerModel> causeSpinnerModels){
        super(context, 0, causeSpinnerModels);
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
                    R.layout.cause_spinners_row, parent, false
            );
        }

        TextView spinCause = convertView.findViewById(R.id.spinCause);
        CauseSpinnerModel causeSpinnerModel = getItem(position);
        spinCause.setText(causeSpinnerModel.getCauseName());

        return convertView;
    }
}
