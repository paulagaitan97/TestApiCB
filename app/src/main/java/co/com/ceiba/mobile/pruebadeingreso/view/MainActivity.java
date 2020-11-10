package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.room.Room;
import java.util.ArrayList;
import co.com.ceiba.mobile.pruebadeingreso.Presenter.UserPresenter;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.Util.DataBase;
import co.com.ceiba.mobile.pruebadeingreso.Util.Utilities;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;

public class MainActivity extends Activity implements UserView, Utilities.ConnectivityReceiverListener {

    RecyclerView recyclerViewSearchResults;
    UserAdapter userListAdapter;
    EditText editTextSearch;
    ArrayList<UserModel> listSearch;
    ProgressDialog dialogLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listSearch = new ArrayList<>();
        dialogLoading = new ProgressDialog(this);
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence paramList, int start, int before, int count) {
                if(paramList.length() > 0 ) {
                    UserPresenter userPresenter = new UserPresenter(MainActivity.this);
                    userPresenter.filterData(paramList.toString().toLowerCase(), MainActivity.this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dialogLoading.setIcon(R.mipmap.ic_launcher);
        dialogLoading.setMessage("Cargando...");
        dialogLoading.setCancelable(false);
        dialogLoading.show();
        checkConnection();
    }

    @Override
    public void userReady(ArrayList<UserModel> userModels) {
        userListAdapter = new UserAdapter(this, userModels);
        recyclerViewSearchResults.setAdapter(userListAdapter);
        dialogLoading.dismiss();

    }


    /*
    Metodo que comprueba la conexion de internet
     */
    private  void checkConnection(){
        DataBase db = Room.databaseBuilder(this, DataBase.class, "databaseceiba").allowMainThreadQueries().build();
        int numRecords = db.userDao().numberRecords();
        if(numRecords >0){
            UserPresenter userPresenter = new UserPresenter(this);
            userPresenter.getData(MainActivity.this);
            dialogLoading.dismiss();
        }else {
            boolean isConnect = Utilities.isConnected(this);
            showSnack(isConnect);
        }
    }
    private void showSnack(boolean isConnected) {
        if (isConnected) {
            boolean isDataNetwork = Utilities.isOnlineNet();
            if (isDataNetwork) {
                UserPresenter userPresenter = new UserPresenter(this);
                userPresenter.getData(MainActivity.this);
            } else {
                dialogLoading.dismiss();
                Toast.makeText(this, "No tiene internet", Toast.LENGTH_SHORT).show();
            }
        } else {
            dialogLoading.dismiss();
            Toast.makeText(this, "No tiene red móvil o red wifi activa.", Toast.LENGTH_SHORT).show();
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