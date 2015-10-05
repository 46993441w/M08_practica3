package com.example.david.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public EditText etPreu;
    public EditText etEstalvis;
    public EditText etAnys;
    public EditText etEuribor;
    public EditText etDiferencial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPreu = (EditText) findViewById(R.id.etPreu);
        etEstalvis = (EditText) findViewById(R.id.etEstalvi);
        etAnys = (EditText) findViewById(R.id.etAnys);
        etEuribor = (EditText) findViewById(R.id.etEuribor);
        etDiferencial = (EditText) findViewById(R.id.etDiferencial);

        afegirTextWatcher();
    }

    private void afegirTextWatcher() {
        TextWatcher myTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!etPreu.getText().toString().isEmpty() && !etEstalvis.getText().toString().isEmpty() && !etAnys.getText().toString().isEmpty() && !etEuribor.getText().toString().isEmpty() && !etDiferencial.getText().toString().isEmpty() ){
                    calcularHipoteca();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };
        etPreu.addTextChangedListener( myTextWatcher );
        etEstalvis.addTextChangedListener (myTextWatcher );
        etAnys.addTextChangedListener( myTextWatcher );
        etEuribor.addTextChangedListener( myTextWatcher );
        etDiferencial.addTextChangedListener( myTextWatcher );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calcularHipoteca() {
        int preu = Integer.parseInt(etPreu.getText().toString());
        int estalvi = Integer.parseInt(etEstalvis.getText().toString());
        int anys = Integer.parseInt(etAnys.getText().toString());
        double euribor = Double.parseDouble(etEuribor.getText().toString());
        double diferencial = Double.parseDouble(etDiferencial.getText().toString());
        calculMes(preu, estalvi, anys, euribor, diferencial);
    }
    public void calculMes(int preu, int estalvi, int anys, double euribor, double diferencial){
        double i = euribor + diferencial;
        double intRate = ( i / 100 ) / 12;
        int capital = preu - estalvi;
        int mes = anys * 12;

        double cuota = Math.floor( ( capital * intRate ) / ( 1 - Math.pow( 1 + intRate, ( -1 * mes ) ) ) * 100 ) / 100;
        TextView txtMesResult = (TextView) findViewById(R.id.txtMesResultat);
        txtMesResult.setText(String.valueOf(cuota)+"€");
        calculTotal(cuota,mes);
    }
    public void calculTotal(double mes,int meses){
        double total = Math.floor( mes * meses * 100 ) / 100;
        TextView txtTotalResult = (TextView) findViewById(R.id.txtTotalResultat);
        txtTotalResult.setText(String.valueOf(total)+"€");
    }

    public void calcularHipoteca(View view) {
        calcularHipoteca();
    }
}
