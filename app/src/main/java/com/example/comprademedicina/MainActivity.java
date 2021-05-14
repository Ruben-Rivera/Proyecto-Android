package com.example.comprademedicina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.comprademedicina.Elementos.ListAdapter;
import com.example.comprademedicina.Elementos.ListElement;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Connection con;
    AutoCompleteTextView txmedicina;
    ListAdapter listAdapter;
    String ConnectionResult = "";
    List<ListElement> elements;
    RadioButton rd1, rd2;
    ArrayAdapter<String>adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicina_buscar);

        init();
        rd1=(RadioButton)findViewById(R.id.rd1);
        rd2=(RadioButton)findViewById(R.id.rd2);
        rd1.setChecked(true);

        txmedicina = (AutoCompleteTextView)findViewById(R.id.txtMedicina);
        txmedicina.setThreshold(2);
        txmedicina.setAdapter(adap);


        txmedicina.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filtrar(s.toString());
            }
        });

        //Prueba del Spinner
        String[] opciones = new String[] {"Tipo de medicina","Antiácidos", "Antialérgicos", "Antidiarreicos", "Antiinfecciosos"};
        /*spinner = (Spinner) findViewById(R.id.tipoMedicina);*/
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, opciones);
       /* spinner.setAdapter(adap);
        //LlenarSpinner();*/
    }
    //Llenar spiner de base de datos
    /*public void LlenarSpinner(){
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
    }*/

    public void init() {
        elements = new ArrayList<>();
        elements.add(new ListElement(R.drawable.acetaminofen, "Acetaminofen", "Ayuda al dolor de cabeza", "$7.599", "Antiácidos"));
        elements.add(new ListElement(R.drawable.alkasetlzer, "Alkaseltzer", "Para la digestion", "$4.99", "Antialergico"));
        elements.add(new ListElement(R.drawable.panadol, "Panadol", "Para el dolor de cuerpo", "$7.55", "Antidiarreicos"));
        elements.add(new ListElement(R.drawable.virogripam, "Virogrip", "Ayuda al resfriado", "$2.54", "Antiinfecciosos"));

        listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rvMedicina);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void moveToDescription(ListElement item) {
        Intent intent = new Intent(this, Medicina_Detalle.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
    }

    //Filtrar por texto
    public void filtrar(String texto) {
        ArrayList<ListElement> filtrarLista = new ArrayList<>();
        for (ListElement medicina : elements) {
            if(rd1.isChecked()) {
                if (medicina.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                    filtrarLista.add(medicina);
                }
            }
            else if(rd2.isChecked()) {
                if (medicina.getTipo().toLowerCase().contains(texto.toLowerCase())) {
                    filtrarLista.add(medicina);
                }
            }
        }
        listAdapter.filtrar(filtrarLista);
    }
}