package co.com.ceiba.mobile.pruebadeingreso.view;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;
import co.com.ceiba.mobile.pruebadeingreso.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    // contexto de la vista
    private Context context;
    // Lista de usuarios original del web services
    final  ArrayList<UserModel> listUsersOriginal;
    // Lista de usuarios parcial construida filtro ingresado
    final ArrayList<UserModel> listUsersPartial;
    private UserView userView;
    // Constructor de la clase
    public UserAdapter(Context context, ArrayList<UserModel> listUsersPartial) {
        this.context = context;
        this.listUsersPartial = listUsersPartial;
        this.listUsersOriginal = new ArrayList<>();
        listUsersOriginal.addAll(listUsersPartial);
    }


    /*
    Metodo encargado de realizar la busqueda por filtro, letra a letra
    ingresado por el usuario
     */
    public  void filterData(final String paramSearch){
        if(paramSearch.length() == 0) {
            listUsersPartial.clear();
            listUsersPartial.addAll(listUsersOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<UserModel> collectionsUser = listUsersOriginal.stream()
                        .filter(i -> i.getName().toLowerCase().contains(paramSearch))
                        .collect(Collectors.toList());
                listUsersPartial.clear();
                listUsersPartial.addAll(collectionsUser);
                Log.i("Example",""+listUsersPartial.size());
                for(UserModel um: listUsersPartial){
                    Log.i("Example2",""+um.getName());
                }
            }else{
                listUsersPartial.clear();
                for(UserModel um: listUsersOriginal){
                    if(um.getName().toLowerCase().contains(paramSearch)){
                        listUsersPartial.add(um);
                    }
                }
            }
        }
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
        Button btn_view_post;
        UserModel detailUser = null;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            btn_view_post = itemView.findViewById(R.id.btn_view_post);
            btn_view_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent viewMember = new Intent(v.getContext(), PostActivity.class);
                    viewMember.putExtra("DetailUser", detailUser);
                    v.getContext().startActivity(viewMember);
                }
            });
        }
    }

    @Override
    //Este método se llama para cada ViewHolder vinculado al adaptador.
    // Aquí es donde se envian los datos al ViewHolder.
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder viewHolder, int i) {
        viewHolder.name.setText(listUsersPartial.get(i).getName());
        viewHolder.phone.setText(listUsersPartial.get(i).getPhone());
        viewHolder.email.setText(listUsersPartial.get(i).getEmail());
        viewHolder.detailUser = new UserModel(listUsersPartial.get(i).getId(),listUsersPartial.get(i).getName(),listUsersPartial.get(i).getEmail(),listUsersPartial.get(i).getPhone());
    }

    //Este método devuelve la cantidad de elementos que contiene la lista..
    @Override
    public int getItemCount() {
        return listUsersPartial.size();
    }
}


