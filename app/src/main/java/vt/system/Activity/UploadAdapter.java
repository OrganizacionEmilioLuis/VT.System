package vt.system.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import vt.system.Activity.model.registro;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.List;

import vt.system.Activity.model.registro;
import vt.system.R;

/**
 * Created by Miler Pacheco 3/31/17.
 */
public class UploadAdapter extends RecyclerView.Adapter<UploadAdapter.MyViewHolder> {

    private Context mContext;
    private List<registro> registroList;

    public UploadAdapter(Context mContext, List<registro> registroList) {
        this.mContext = mContext;
        this.registroList = registroList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.album_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    //Cargar el Cardview
    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int i) {
        viewHolder.cliente.setText(registroList.get(i).getNombre_cliente());
        viewHolder.marca.setText(registroList.get(i).getMarca_smartphone());

        //Cargar las fotos usando Glide
        Glide.with(mContext)
                .load(registroList.get(i).getImagen_smartphone())
                .placeholder(R.drawable.load)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.iv_imagen_smartphone_cardview);


    }

    @Override
    public int getItemCount() {
        return registroList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cliente, marca;
        public ImageView iv_imagen_smartphone_cardview;

        public MyViewHolder(View view) {
            super(view);
            cliente = (TextView) view.findViewById(R.id.cliente);
            marca = (TextView) view.findViewById(R.id.marca);
            iv_imagen_smartphone_cardview= (ImageView) view.findViewById(R.id.iv_imagen_smartphone_cardview);


            //En el artículo haga clic
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        registro clickedDataItem = registroList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("nombre_cliente", registroList.get(pos).getNombre_cliente());
                        intent.putExtra("telefono_cliente", registroList.get(pos).getTelefono_cliente());
                        intent.putExtra("marca_smartphone", registroList.get(pos).getMarca_smartphone());
                        intent.putExtra("modelo_smartphone", registroList.get(pos).getModelo_smartphone());
                        intent.putExtra("imei1_smartphone", registroList.get(pos).getImei1_smartphone());
                        intent.putExtra("imei2_smartphone", registroList.get(pos).getImei2_smartphone());
                        intent.putExtra("observacion", registroList.get(pos).getObservacion());
                        intent.putExtra("imagen_smartphone", registroList.get(pos).getImagen_smartphone());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "Cliente Registrado " + clickedDataItem.getNombre_cliente() + clickedDataItem.getMarca_smartphone(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
