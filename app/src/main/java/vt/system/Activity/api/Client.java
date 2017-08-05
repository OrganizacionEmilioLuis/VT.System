package vt.system.Activity.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Miler Pacheco 3/31/17.
 */
public class Client {

    //public static final String BASE_URL = "http://192.168.137.1";
    public static final String BASE_URL = "https://emilioluisprogramadores.000webhostapp.com";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
