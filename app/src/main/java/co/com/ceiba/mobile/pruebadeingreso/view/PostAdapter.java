package co.com.ceiba.mobile.pruebadeingreso.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserPostModel;
import co.com.ceiba.mobile.pruebadeingreso.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    // contexto de la vista
    private Context context;
    // Lista de usuarios original del web services
    final ArrayList<UserPostModel> listUserPosts;

    // Constructor de la clase
    public PostAdapter(Context context, ArrayList<UserPostModel> listUserPosts) {
        this.context = context;
        this.listUserPosts = listUserPosts;
    }

    @NonNull
    @Override
    //Este método se llama conjuntamente cuando se crea el adaptador y se usa para inicializar el ViewHolder.
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewDetail = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_list_item, viewGroup, false);
        return new PostAdapter.ViewHolder(viewDetail);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }

    @Override
    //Este método se llama para cada ViewHolder vinculado al adaptador.
    // Aquí es donde se envian los datos al ViewHolder.
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(listUserPosts.get(i).getTitle());
        viewHolder.body.setText(listUserPosts.get(i).getBody());
    }

    //Este método devuelve la cantidad de elementos que contiene la lista..
    @Override
    public int getItemCount() {
        return listUserPosts.size();
    }


}
