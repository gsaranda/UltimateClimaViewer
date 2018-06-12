package mx.com.darklider.httprequester

interface AsyncLoaderListener {
    fun   isJSONReady(Json: String)
    fun   onConnectionError()
}