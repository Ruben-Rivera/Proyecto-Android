package com.example.comprademedicina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comprademedicina.Elementos.ListElement;

import java.util.List;

public class Medicina_Detalle extends AppCompatActivity {
    TextView nombre, desc, precio,cantid ;
    ImageView img;
    Button mas, menos;
    int Cantidad=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicina_detalle);

        ListElement element=(ListElement)getIntent().getSerializableExtra("ListElement");
        img=findViewById(R.id.iconImageView);
        nombre=findViewById(R.id.medicinaNombre);
        desc=findViewById(R.id.medicinaDescripcion);
        precio=findViewById(R.id.medicinaPrecio);
        mas=findViewById(R.id.mascantidad);
        menos=findViewById(R.id.menoscantidad);
        cantid=findViewById(R.id.txtCantidad);

        nombre.setText(element.getNombre());
        desc.setText(element.getDescripcion());
        precio.setText(element.getPrecio());
        img.setImageResource(element.getImgMedicina());

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cantidad++;
                cantid.setText(""+Cantidad);
            }
        });
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cantidad--;
                if(Cantidad<=0){
                    cantid.setText("0");
                    Cantidad=0;
                }
                else {
                    cantid.setText("" + Cantidad);
                }
            }
        });
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