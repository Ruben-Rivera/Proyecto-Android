package com.example.comprademedicina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Medicina_Carro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicina_carro);
    }

    public void Inicio(View v){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }
}