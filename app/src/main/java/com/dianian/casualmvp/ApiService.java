package com.dianian.casualmvp;



import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {


    @GET("HotPlayMovies.api")
    Observable<ResponseBody> Test(@Query("locationId") String locationId);
}
