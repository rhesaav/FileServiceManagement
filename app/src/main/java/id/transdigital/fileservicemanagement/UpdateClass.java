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

public class UpdateClass extends AppCompatActivity {
    RequestQueue rq;
    String table_id, record_id, app_id, app_key, app_secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_scr);

        table_id = "o6WQb5NnBZ";
        record_id = "DVWQWRNZ49";
        app_id = "72Npv8mjwv";
        app_key = "52gv3cMRZYoQ";
        app_secret = "tf5VQXHkfLgnKGlwP7h1naDMg5NVdWZC";

        final ProgressBar progressBar = findViewById(R.id.upd_progressbar);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable wrapDrawable = DrawableCompat.wrap(progressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(UpdateClass.this, android.R.color.holo_red_light));
            progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
        } else {
            progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(UpdateClass.this, android.R.color.holo_red_light), PorterDuff.Mode.SRC_IN);
        }

        Button backBtn = findViewById(R.id.cnc_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postTripIntent = new Intent(UpdateClass.this, MainActivity.class);
                UpdateClass.this.startActivity(postTripIntent);
            }
        });

        Button btnUpdate = findViewById(R.id.cnc_button_update);
        final TextView txt = findViewById(R.id.textView);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                rq = Volley.newRequestQueue(UpdateClass.this);

                StringRequest getCntryReq = new StringRequest(Request.Method.POST,
                        "https://api.tadabase.io/api/v1/data-tables/" + table_id + "/records/" + record_id,
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
                            Toast.makeText(getApplicationContext(), "Server response take too long", Toast.LENGTH_LONG).show();
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No connection to server", Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                }
                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("X-Tadabase-App-id", app_id);
                        headers.put("X-Tadabase-App-Key", app_key);
                        headers.put("X-Tadabase-App-Secret", app_secret);
                        return headers;
                    }

                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("field_22", "coba dari android");
                        return params;
                    }
                };
                rq.add(getCntryReq);
            }
        });
    }
}