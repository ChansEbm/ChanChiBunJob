package com.app.recall.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import static org.litepal.tablemanager.AssociationUpdater.TAG;

/**
 * Created by KenChan on 16/12/14.
 */

public class NetworkChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: " + intent.getAction());
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    Toast.makeText(context, "Wifi is disabled", Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:
                    Toast.makeText(context, "Unknown wifi error", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        //        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
        //            Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager
        // .EXTRA_NETWORK_INFO);
        //            if (parcelableExtra != null) {
        //                NetworkInfo info = (NetworkInfo) parcelableExtra;
        //                boolean isConnected = info.getState() == NetworkInfo.State.CONNECTED;
        //                WifiManager wifiManager = (WifiManager) context.getSystemService(
        //                        Context.WIFI_SERVICE);
        //                wifiManager.setWifiEnabled(isConnected);
        //            }
        //        }

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                boolean isConnected = activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
                if (!isConnected) {
                    Toast.makeText(context, "Connection not available", Toast.LENGTH_SHORT).show();
                }

            } else {
                Log.e(TAG, "onReceive: a null");
            }
        }


    }
}
