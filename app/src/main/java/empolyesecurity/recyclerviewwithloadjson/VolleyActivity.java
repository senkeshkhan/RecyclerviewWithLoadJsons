package empolyesecurity.recyclerviewwithloadjson;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import empolyesecurity.recyclerviewwithloadjson.dp.SQLiteHelper;

public class VolleyActivity extends AppCompatActivity {

    List<VolleyBean> listVal;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    int firsttime=0;
    VolleyBean mov;
    String val="1";
    SQLiteHelper sQLiteHelper;
    String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=ec01f8c2eb6ac402f2ca026dc2d9b8fd&language=en_US&" + "page="+val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        sQLiteHelper = new SQLiteHelper(VolleyActivity.this);
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());




        if (App.checkConnection(VolleyActivity.this)) {
            // Its Available...
            volleyJsonParse();
            Toast.makeText(VolleyActivity.this, "Available", Toast.LENGTH_SHORT).show();


        } else {
            // Not Available...
            databaseValues();


            Toast.makeText(VolleyActivity.this, "Not Available", Toast.LENGTH_SHORT).show();
        }
    }



public void databaseValues(){

    List<VolleyBean> contacts = sQLiteHelper.getAllRecords();
    System.out.println("valuessssssssssss+"+contacts);
    if (contacts.size() > 0) {



        VolleyAdapter adapter = new VolleyAdapter(VolleyActivity.this,contacts);
        recyclerView.setAdapter(adapter);
    }
}


    public void volleyJsonParse() {
       /* String url = "";
        Uri.Builder builder = Uri.parse(url).buildUpon();

        builder.appendQueryParameter("", "");
        builder.appendQueryParameter("", "");*/
       final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());

                       try {
                           sQLiteHelper.deleteAllRecordsAlternate();
                            JSONArray jsonArray = response.getJSONArray("results");
                            listVal    = new ArrayList<>();

                            for (int i = 0; i <jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                 mov = new VolleyBean();
                                mov.setName(jsonObject.getString("title"));
                                mov.setDate(jsonObject.getString("vote_count"));
                                mov.setImageurl(jsonObject.getString("poster_path"));

                                VolleyBean movie = new VolleyBean(jsonObject.getString("title"),jsonObject.getString("poster_path"),jsonObject.getString("vote_count"));

                                System.out.println("11111111111" + jsonObject.getString("title") + "dk" + jsonArray.length());



                               // sQLiteHelper.updateRecord(mov);
                                //sQLiteHelper.deleteRecord(mov);

                               sQLiteHelper.insertRecord(mov);
                                listVal.add(movie);



                            }






                          /* for(int i=0; i<listVal.size(); i++) {

                               sQLiteHelper.insertRecord(mov);
                           }*/






                            VolleyAdapter adapter = new VolleyAdapter(VolleyActivity.this,listVal);
                            recyclerView.setAdapter(adapter);




                        } catch (Exception e) {

                        }

                        pDialog.cancel();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("err", "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.dismiss();
            }
        });

// Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);


    }

}
