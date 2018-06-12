package mx.com.darklider.climamodels

import com.google.gson.annotations.SerializedName
/*Se creo una librería aparte con los modelos para una compilación mas rapida, para esta arquitectura
* la unica responsabilidad del modelo es proporcionar la estructura de datos, se uso GSON para pasar los datos del servicio de clima
* a los objetos del modelo*/
data class TemperatureModel(
        @SerializedName("temp") var temperature: String,
        @SerializedName("temp_min") var minTemperature: String,
        @SerializedName("temp_max") var maxTemperature: String,
        @SerializedName("pressure") var pressure: String,
        @SerializedName("humidity") var humidity: String
)