package co.com.ceiba.mobile.pruebadeingreso.Presenter;

import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;
import co.com.ceiba.mobile.pruebadeingreso.Service.UserService;
import co.com.ceiba.mobile.pruebadeingreso.view.UserView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserPresenter {
    private UserView userView;
    private UserService userService;
    ArrayList<UserModel> userModels;

    public UserPresenter(UserView view){
        this.userView = view;
        if(this.userService == null) {
            this.userService = new UserService();
        }
    }

    public void getData() {
        userService
                .getUserData()
                .getUsers()
                .enqueue(new Callback<List<UserModel>>() {
                    @Override
                    public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                        userModels = new ArrayList<>(response.body());
                        userView.userReady(userModels);
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
