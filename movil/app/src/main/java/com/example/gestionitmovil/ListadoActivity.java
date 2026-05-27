package com.example.gestionitmovil;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;public class ListadoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        findViewById(R.id.btnIrPerfil).setOnClickListener(v -> startActivity(new Intent(this, PerfilActivity.class)));
        findViewById(R.id.fabNueva).setOnClickListener(v -> startActivity(new Intent(this, CreacionActivity.class)));

    }
}