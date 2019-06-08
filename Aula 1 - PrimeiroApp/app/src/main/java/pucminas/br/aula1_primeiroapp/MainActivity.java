package pucminas.br.aula1_primeiroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CalcularIMC(View view){
        TextView txtIMC = findViewById(R.id.textView3);
        EditText edtPeso = findViewById(R.id.editText);
        EditText edtAltura = findViewById(R.id.editText2);
        double peso = Double.valueOf(edtPeso.getText().toString());
        double altura = Double.parseDouble(edtAltura.getText().toString())/100;
        double imc = Math.round(peso/(altura*altura));
        txtIMC.setText(String.valueOf(imc));
    }
}
