package co.com.ceiba.mobile.pruebadeingreso.Service;

import co.com.ceiba.mobile.pruebadeingreso.Util.Endpoints;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserService {

    private Retrofit retrofit = null;
    //Instancia de variables de acceso al servicio,
    Endpoints ENDPOINTS = new Endpoints();

    public  DataInterface getUserData(){
        if(retrofit == null) {
            retrofit = new  Retrofit
                    .Builder()
                    .baseUrl(ENDPOINTS.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(DataInterface.class);
    }
}

