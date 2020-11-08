package co.com.ceiba.mobile.pruebadeingreso.Service;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserPostModel;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataInterface {
    //Instancia de variables de acceso al servicio,
    Endpoints ENDPOINTS = new Endpoints();

    /*
    Metodo encargado de obtener la información de los usuarios
    que se encuentran en el servicio web.
     */
    @GET("/users")
    Call<List<UserModel>> getUsers();

    /*
   Metodo encargado de obtener la información de los post
   de cada usuario que se encuentran en el servicio web.
    */
    @GET("/posts")
    Call<List<UserPostModel>> getPost(@Query("userId") int userCode);
}
