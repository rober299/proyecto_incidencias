package com.example.gestionitmovil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetalleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        // Flecha de volver atrás
        Toolbar toolbar = findViewById(R.id.toolbarDetalle);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Botón de abajo (Marcar resuelta) también cierra la pantalla
        findViewById(R.id.btnVolver).setOnClickListener(v -> finish());
    }
}