package mx.com.darklider.httprequester

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset
/*La clase entrega un JSON de un servicio REST usando async task
*
* */
class AsyncLoader {

    var asyncLoaderListener: AsyncLoaderListener? = null
    fun start(stringUrl: String, asyncLoaderListener: AsyncLoaderListener) {
        this.asyncLoaderListener = asyncLoaderListener;
        ServiceLoader().execute(createUrl(stringUrl));
    }

    private fun createUrl(stringUrl: String): URL? {
        val url: URL
        try {
            url = URL(stringUrl)
        } catch (exception: MalformedURLException) {
            return null
        }

        return url
    }

    @Throws(IOException::class)
    private fun makeHttpRequest(url: URL?): String {
        var jsonResponse = ""

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse
        }

        var urlConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        try {
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 10000
            urlConnection.connectTimeout = 15000
            urlConnection.connect()

            if (urlConnection.responseCode == 200) {
                inputStream = urlConnection.inputStream
                jsonResponse = readFromStream(inputStream)
            } else {
                Log.d("Error response code: ", urlConnection.responseCode.toString())
            }
        } catch (e: IOException) {
            Log.d("Problem ", e.toString())
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect()
            }
            if (inputStream != null) {
                inputStream.close()
            }
        }
        return jsonResponse
    }

    @Throws(IOException::class)
    private fun readFromStream(inputStream: InputStream?): String {
        val output = StringBuilder()
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
            val reader = BufferedReader(inputStreamReader)
            var line: String? = reader.readLine()
            while (line != null) {
                output.append(line)
                line = reader.readLine()
            }
        }
        return output.toString()
    }


    private inner class ServiceLoader : AsyncTask<URL, String, String>() {


        override fun onPostExecute(json: String) {
            if (json != null)
                asyncLoaderListener?.isJSONReady(json)
            else
                asyncLoaderListener?.onConnectionError()

        }


        override fun doInBackground(vararg params: URL): String {
            try {
                return makeHttpRequest(params[0])
            } catch (e: Exception) {
                return "something went wrong"
            }

        }
    }
}