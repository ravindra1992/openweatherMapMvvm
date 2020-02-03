package rbl.Assignment.weatherapp.common.network.listener

interface INetworkListener {
    fun onConnectivityChange(connectionStatus: String)
}