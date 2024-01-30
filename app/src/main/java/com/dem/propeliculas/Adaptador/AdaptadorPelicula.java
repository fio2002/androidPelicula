package com.dem.propeliculas.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.dem.propeliculas.Modelo.Pelicula;
import com.dem.propeliculas.R;


import java.text.BreakIterator;
import java.util.ArrayList;

public class AdaptadorPelicula extends RecyclerView.Adapter<AdaptadorPelicula.ViewHolder> {
    private ArrayList<Pelicula> data;
    private Context context;
    public  AdaptadorPelicula(Context context){
        this .context=context;
        data= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.elmentpeli,parent,false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pelicula p=data.get(position);
        holder.pos.setText(String.valueOf(position).toString());
        holder.id.setText(String.valueOf(p.getId()));
        holder.Nombre.setText(p.getOriginal_title());

        Glide.with(context).load("https://image.tmdb.org/t/p/original/"+
                p.getPoster_path()+".png" ).centerCrop().into(holder.imagen);
    }
    //En el método onBindViewHolder podrá asignarle al textview el indice de la pelicula:
    //        holder.txtpos.setText(String.valueOf(position).toString());+

    @Override
    public int getItemCount() {
        return data.size();
    }
    public  void adicionarRegistro(ArrayList<Pelicula>listap){
        data.addAll(listap);
        notifyDataSetChanged();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen ;
        private  TextView id;
        private TextView Nombre;
        private TextView pos;
        private TextView Date;
        private TextView Descripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id .imgPelicula);
            id= itemView.findViewById(R.id .txtIndice);
            Nombre = itemView.findViewById(R.id .txtNombre);
            pos=itemView.findViewById(R.id.txtPos);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posi = Integer.parseInt(pos.getText().toString());
                    Pelicula p=data.get(posi-1);
                    //--- Ver deatlle de un Registro
                    Context c=view.getContext();
                    Intent v=new Intent(c, Ver.class);
                    v.putExtra("codigo",pos);
                    v.putExtra("nombre",p.getName());
                    c.startActivity(v);



                }
            });*/

        }
    }
}