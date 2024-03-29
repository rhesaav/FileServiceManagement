package id.transdigital.fileservicemanagement;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RequestQueue rq;
    String table_id, record_id, app_id, app_key, app_secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table_id = "o6WQb5NnBZ";
        record_id = "DVWQWRNZ49";
        app_id = "72Npv8mjwv";
        app_key = "52gv3cMRZYoQ";
        app_secret = "tf5VQXHkfLgnKGlwP7h1naDMg5NVdWZC";

        final ProgressBar progressBar = findViewById(R.id.hfc_progressbar);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable wrapDrawable = DrawableCompat.wrap(progressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_light));
            progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
        } else {
            progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_light), PorterDuff.Mode.SRC_IN);
        }

        Button updBtn = findViewById(R.id.cnc_button_update);
        updBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postTripIntent = new Intent(MainActivity.this, UpdateClass.class);
                MainActivity.this.startActivity(postTripIntent);
            }
        });

        Button btnList = findViewById(R.id.cnc_button);

        final TextView txt = findViewById(R.id.textView);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                rq = Volley.newRequestQueue(MainActivity.this);

                StringRequest getCntryReq = new StringRequest(Request.Method.GET, "https://api.tadabase.io/api/v1/data-tables",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                txt.setText(response);
                                try {
                                    JSONObject json = new JSONObject(response);
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                                progressBar.setVisibility(View.GONE);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError) {
                            Toast.makeText(getApplicationContext(), "Server response tak too long", Toast.LENGTH_LONG).show();
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No connectionto server", Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                }
                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("X-Tadabase-App-id", app_id);
                        params.put("X-Tadabase-App-Key", app_key);
                        params.put("X-Tadabase-App-Secret", app_secret);
                        return params;
                    }
                };
                rq.add(getCntryReq);
            }
        });
    }
}

