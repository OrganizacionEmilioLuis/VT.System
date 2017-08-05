package vt.system.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import vt.system.R;

/**
 * Created by Miler Pacheco 3/31/17.
 */

public class DetailActivity extends AppCompatActivity {

    ImageView iv_imagen_smartphone;
    TextView textViewNombre_Cliente,textViewTelefono_Cliente,textViewMarca_Smartphone,textViewModelo_Smartphone,textViewImei1_Smartphone,textViewImei2_Smartphone,textViewObservacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iv_imagen_smartphone = (ImageView) findViewById(R.id.iv_imagen_smartphone);
        textViewNombre_Cliente = (TextView) findViewById(R.id.textViewNombre_Cliente);
        textViewTelefono_Cliente = (TextView) findViewById(R.id.textViewTelefono_Cliente);
        textViewMarca_Smartphone = (TextView) findViewById(R.id.textViewMarca_Smartphone);
        textViewModelo_Smartphone = (TextView) findViewById(R.id.textViewModelo_Smartphone);
        textViewImei1_Smartphone = (TextView) findViewById(R.id.textViewImei1_Smartphone);
        textViewImei2_Smartphone = (TextView) findViewById(R.id.textViewImei2_Smartphone);
        textViewObservacion = (TextView) findViewById(R.id.textViewObservacion);

        String Imagen_smartphone = getIntent().getExtras().getString("imagen_smartphone");
        String Nombre_cliente = getIntent().getExtras().getString("nombre_cliente");
        String Telefono_cliente = getIntent().getExtras().getString("telefono_cliente");
        String Marca_smartphone = getIntent().getExtras().getString("marca_smartphone");
        String Modelo_smartphone = getIntent().getExtras().getString("modelo_smartphone");
        String Imei1_smartphone = getIntent().getExtras().getString("imei1_smartphone");
        String Imei2_smartphone = getIntent().getExtras().getString("imei2_smartphone");
        String Observacion = getIntent().getExtras().getString("observacion");


        textViewNombre_Cliente.setText(Nombre_cliente);
        textViewTelefono_Cliente.setText(Telefono_cliente);
        textViewMarca_Smartphone.setText(Marca_smartphone);
        textViewModelo_Smartphone.setText(Modelo_smartphone);
        textViewImei1_Smartphone.setText(Imei1_smartphone);
        textViewImei2_Smartphone.setText(Imei2_smartphone);
        textViewObservacion.setText(Observacion);
        Glide.with(this)
                .load(Imagen_smartphone)
                .placeholder(R.drawable.load)
                .into(iv_imagen_smartphone);


        getSupportActionBar().setTitle("Details Activity");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
