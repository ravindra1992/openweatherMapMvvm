package rbl.Assignment.weatherapp.weather.view


import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_weather_error.view.*
import rbl.Assignment.weatherapp.R


class WeatherErrorFragment : Fragment(){

    private var mContext: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather_error_container, container, false)
        initViews(view)
        return view
    }

    private fun initViews(v: View) {
        v.button_turn_on_network.setOnClickListener { v -> displayAlertDialog(v!!) }
    }

    private fun displayAlertDialog(v: View){
        AlertDialog.Builder(mContext!!)
            .setTitle(resources.getString(R.string.turn_on_wifi))
            .setMessage(resources.getString(R.string.msg_please_turn_on))
            .setPositiveButton(resources.getString(R.string.turn_on)) { dialog, which ->
                val wifi = v.context?.getSystemService(Context.WIFI_SERVICE) as WifiManager?
                wifi!!.isWifiEnabled = true
            }
            .setNegativeButton(resources.getString(R.string.cancel)
            ) { dialog, which -> dialog?.dismiss() }.create().show()
    }

}
