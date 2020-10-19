package com.unitedfoods.allcashu.Settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.unitedfoods.allcashu.DB;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.SettingContract;

import java.util.Objects;

public class SettingFragment extends Fragment implements View.OnClickListener{

    DB database;
    TextView IP,DNS,PortDNS;
    String IPText,DNSText,PORTDNSText;
    AlertDialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vi = inflater.inflate(R.layout.fragment_setting,container,false);
        return vi;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        IP = getView().findViewById(R.id.IP);
        IPText = "";
        DNS = getView().findViewById(R.id.DNS);
        DNSText = "";
        PortDNS = getView().findViewById(R.id.PortDNS);
        PORTDNSText = "";
        database = DB.getInstance(getActivity());

        IP.setOnClickListener(this);
        DNS.setOnClickListener(this);
        PortDNS.setOnClickListener(this);

        SQLiteDatabase db = database.getReadableDatabase();

        //Set IP
        String SQL_IP = "SELECT * FROM "+ SettingContract.TABLE_NAME+" Where "+SettingContract.Columns.TASK_CFGNAME+" = 'WebServiceHost'";
        Cursor cursor = db.rawQuery(SQL_IP,null);
        while (cursor.moveToNext()){

            IPText = cursor.getString(1);

        }

        IP.append(IPText);

        //Set DNS
        String SQL_DNS = "SELECT * FROM "+SettingContract.TABLE_NAME+ " Where "+SettingContract.Columns.TASK_CFGNAME+" = 'WebServiceOnline'";
        cursor = db.rawQuery(SQL_DNS,null);
        while (cursor.moveToNext()){

            DNSText = cursor.getString(1);
        }

        DNS.append(DNSText);

        //Set PortDNS
        String SQL_PORTDNS = "SELECT * FROM "+SettingContract.TABLE_NAME + " Where "+SettingContract.Columns.TASK_CFGNAME+ " = 'WebServiceOnlinePort'";
        cursor = db.rawQuery(SQL_PORTDNS,null);

        while (cursor.moveToNext()){
            PORTDNSText = cursor.getString(1);
        }
        PortDNS.append(PORTDNSText);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.IP:

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = getLayoutInflater().inflate(R.layout.edit_setting,null);
                final EditText editText = view.findViewById(R.id.editText_setting);
                Button save = view.findViewById(R.id.save_setting);
                editText.setText(IPText);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        database.UpdateIP(editText.getText().toString());
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(SettingFragment.this).attach(SettingFragment.this).commit();
                        CloseDialog();
                    }
                });
                builder.setView(view);
                dialog = builder.create();
                dialog.show();
                break;

            case R.id.DNS:

                AlertDialog.Builder builderDNS = new AlertDialog.Builder(getContext());
                View viewDNS = getLayoutInflater().inflate(R.layout.edit_setting,null);
                final EditText editTextDNS = viewDNS.findViewById(R.id.editText_setting);
                Button saveDNS = viewDNS.findViewById(R.id.save_setting);
                editTextDNS.setText(DNSText);
                saveDNS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        database.UpdateDNS(editTextDNS.getText().toString());
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(SettingFragment.this).attach(SettingFragment.this).commit();
                        CloseDialog();

                    }
                });
                builderDNS.setView(viewDNS);
                dialog = builderDNS.create();
                dialog.show();
                break;

            case R.id.PortDNS:
                AlertDialog.Builder builderPortDNS = new AlertDialog.Builder(getContext());
                View viewPortDNS = getLayoutInflater().inflate(R.layout.edit_setting, null);
                final EditText editTextPortDNS = viewPortDNS.findViewById(R.id.editText_setting);
                Button savePortDNS = viewPortDNS.findViewById(R.id.save_setting);
                editTextPortDNS.setText(PORTDNSText);
                savePortDNS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        database.UpdatePortDNS(editTextPortDNS.getText().toString());
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(SettingFragment.this).attach(SettingFragment.this).commit();
                        CloseDialog();
                    }
                });
                builderPortDNS.setView(viewPortDNS);
                dialog = builderPortDNS.create();
                dialog.show();
                break;
        }

    }

    private void CloseDialog()
    {
        if(dialog != null)
        {
            dialog.dismiss();
        }
    }
}


