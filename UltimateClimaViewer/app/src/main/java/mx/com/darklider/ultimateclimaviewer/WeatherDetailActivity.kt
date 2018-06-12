package mx.com.darklider.ultimateclimaviewer


import android.app.Activity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_weather_detail.*
import mx.com.darklider.climamodels.List

class WeatherDetailActivity : Activity() {
    /* Actividad encargada de desplegar mas a detalle el clima del periodo de tiempo seleccionado,
     solo despliega en el onCreate los valore que necesita del modelo, se reciben los datos en un string JSON y se deserializan.
    * */

    val WEATHER_DETAIL_KEY = "WEATHER_KEY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
        val weatherData: String = intent.getStringExtra(WEATHER_DETAIL_KEY)
        val type = object : TypeToken<List>() {

        }.type
        val gson = Gson()
        val weatherDetail: List = gson.fromJson(weatherData, type)

        vdd_date.setData(weatherDetail.Date)
        vdd_description.setData(weatherDetail.weather.get(0).description)

        if (!weatherDetail.temperatureModel.maxTemperature .equals("")) vdd_temp_max.setData(weatherDetail.temperatureModel.maxTemperature+"°C")
        else vdd_temp_max.visibility = View.GONE

        if (!weatherDetail.temperatureModel.minTemperature.equals("")) vdd_temp_min.setData(weatherDetail.temperatureModel.minTemperature+"°C")
        else vdd_temp_min.visibility = View.GONE

        vdd_humidity.setData(weatherDetail.temperatureModel.humidity + " %")
        vdd_pressure.setData(weatherDetail.temperatureModel.pressure + " hPa")
    }

    override fun onBackPressed() {
        overridePendingTransition(R.anim.abc_fade_out, R.anim.abc_fade_in)
        super.onBackPressed()
    }
}
