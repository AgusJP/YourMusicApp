package com.example.yourmusic.v1.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.yourmusic.v1.models.AlbumEntity;
import com.example.yourmusic.R;
import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>
        implements View.OnClickListener {

    private ArrayList<AlbumEntity> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class AlbumViewHolder
            extends RecyclerView.ViewHolder {

        private ImageView ImageView_profile;
        private TextView TextView_nameAlbum;
        private TextView TextView_nameArtist;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            ImageView_profile = (ImageView) itemView.findViewById(R.id.imageViewAlbum);
            TextView_nameAlbum = (TextView) itemView.findViewById(R.id.textViewNameAlbum);
            TextView_nameArtist = (TextView) itemView.findViewById(R.id.textViewNameArtist);
        }

        public void AlbumBind(AlbumEntity item) {
            //Faltaría asignar contenido al ImageView
            if(item.getImage()!=null && item.getImage()!="") {
                byte[] decodedString = Base64.decode(item.getImage(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                ImageView_profile.setImageBitmap(decodedByte);
                ImageView_profile.setBackground(null);
            }
            TextView_nameAlbum.setText(item.getAlbumName());
            TextView_nameArtist.setText(item.getArtistName());
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public AlbumAdapter(@NonNull ArrayList<AlbumEntity> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colección.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        row.setOnClickListener(this);

        AlbumViewHolder albumViewHolder= new AlbumViewHolder(row);
        return albumViewHolder;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(AlbumViewHolder viewHolder, int position) {
        AlbumEntity item = items.get(position);
        viewHolder.AlbumBind(item);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

}