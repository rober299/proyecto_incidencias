package com.example.gestionitmovil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            // VALIDACIONES REALES
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Introduce un email corporativo válido");
            } else if (pass.length() < 4) {
                etPassword.setError("La contraseña debe tener al menos 4 caracteres");
            } else {
                // Si todo es correcto, entramos
                Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                startActivity(intent);
                finish(); // Cerramos el login para que no puedan volver atrás
            }
        });
    }
}