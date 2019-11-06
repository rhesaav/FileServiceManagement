package id.transdigital.fileservicemanagement;

import android.content.Context;

public class GETAPIRequest {

    public void request(final Context context, final FetchDataListener listener, final String ApiURL) {
        if (listener != null) {
            listener.onFetchStart();
        }

        String baseUrl = "http://studypeek.com/test/";
        String url = baseUrl + ApiURL;
    }
}
