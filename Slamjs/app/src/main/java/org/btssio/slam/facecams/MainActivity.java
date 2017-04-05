package org.btssio.slam.facecams;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.btssio.slam.facecams.R;
import org.btssio.slam.facecams.adapters.EventsAdapter;
import org.btssio.slam.facecams.objects.Events;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    public static final String SERVER_URL = "http://172.20.10.2:3000/android/event";

    ListView lv;
    private String jsonString;
    JSONObject jsonResponse;
    JSONArray arrayJson;
    ArrayList<Events> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<Events>();
        lv = (ListView) findViewById(R.id.list);
    }

    public void onStart() {
        super.onStart();
        // Envoi d'une requete dans la file d'attente
        sendRequest();
    }

    private void sendRequest() {
        StringRequest stringRequest = new StringRequest(SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Log.i("reponse",""+response);
                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void parseJSON(String response) {


        try {
            jsonResponse = new JSONObject(response);
            // Création du tableau général à partir d'un JSONObject
            JSONArray jsonArray = jsonResponse.getJSONArray("data");
            Events currentEvent = null;

            // Pour chaque élément du tableau
            for (int i = 0; i < jsonArray.length(); i++) {
                currentEvent = new Events();

                // Création d'un tableau élément à  partir d'un JSONObject
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                // Récupération à partir d'un JSONObject nommé
                //JSONObject fields  = jsonObj.getJSONObject("fields");

                // Récupération de l'item qui nous intéresse
                String nom = jsonObj.getString("nom");
                String type = jsonObj.getString("type");
                String date = jsonObj.getString("date");
                String place = jsonObj.getString("type");

                currentEvent.setNom(nom);
                currentEvent.setType(type);
                currentEvent.setDate(date);
                currentEvent.setPlace(place);

                // Ajout dans l'ArrayList
                items.add(currentEvent);
            }

            ArrayAdapter<Events> objAdapter = new EventsAdapter(this, R.layout.row, items);
            lv.setAdapter(objAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

