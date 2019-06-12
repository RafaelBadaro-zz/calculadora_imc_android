package pucminas.br.aula1_primeiroapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Double> user = new HashMap<>();

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
        user.put("peso", peso);
        user.put("altura", altura);
        user.put("imc", imc);
        db.collection("IMC - usuarios")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(getApplicationContext(),
                                    "Dados salvos com sucesso",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "Erro ao salvar dados",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
