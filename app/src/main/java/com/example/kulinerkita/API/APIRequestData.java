package com.example.kulinerkita.API;

import com.example.kulinerkita.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse>ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse>ardcreate(
            @Field("nama")String nama,
            @Field("asal")String asal,
            @Field("deskripsi_singkat")String deskripsi_singkat
    );


    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse>ardupdate(
            @Field("id")String id,
            @Field("nama")String nama,
            @Field("asal")String asal,
            @Field("deskripsi_singkat")String deskripsi_singkat
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse>arddelete(
            @Field("id")String id
    );

}
