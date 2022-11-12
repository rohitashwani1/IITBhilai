package com.example.iitbhilai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class fullmessdio extends AppCompatActivity {
    private JSONArray s;
    private String[] arr1 = {"Monday","Monday","Tuesday","Tuesday","Wednesday","Wednesday","Thursday","Thursday","Friday","Friday","Saturday","Saturday","Sunday","Sunday"};
    private String[] arr2 = new String[14];
    private String[] arr3 = new String[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullmessdio);
        loadmenu();
    }

    protected void loadmenu() {
        final ListView l1 = findViewById(R.id.l1);
        final ListView l2 = findViewById(R.id.l2);
        final ListView l3 = findViewById(R.id.l3);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://script.googleusercontent.com/macros/echo?user_content_key=YGGh0YPQgg4Yxy4870YgzEGtfSoGbVuazg3LfectTa0Kd3TXNtV9SD6eS__oKPMxDUI5N1o0xGPUMP05QQATkvsBxlOW2cr5m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnH8TPnnTLNvlCsvVPQpi8ZGYz5h2RFutQwTnq6qokUNN1d5fsZnobFtpxea5ZoRmL2T2JNytwpbU8054lgY35WNYAgO4dxHm99z9Jw9Md8uu&lib=MfUTt2aPnyHxBtnRqYf_t75AWDXhq12_r";
        JsonObjectRequest js = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject r){
                        try {
                            Toast.makeText(fullmessdio.this, "hua hua", Toast.LENGTH_SHORT).show();
                            s = r.getJSONArray("data");
                            int k = 0;
                            for (int i = 0; i < 7; i++){
                                arr2[k] = s.getJSONObject(i+1).getString("tb1");
                                arr3[k] = s.getJSONObject(i+1).getString("tl1");
                                k++;
                                arr2[k] = s.getJSONObject(i+1).getString("tb2");
                                arr3[k] = s.getJSONObject(i+1).getString("tl2");
                                k++;
                            }
                            ArrayAdapter<String> ad1 = new ArrayAdapter<String>(fullmessdio.this, android.R.layout.simple_list_item_1,arr1);
                            ArrayAdapter<String> ad2 = new ArrayAdapter<String>(fullmessdio.this, android.R.layout.simple_list_item_1,arr2);
                            ArrayAdapter<String> ad3 = new ArrayAdapter<String>(fullmessdio.this, android.R.layout.simple_list_item_1,arr3);
                            l1.setAdapter(ad1);
                            l2.setAdapter(ad2);
                            l3.setAdapter(ad3);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(fullmessdio.this, "Unable to load", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(js);
    }
}