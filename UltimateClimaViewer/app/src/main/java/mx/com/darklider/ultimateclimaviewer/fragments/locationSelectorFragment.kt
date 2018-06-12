package mx.com.darklider.ultimateclimaviewer.fragments

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.location_selector_fragment.*
import mx.com.darklider.ultimateclimaviewer.R

class locationSelectorFragment : DialogFragment() {
/* Fragmento para selecciónar la región de la cual se quiere obtener el clima, por ahora solo tiene 3 opciones, pero
pueden agregarse mas lugares facilmente o en un futuro remplazar este fragmento por otro que use un mapa
*
*          */
    var locationSelectorListener: LocationSelectorListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val mView = inflater!!.inflate(R.layout.location_selector_fragment, container, false)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mex_button.setOnClickListener(View.OnClickListener { changeLocation("Mexico") })
        en_button.setOnClickListener(View.OnClickListener { changeLocation("London") })
        fr_button.setOnClickListener(View.OnClickListener { changeLocation("Paris") })
    }

    fun setListener(locationSelectorListener: LocationSelectorListener) {
        this.locationSelectorListener = locationSelectorListener
    }

    fun changeLocation(newLocation: String) {
        locationSelectorListener?.onLocationSelected(newLocation)
        dismiss()
    }
}