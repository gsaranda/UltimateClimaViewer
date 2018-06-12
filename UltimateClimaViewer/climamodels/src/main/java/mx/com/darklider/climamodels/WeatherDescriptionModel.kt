package mx.com.darklider.climamodels

import com.google.gson.annotations.SerializedName
/*Se creo una librería aparte con los modelos para una compilación mas rapida, para esta arquitectura
* la unica responsabilidad del modelo es proporcionar la estructura de datos, se uso GSON para pasar los datos del servicio de clima
* a los objetos del modelo*/

data class WeatherDescriptionModel(
        @SerializedName("main") var descriptionTitle:String,
        @SerializedName("description") var description :String
)