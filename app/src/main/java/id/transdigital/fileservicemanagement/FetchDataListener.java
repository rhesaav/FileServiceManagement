package id.transdigital.fileservicemanagement;

import org.json.JSONObject;

public interface FetchDataListener {

    void onFetchComplete(JSONObject data);

    void onStringFailure(String msg);

    void onFetchStart();
}
