package empolyesecurity.recyclerviewwithloadjson.api;




import empolyesecurity.recyclerviewwithloadjson.modelpojo.Movies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Pagination
 * Created by Suleiman19 on 10/27/16.
 * Copyright (c) 2016. Suleiman Ali Shakir. All rights reserved.
 */

public interface MovieService {

    @GET("movie/top_rated")
    Call<Movies> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex
    );

}
