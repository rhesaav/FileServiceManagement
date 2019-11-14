package id.transdigital.fileservicemanagement;

import android.app.Dialog;
import android.content.Context;

import com.android.volley.RequestQueue;

public class RequestQueueService {
    private static RequestQueueService mInstance;
    private static Context mCtx;
    private static Dialog mProgressDialog;
    private RequestQueue mRequestQueue;

    private RequestQueueService(Context context) {
        mCtx = context;
//        mRequestQueue = getRequestQueue();
    }
}
