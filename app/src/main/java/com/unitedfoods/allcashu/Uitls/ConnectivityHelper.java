package com.unitedfoods.allcashu.Uitls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityHelper {

    public static boolean isConnectedToNetwork(Context context){

        ConnectivityManager connectivityManager  = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean isConnect = false;

        if (connectivityManager != null)
        {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            isConnect = (networkInfo != null) && (networkInfo.isConnectedOrConnecting());
        }

        return isConnect;
    }
}
