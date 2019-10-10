package id.transdigital.fileservicemanagement;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String url = "https://api.tadabase.io/api/v1/data-tables";

    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
            url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //succes callback
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Failure Callback
                }
            }) {
        @Override
        public Map getHeaders() {
            HashMap headers = new HashMap();
            headers.put("Content-type", "application/json");
            headers.put("apiKey", "52gv3cMRZYoQ");
            return headers;
        }
    };
}
