package com.adityadharma.hmovie.API;

import com.adityadharma.hmovie.AmbilData.Example;
import retrofit2.Call;
import retrofit2.http.GET;



public interface TampilanAPI {

    public static String DB_API="6bd1a8535e07f4a3a42327289b09af55";

//    @GET("popular?api_key="+DB_API)
//    Call<Dates>getDates();

//    @GET("popular?api_key="+DB_API)
//    Call<Example>getPopular();

    @GET("top_rated?api_key="+DB_API)
    Call<Example>getRated();

}
