package mx.com.darklider.climamodels

import com.google.gson.annotations.SerializedName
/*Se creo una librería aparte con los modelos para una compilación mas rapida, para esta arquitectura
* la unica responsabilidad del modelo es proporcionar la estructura de datos, se uso GSON para pasar los datos del servicio de clima
* a los objetos del modelo*/


//WeatherDescriptionModel es un arreglo porque por alguna  razon desconocida,
// el Api de openWeather nos da los valores en un arreglo de un elemento
data class List(
        @SerializedName("dt_txt") var Date:String,
        @SerializedName("weather") var weather:ArrayList<WeatherDescriptionModel>,
        @SerializedName("main") var temperatureModel: TemperatureModel
)