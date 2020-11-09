package co.com.ceiba.mobile.pruebadeingreso.Presenter;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;
import co.com.ceiba.mobile.pruebadeingreso.Service.UserService;
import co.com.ceiba.mobile.pruebadeingreso.Util.DataBase;
import co.com.ceiba.mobile.pruebadeingreso.view.UserView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserPresenter {
    private UserView userView;
    private UserService userService;
    ArrayList<UserModel> userModels;
    ArrayList<UserModel> listUsersPartial;
    DataBase db;

    public UserPresenter(UserView view){
        this.userView = view;
        if(this.userService == null) {
            this.userService = new UserService();
        }
        userModels = new ArrayList<>();
        listUsersPartial = new ArrayList<>();

    }

    /**
     * Metodo encargado de obtener la lista de usuarios
     * almacenados en el web services
     * @param contextActivity
     * @Author Paula Gaitán
     */
    public void getData(Context contextActivity) {
        db = Room.databaseBuilder(contextActivity, DataBase.class, "databaseceiba").allowMainThreadQueries().build();
        int numRecords = db.userDao().numberRecords();
        if(numRecords >0){
            userModels.addAll(db.userDao().getAll());
            userView.userReady((ArrayList<UserModel>) userModels);
        }else {
            userService
                    .getUserData()
                    .getUsers()
                    .enqueue(new Callback<List<UserModel>>() {
                        @Override
                        public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                            userModels.addAll(response.body());
                            insertDataUser(contextActivity, userModels);
                        }

                        @Override
                        public void onFailure(Call<List<UserModel>> call, Throwable t) {
                            try {
                                throw new InterruptedException("!!Algo Salió mal!!");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    });
        }
    }


    /**
     * Metodo encargado de realizar la busqueda por filtro, letra a letra
     * ingresado por el usuario.
     * @param paramSearch parametro de busqueda de usuarios/usuario
     * @Author Paula Gaitán
     */

    public  void filterData(String paramSearch,Context contextActivity){
        db = Room.databaseBuilder(contextActivity, DataBase.class, "databaseceiba").allowMainThreadQueries().build();
        ArrayList<UserModel> lisUserOriginal = new ArrayList<>();
        lisUserOriginal.addAll(db.userDao().getAll());

        if(paramSearch.length() == 0) {
            listUsersPartial.clear();
            listUsersPartial.addAll(lisUserOriginal);
            userView.userReady(listUsersPartial);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<UserModel> collectionsUser = lisUserOriginal.stream()
                        .filter(i -> i.getName().toLowerCase().contains(paramSearch))
                        .collect(Collectors.toList());
                listUsersPartial.clear();
                listUsersPartial.addAll(collectionsUser);
            }else{
                listUsersPartial.clear();
                for(UserModel um: lisUserOriginal){
                    if(um.getName().toLowerCase().contains(paramSearch)){
                        listUsersPartial.add(um);
                    }
                }
            }
            userView.userReady(listUsersPartial);
        }
    }

    /**
     * Metodo encargado de insertar en la base de datos, verifica si el registro existe
     * @param contextActivity contexto de la actividad que lo llama
     * @param userModels lista de usuario del web services
     * @Author Paula Gaitan
     */
    public void insertDataUser(Context contextActivity, ArrayList<UserModel> userModels ){
        db = Room.databaseBuilder(contextActivity, DataBase.class, "databaseceiba").allowMainThreadQueries().build();
        for(UserModel newUser: userModels){
           UserModel findUser  = db.userDao().findByUserId(newUser.getId());
            if(findUser == null){
                db.userDao().insertNewUser(newUser);
            }
        }
        listDataUser(contextActivity);
    }

    /**
     * Metodo encargado de mostrar los registro de la base datos al usuario
     * @param contextActivity contexto de la actividad que lo llama
     * @Author Paula Gaitán
     */
    public  void listDataUser(Context contextActivity ){
        db = Room.databaseBuilder(contextActivity, DataBase.class, "databaseceiba").allowMainThreadQueries().build();
        List<UserModel> listUser = db.userDao().getAll();
        userView.userReady((ArrayList<UserModel>) listUser);
    }


}
