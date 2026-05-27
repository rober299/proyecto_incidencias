package com.example.gestionitmovil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

public class CreacionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion);

        // Flecha de volver atrás
        Toolbar toolbar = findViewById(R.id.toolbarCreacion);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Botón guardar
        findViewById(R.id.btnGuardar).setOnClickListener(v -> {
            Toast.makeText(this, "Incidencia guardada", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}