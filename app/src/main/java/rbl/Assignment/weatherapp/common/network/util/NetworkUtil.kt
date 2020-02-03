package rbl.Assignment.weatherapp.common.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager
import android.util.Log
import rbl.Assignment.weatherapp.common.network.listener.INetworkListener
import rbl.Assignment.weatherapp.common.network.util.NetworkConstants.Companion.OPTIMAL_NETWORK
import rbl.Assignment.weatherapp.common.network.util.NetworkConstants.Companion.POOR_NETWORK
import rbl.Assignment.weatherapp.common.network.util.NetworkConstants.Companion.TYPE_NOT_CONNECTED
import rbl.Assignment.weatherapp.common.network.util.NetworkConstants.Companion.TYPE_WIFI

class NetworkUtil {

    companion object {

        private val TAG: String = NetworkUtil::class.java.simpleName
        private var networkListener: INetworkListener? = null

        private fun isNetworkAvailable(activeNetworkInfo: NetworkInfo?): Boolean {
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }

        fun getConnectivityStatus(context: Context) {
            val connectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

            val activeNetwork = connectivityManager?.activeNetworkInfo
            if (null != activeNetwork && isNetworkAvailable(activeNetwork)) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    notifyConnectivityChange(TYPE_WIFI)
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    notifyConnectivityChange(checkConnectivity(activeNetwork))
                }
            } else {
                notifyConnectivityChange(TYPE_NOT_CONNECTED)
            }
        }

        private fun checkConnectivity(activeNetworkInfo: NetworkInfo): String {
            when (activeNetworkInfo.subtype) {
                TelephonyManager.NETWORK_TYPE_1xRTT,
                TelephonyManager.NETWORK_TYPE_CDMA,
                TelephonyManager.NETWORK_TYPE_EDGE,
                TelephonyManager.NETWORK_TYPE_GPRS,
                TelephonyManager.NETWORK_TYPE_IDEN -> return POOR_NETWORK
                TelephonyManager.NETWORK_TYPE_EVDO_0,
                TelephonyManager.NETWORK_TYPE_EVDO_A,
                TelephonyManager.NETWORK_TYPE_HSDPA,
                TelephonyManager.NETWORK_TYPE_HSPA,
                TelephonyManager.NETWORK_TYPE_HSUPA,
                TelephonyManager.NETWORK_TYPE_UMTS,
                TelephonyManager.NETWORK_TYPE_EHRPD,
                TelephonyManager.NETWORK_TYPE_EVDO_B,
                TelephonyManager.NETWORK_TYPE_HSPAP,
                TelephonyManager.NETWORK_TYPE_LTE -> return OPTIMAL_NETWORK
                TelephonyManager.NETWORK_TYPE_UNKNOWN -> return TYPE_NOT_CONNECTED
                else -> return TYPE_NOT_CONNECTED
            }
        }

        fun setConnectivityListener(listener: INetworkListener) {
            Log.i(TAG, "Enter setInternetConnectivityListener : $listener")
            networkListener = listener
        }

        private fun notifyConnectivityChange(connectionStatus: String) {
            networkListener?.onConnectivityChange(connectionStatus)
        }
    }
}