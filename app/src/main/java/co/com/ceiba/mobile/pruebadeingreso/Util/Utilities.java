package co.com.ceiba.mobile.pruebadeingreso.Util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Clase que verifica el estado de la red
 * y conexión de la red movil con wifi o datos moviles.
 */
public class Utilities extends BroadcastReceiver {

    public static ConnectivityReceiverListener connectivityReceiverListener;

    public Utilities() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();

        if(connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }

    }

    public static boolean isConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
    public static Boolean isOnlineNet() {

        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
