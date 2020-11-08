package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.Presenter.UserPresenter;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.Service.ReceptorConectividad;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;

public class MainActivity extends Activity implements UserView, ReceptorConectividad.ConnectivityReceiverListener {

    RecyclerView recyclerViewSearchResults;
    ArrayList<UserModel> listUser;
    UserAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listUser = new ArrayList<>();
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
        checkConnection();
    }

    @Override
    public void userReady(ArrayList<UserModel> userModels) {
        userListAdapter = new UserAdapter(MainActivity.this,userModels);
        recyclerViewSearchResults.setAdapter(userListAdapter);
    }

    private  void checkConnection(){
        boolean isConnect = ReceptorConectividad.isConnected(this);
        showSnack(isConnect);
    }
    private void showSnack(boolean isConnected) {
        if (isConnected) {
            boolean isDataNetwork = ReceptorConectividad.isOnlineNet();
            if (isDataNetwork) {
                UserPresenter userPresenter = new UserPresenter(this);
                userPresenter.getData();
            } else {
                Toast.makeText(this, "No tiene internet", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No tiene internet", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}