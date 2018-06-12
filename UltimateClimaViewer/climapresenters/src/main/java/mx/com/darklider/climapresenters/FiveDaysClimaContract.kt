package mx.com.darklider.climapresenters

import mx.com.darklider.climamodels.fiveDaysWeatherModel

interface FiveDaysClimaContract {
    fun onModelRetreivedSuccesfully(fiveDaysWeatherModel: fiveDaysWeatherModel)
    fun onModelCouldNotBeRetreived()
}