package mx.com.darklider.climapresenters

import com.google.gson.Gson
import mx.com.darklider.climamodels.fiveDaysWeatherModel
import mx.com.darklider.httprequester.AsyncLoader
import mx.com.darklider.httprequester.AsyncLoaderListener

/*Presentador, encargado de generar el modelo de datos para porporcionarle la información del modelo a la Vista
con el metodo onModelRetreivedSuccesfully

Al ser pocos parametros para hacer un Request, decidi hacer mi propia librería para consumir el servicio, armando yo mismo el endPoint.
* */

class FiveDaysClimaPresenter() : AsyncLoaderListener {


    val API_KEY = "53486f7d8e7a37fc977897f4574f1925"

    internal
    lateinit var fiveDaysClimaContract: FiveDaysClimaContract

    fun retrieveData(fiveDaysClimaContract: FiveDaysClimaContract, CityCode: String, languageCode: String) {
        this.fiveDaysClimaContract = fiveDaysClimaContract
        val CITY_CODE = CityCode
        val LANGUAGE = "&lang=" + languageCode
        val URl = "https://api.openweathermap.org/data/2.5/forecast?q=" + CITY_CODE + "&APPID=" + API_KEY + LANGUAGE+"&units=metric"
        AsyncLoader().start(URl, this)
    }

    override fun isJSONReady(Json: String) {
        val gson = Gson()
        if(Json!="") {
            val model = gson.fromJson(Json, fiveDaysWeatherModel::class.java)
            fiveDaysClimaContract?.onModelRetreivedSuccesfully(model)
        }else{
            fiveDaysClimaContract?.onModelCouldNotBeRetreived()
        }
    }
//Callback para cuando hay algun error al sacar la información
    override fun onConnectionError() {
        fiveDaysClimaContract?.onModelCouldNotBeRetreived()
    }
}
