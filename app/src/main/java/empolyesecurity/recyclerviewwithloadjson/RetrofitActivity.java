package empolyesecurity.recyclerviewwithloadjson;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import empolyesecurity.recyclerviewwithloadjson.api.MovieApi;
import empolyesecurity.recyclerviewwithloadjson.api.MovieService;
import empolyesecurity.recyclerviewwithloadjson.modelpojo.Movies;
import empolyesecurity.recyclerviewwithloadjson.modelpojo.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {


    MovieService movieService;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);


        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        movieService = MovieApi.getClient().create(MovieService.class);

        retrofitJsonParse();




    }

    public void retrofitJsonParse(){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        moviesCall().enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Log.e("Success", new Gson().toJson(response.body()));

                List<Result> movies = response.body().getResults();
                // Movies resource = response.body();
                //  int statusCode = response.code();

                recyclerView.setAdapter(new RetrofitAdapter(movies, R.layout.card_view, getApplicationContext()));


                pDialog.cancel();

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

                pDialog.dismiss();
            }
        });
    }
    private Call<Movies> moviesCall(){

        return movieService.getTopRatedMovies("ec01f8c2eb6ac402f2ca026dc2d9b8fd","en_US",1);
    }
}
