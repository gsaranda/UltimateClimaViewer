package mx.com.darklider.climamodels

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/*Se creo una librería aparte con los modelos para una compilación mas rapida, para esta arquitectura
* la unica responsabilidad del modelo es proporcionar la estructura de datos, se uso GSON para pasar los datos del servicio de clima
* a los objetos del modelo*/
 data class fiveDaysWeatherModel(
     @SerializedName("list") var List: ArrayList<List>
)