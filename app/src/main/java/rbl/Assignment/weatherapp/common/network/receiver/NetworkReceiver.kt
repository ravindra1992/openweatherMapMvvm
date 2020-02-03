package rbl.Assignment.weatherapp.common.network.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import rbl.Assignment.weatherapp.common.network.util.NetworkUtil

class NetworkReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        NetworkUtil.getConnectivityStatus(context!!)
    }

    companion object {
        val TAG: String = NetworkReceiver::class.java.simpleName
    }
}