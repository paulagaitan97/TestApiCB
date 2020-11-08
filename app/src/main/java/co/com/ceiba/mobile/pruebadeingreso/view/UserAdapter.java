package co.com.ceiba.mobile.pruebadeingreso.view;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;
import co.com.ceiba.mobile.pruebadeingreso.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    // contexto de la vista
    private Context context;
    final ArrayList<UserModel> listUsers;

    // Constructor de la clase
    public UserAdapter(Context context, ArrayList<UserModel> listUsers) {
        this.context = context;
        this.listUsers = listUsers;
    }

    @NonNull
    @Override
    //Este método se llama conjuntamente cuando se crea el adaptador y se usa para inicializar el ViewHolder.
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewDetail = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        return new UserAdapter.ViewHolder(viewDetail);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
        }
    }

    @Override
    //Este método se llama para cada ViewHolder vinculado al adaptador.
    // Aquí es donde se envian los datos al ViewHolder.
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder viewHolder, int i) {
        viewHolder.name.setText(listUsers.get(i).getName());
        viewHolder.phone.setText(listUsers.get(i).getPhone());
        viewHolder.email.setText(listUsers.get(i).getEmail());
    }

    //Este método devuelve la cantidad de elementos que contiene la lista..
    @Override
    public int getItemCount() {
        return listUsers.size();
    }
}


