package com.example.comprademedicina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comprademedicina.Elementos.ListElement;

import java.util.List;

public class Medicina_Detalle extends AppCompatActivity {
    TextView nombre;
    TextView desc;
    TextView precio;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicina_detalle);

        ListElement element=(ListElement)getIntent().getSerializableExtra("ListElement");
        img=findViewById(R.id.iconImageView);
        nombre=findViewById(R.id.medicinaNombre);
        desc=findViewById(R.id.medicinaDescripcion);
        precio=findViewById(R.id.medicinaPrecio);

        nombre.setText(element.getNombre());
        desc.setText(element.getDescripcion());
        precio.setText(element.getPrecio());
        img.setImageResource(element.getImgMedicina());
    }

    //Manda a la pantalla de todos los medicamentos
    public void Inicio(View v){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //Manda a la pantalla del carrito de compras
    public void Vercarro(View v){
        Intent i=new Intent(this, Medicina_Carro.class);
        startActivity(i);
    }
}