package app.tumblrviewer;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by seba on 03.11.15.
 */
public class AsyncGET extends AsyncTask<String, Void, String> {

    private Context context;

    private String url;

    private StringBuilder lastResponse;

    private Handler handler;

    private String postNumbersToGet;

    public AsyncGET(Context context, String url, Handler handler, String posts){

        this.context = context;

        this.url = url;

        this.handler = handler;

        this.postNumbersToGet = posts;

    }

    @Override
    protected String doInBackground(String... params) {

        lastResponse = new StringBuilder("");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        String uri = String.format(url + "?num=%1$s", postNumbersToGet);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        PostListView.lastXML = new XMLParser(response);

                        Message m = new Message();
                        Bundle b = new Bundle();
                        b.putString("result", "OK");
                        m.setData(b);
                        handler.sendMessage(m);



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast toast = Toast.makeText(context, "Did not work - try again", Toast.LENGTH_SHORT);
                toast.show();

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


        return "";

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


    }
}
