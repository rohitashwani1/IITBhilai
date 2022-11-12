package com.example.iitbhilai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class messdio extends AppCompatActivity {
    private JSONArray s;
    private Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messdio);
        but = (Button)findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(messdio.this,fullmessdio.class));
            }
        });
        loadmenu();
    }
    protected void loadmenu(){
        final TextView b1 = (TextView) findViewById(R.id.textView5);
        final TextView b2 = (TextView) findViewById(R.id.textView7);
        final TextView l1 = (TextView) findViewById(R.id.textView6);
        final TextView l2 = (TextView) findViewById(R.id.textView8);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://script.googleusercontent.com/macros/echo?user_content_key=YGGh0YPQgg4Yxy4870YgzEGtfSoGbVuazg3LfectTa0Kd3TXNtV9SD6eS__oKPMxDUI5N1o0xGPUMP05QQATkvsBxlOW2cr5m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnH8TPnnTLNvlCsvVPQpi8ZGYz5h2RFutQwTnq6qokUNN1d5fsZnobFtpxea5ZoRmL2T2JNytwpbU8054lgY35WNYAgO4dxHm99z9Jw9Md8uu&lib=MfUTt2aPnyHxBtnRqYf_t75AWDXhq12_r";
        JsonObjectRequest js = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject r) {
                        try {
                            s = r.getJSONArray("data");
                            b1.setText(s.getJSONObject(0).getString("tb1"));
                            b2.setText(s.getJSONObject(0).getString("tb2"));
                            l1.setText(s.getJSONObject(0).getString("tl1"));
                            l2.setText(s.getJSONObject(0).getString("tl2"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                b1.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(js);
    }
}