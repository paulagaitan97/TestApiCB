package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserPostModel;
import co.com.ceiba.mobile.pruebadeingreso.Presenter.PostPresenter;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.Util.Utilities;

public class PostActivity extends Activity implements PostView, Utilities.ConnectivityReceiverListener{

    UserModel detailUser = null;
    TextView name,phone,email;
    RecyclerView recyclerViewPostsResults;
    PostAdapter postAdapter;
    ProgressDialog dialogLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        dialogLoading = new ProgressDialog(this);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        recyclerViewPostsResults = findViewById(R.id.recyclerViewPostsResults);
        recyclerViewPostsResults.setLayoutManager(new LinearLayoutManager(this));
        detailUser = (UserModel) getIntent().getSerializableExtra("DetailUser");
        dialogLoading.setIcon(R.mipmap.ic_launcher);
        dialogLoading.setMessage("Cargando...");
        dialogLoading.setCancelable(false);
        dialogLoading.show();
    }



    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    public  void loadData(){
        name.setText(detailUser.getName());
        phone.setText(detailUser.getPhone());
        email.setText(detailUser.getEmail());

    }


    @Override
    public void postReady(ArrayList<UserPostModel> userPostModels) {
        postAdapter = new PostAdapter(PostActivity.this,userPostModels);
        recyclerViewPostsResults.setAdapter(postAdapter);
        dialogLoading.dismiss();
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
                PostPresenter postPresenter = new PostPresenter(this, detailUser.getId());
                postPresenter.getPostData();

            } else {
                dialogLoading.dismiss();
                Toast.makeText(this, "No tiene conexión a internet", Toast.LENGTH_SHORT).show();
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
