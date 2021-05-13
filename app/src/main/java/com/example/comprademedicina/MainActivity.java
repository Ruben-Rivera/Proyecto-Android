package com.example.comprademedicina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.comprademedicina.Connection.ConnectionClass;
import com.example.comprademedicina.Elementos.ListAdapter;
import com.example.comprademedicina.Elementos.ListElement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Connection con;
    EditText medicina, resul;
    String ConnectionResult="";
    List<ListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicina_buscar);

        //Prueba del Spinner
        /*String[]opciones={"Antiácidos y antiulcerosos","Antialérgicos","Antidiarreicos y laxantes","Antiinfecciosos","Antiinflamatorios","Antipiréticos","Antitusivos y mucolíticos"};
        spinner=(Spinner)findViewById(R.id.tipoMedicina);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.config_spinner);
        List<CharSequence> adapter=new List();
        spinner.setAdapter(adapter);*/
        medicina=(EditText)findViewById(R.id.edtMedicina);
        spinner=(Spinner)findViewById(R.id.tipoMedicina);
        LlenarSpinner();
        init();
    }

    public void LlenarSpinner(){
        try{
            con = connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
            String query ="select * from Productos";
            PreparedStatement stat = con.prepareStatement(query);
            ResultSet rs = stat.executeQuery();

            ArrayList<String> data = new ArrayList<String>();
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                data.add(tipo);
            }
            ArrayAdapter array = new ArrayAdapter(this, R.layout.config_spinner, data);
            spinner.setAdapter(array);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    //conexion al SQL Server
    @SuppressLint("NewApi")
    public Connection connectionClass(String user, String password, String database, String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server+"/" + database + ";user=" + user + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectionURL);
        }catch (Exception e){
            Log.e("SQL Connection Error : ", e.getMessage());
        }
        return connection;

    }
    //Manda a la pantalla del carrito de compras
    public void Vercarro(View v){
        Intent i=new Intent(this, Medicina_Carro.class);
        startActivity(i);
    }

    public void init(){
        elements=new ArrayList<>();
        elements.add(new ListElement("#ED538E", "Acetaminofen", "Si", "$7.5"));
        elements.add(new ListElement("#ED538E", "Pythonb", "Si", "$7.5"));
        elements.add(new ListElement("#ED538E", "sdfsdfn", "Si", "$7.5"));
        elements.add(new ListElement("#ED538E", "sdfsdfsdfn", "Si", "$7.5"));

        ListAdapter listAdapter=new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView=findViewById(R.id.rvMedicina);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void moveToDescription(ListElement item){
        Intent intent=new Intent(this, Medicina_Detalle.class);
        intent.putExtra("ListElement",item);
        startActivity(intent);
    }
}