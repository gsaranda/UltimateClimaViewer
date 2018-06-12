package mx.com.darklider.ultimateclimaviewer

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_get_weather.*
import kotlinx.android.synthetic.main.bottomsheet_menu.*
import mx.com.darklider.climamodels.List
import mx.com.darklider.climapresenters.FiveDaysClimaContract
import mx.com.darklider.climapresenters.FiveDaysClimaPresenter
import mx.com.darklider.ultimateclimaviewer.fragments.LocationSelectorListener
import mx.com.darklider.ultimateclimaviewer.fragments.locationSelectorFragment
import mx.com.lider.awesomeanimator.SimpleAlfaAnimation
import java.util.*


class GetWeatherActivity : Activity(), FiveDaysClimaContract, LocationSelectorListener, ResultsViewItemListener {
/******
Main Activity, en esta actividad puedes ver el clima de los ultimos 5 dìas desglosados en intervalos de 3 horas

**/
    lateinit var progressDialog: ProgressDialog
    lateinit var fiveDaysClimaPresenter: FiveDaysClimaPresenter
    lateinit var bottomSheetView: BottomSheetBehavior<View>

    val WEATHER_DETAIL_KEY = "WEATHER_KEY"
    val ANIMATION_TIME:Long=500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_weather)
        fiveDaysClimaPresenter = FiveDaysClimaPresenter()
        setProgressDialog()
        iniListener()
        bottomSheetView = BottomSheetBehavior.from(bottomsheet_menu_weather)
        //solve a glitch on 4.4 devices
        middle_imgbtn.bringToFront()
        middle_tv.bringToFront()
    }

    private fun iniListener() {
        val clickListener=View.OnClickListener{
            var selectorFragment = locationSelectorFragment()
            selectorFragment.setListener(this)
            selectorFragment!!.show(fragmentManager.beginTransaction(), "selector")
            if(bottomsheet_menu_weather.visibility==View.VISIBLE)
                bottomSheetView.state=BottomSheetBehavior.STATE_COLLAPSED
        }
        btn_get_weather.setOnClickListener(clickListener)

        middle_imgbtn.setOnClickListener(clickListener)

    }

    override fun onModelRetreivedSuccesfully(fiveDaysWeatherModel: mx.com.darklider.climamodels.fiveDaysWeatherModel) {
        val resultsViewAdpter = ResultsViewAdapter(fiveDaysWeatherModel.List)
        resultsViewAdpter.setItemListener(this)
        rv_results.adapter = resultsViewAdpter
        rv_results.layoutManager = LinearLayoutManager(this)
        enableViewEfficiently(rv_results)
        enableViewEfficiently(vdd_location)
        enableViewEfficiently(bottomsheet_menu_weather)
        progressDialog.dismiss()
        btn_get_weather.visibility = View.GONE

    }

    //Metodo para configurar el spinner que se muestra al cargar el servicio
    private fun setProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    }

    //Muestra spinner durante carga de servicios
    private fun showProgreesDialog() {
        progressDialog.show()
        progressDialog.setContentView(R.layout.custom_progressdialog);
    }


    //Función para cambiar leyendas con la ubicación del clima
    private fun changeLocationText(location: String) {
        if (location.equals("Mexico"))
            vdd_location.setData(getString(R.string.mexic_location) + ", " + getString(R.string.mexico_description))
        if (location.equals("London"))
            vdd_location.setData(getString(R.string.london_location) + ", " + getString(R.string.london_description))
        if (location.equals("Paris"))
            vdd_location.setData(getString(R.string.paris_location) + ", " + getString(R.string.paris_description))
    }

    //función sencilla para cambiar de actividad
    private fun changeActivity(destinationActivity: Class<*>, detailData: String) {
        val ChangeViewIntent = Intent(this, destinationActivity)
        ChangeViewIntent.putExtra(WEATHER_DETAIL_KEY, detailData)
        overridePendingTransition(R.anim.abc_fade_out, R.anim.abc_fade_in)
        this.startActivity(ChangeViewIntent)
    }

    //Funciones para animar vistas con transparencias de acuerdo a los material designs
    private fun enableViewEfficiently(view: View) {
        if (view.visibility != View.VISIBLE)
            SimpleAlfaAnimation(view,1.0f,ANIMATION_TIME)
    }
    private fun banishViewEfficiently(view: View) {
        if (view.visibility != View.GONE)
            SimpleAlfaAnimation(view,0.0f,ANIMATION_TIME)
    }

/** Metodos que implementa la actividad, el evento click de los elementos con el clima,
 *  el callback cuando se obtuvo la información del servicio correcta,cuando hay error de conexión y
 *  cuando se escoge la ubicación por medio de una modal *****/
    override fun onItemCLicked(weather: List) {
        val gson = Gson()
        val json = gson.toJson(weather)
        changeActivity(WeatherDetailActivity::class.java, json)
    }

    override fun onLocationSelected(location: String) {
        changeLocationText(location)
        banishViewEfficiently(tv_header)
        banishViewEfficiently(btn_get_weather)
        showProgreesDialog()
        fiveDaysClimaPresenter.retrieveData(this, location, Locale.getDefault().getLanguage())
    }

    override fun onModelCouldNotBeRetreived() {
        progressDialog.dismiss()
        val builder = AlertDialog.Builder(this)

        builder.setTitle(getString(R.string.error_dialog_header))
        builder.setMessage(R.string.error_dialog_body)
        builder.setPositiveButton(getString(R.string.error_accept_button)) { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
