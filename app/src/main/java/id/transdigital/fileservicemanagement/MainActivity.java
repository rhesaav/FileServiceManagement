package id.transdigital.fileservicemanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    final String url = "https://api.tadabase.io/api/v1/data-tables";
    RequestQueue queue = Volley.newRequestQueue(this);
    //prepare the request
//    JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//            new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    Log.d("Response", response.toString());
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.d("Error.Response", response);
//                }
//            }
//    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //to request queue
//    queue.add(getRequest)
}
