package com.example.proyec_ad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Principal extends AppCompatActivity {

    ImageButton bntAxulio;
    private static final String ONESIGNAL_APP_ID = "8678709e-249d-4ea6-b50a-9cf2da896b64";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        bntAxulio = findViewById(R.id.btnAxulio);
        bntAxulio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Se ha enviado la señal de ayuda, mantega la calma", Toast.LENGTH_SHORT).show();
                OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
                // OneSignal Initialization
                OneSignal.initWithContext(bntAxulio.getContext());
                OneSignal.setAppId(ONESIGNAL_APP_ID);
                crearNotificacioLanzar();
            }
        });

    }

        public void crearNotificacioLanzar(){
            String url = "https://onesignal.com/api/v1/notifications";
            String latitude ="";
            String longitud ="";
            JSONObject jsonBody;
            try {
                jsonBody = new JSONObject("{\"app_id\":\"8678709e-249d-4ea6-b50a-9cf2da896b64\",\"headings\": {\"en\":\"SOLICITUD DE ALERTA\"},\"included_segments\": [\"Subscribed Users\"],\"contents\": {\"en\":\"Necesito tu ayuda...\"},\"data\":{\"latitud\": \""+latitude+"\",\"altitud\": \""+longitud+"\"}}");
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //now handle the response
                        Toast.makeText(Principal.this, "La notificacion se ha enviado", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //handle the error
                        Toast.makeText(Principal.this, "------", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Accept", "application/json");
                        params.put("Authorization", "Basic MGIwZjc2YjMtOTg5ZC00MzdjLTg3YzctYzcyMmFhMTk5YjRk");
                        params.put("Content-type", "application/json");
                        return params;
                    }
                };
                // Add the request to the queue
                Volley.newRequestQueue(Principal.this).add(jsonRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
