package co.com.ceiba.mobile.pruebadeingreso.Presenter;

import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserPostModel;
import co.com.ceiba.mobile.pruebadeingreso.Service.PostService;
import co.com.ceiba.mobile.pruebadeingreso.view.PostView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostPresenter {
    private PostView postView;
    private PostService postService;
    private  int paramCodeUser;
    ArrayList<UserPostModel> userPostModels;

    public PostPresenter(PostView postView, int codeUser) {
        this.postView = postView;
        if(this.postService == null){
            this.postService = new PostService();
            this.paramCodeUser = codeUser;
        }
    }

    public void getPostData() {
        postService
                .getUserPostData()
                .getPost(this.paramCodeUser)
                .enqueue(new Callback<List<UserPostModel>>() {
                    @Override
                    public void onResponse(Call<List<UserPostModel>> call, Response<List<UserPostModel>> response) {
                        userPostModels = new ArrayList<>(response.body());
                        postView.postReady(userPostModels);
                    }

                    @Override
                    public void onFailure(Call<List<UserPostModel>> call, Throwable t) {
                        try {
                            throw new InterruptedException("!!Algo Salió mal!!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


}
