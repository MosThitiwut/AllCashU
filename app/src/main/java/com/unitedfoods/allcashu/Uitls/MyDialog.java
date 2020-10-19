package com.unitedfoods.allcashu.Uitls;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.unitedfoods.allcashu.R;

public class MyDialog {

    private Context context;

    public MyDialog(Context context){
        this.context = context;

    }

    public void normalDialog(String titleString, String messageString){
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        builder.setCancelable( false );
        builder.setIcon( R.drawable.ic_alert );
        builder.setTitle( titleString );
        builder.setMessage( messageString );
        builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        } );
        builder.show();
    }
}
