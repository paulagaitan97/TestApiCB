package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.Presenter.UserPresenter;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.Util.Utilities;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;

public class MainActivity extends Activity implements UserView, Utilities.ConnectivityReceiverListener {

    RecyclerView recyclerViewSearchResults;
    UserAdapter userListAdapter;
    EditText editTextSearch;
    ArrayList<UserModel> listSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listSearch = new ArrayList<>();
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence paramList, int start, int before, int count) {
                userListAdapter = new UserAdapter(MainActivity.this,listSearch);
                userListAdapter.filterData(paramList.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        checkConnection();
    }

    @Override
    public void userReady(ArrayList<UserModel> userModels) {
        userListAdapter = new UserAdapter(MainActivity.this,userModels);
        recyclerViewSearchResults.setAdapter(userListAdapter);
        listSearch.addAll(userListAdapter.listUsersOriginal);

    }


    /*
    Metodo que comprueba la conexion de internet
     */
    private  void checkConnection(){
        boolean isConnect = Utilities.isConnected(this);
        showSnack(isConnect);
    }
    private void showSnack(boolean isConnected) {
        if (isConnected) {
            boolean isDataNetwork = Utilities.isOnlineNet();
            if (isDataNetwork) {
                UserPresenter userPresenter = new UserPresenter(this);
                userPresenter.getData(MainActivity.this);

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