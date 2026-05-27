package com.example.gestionitmovil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private Button btnLogin;
    // CORRECCIÓN: Puerto 8082 según tu Postman
    private static final String URL_LOGIN = "http://10.0.2.2:8082/api/v1/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("SesionIT", Context.MODE_PRIVATE);
        if (prefs.contains("TOKEN")) {
            startActivity(new Intent(this, ListadoActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Introduce un email válido");
                return;
            }
            if (pass.isEmpty()) {
                etPassword.setError("Contraseña requerida");
                return;
            }

            btnLogin.setText("CONECTANDO...");
            btnLogin.setEnabled(false);
            hacerLoginAPI(email, pass);
        });
    }

    private void hacerLoginAPI(String email, String pass) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                URL url = new URL(URL_LOGIN);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setConnectTimeout(3000);
                conn.setDoOutput(true);

                JSONObject jsonBody = new JSONObject();
                jsonBody.put("email", email);
                jsonBody.put("password", pass);

                OutputStream os = conn.getOutputStream();
                os.write(jsonBody.toString().getBytes("UTF-8"));
                os.close();

                int code = conn.getResponseCode();

                if (code == 200 || code == 201) { // Algunos backend devuelven 201 en login
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) response.append(line);
                    in.close();

                    JSONObject jsonResponse = new JSONObject(response.toString());
                    String tokenLimpio = jsonResponse.getString("token");

                    SharedPreferences prefs = getSharedPreferences("SesionIT", Context.MODE_PRIVATE);
                    prefs.edit().putString("TOKEN", tokenLimpio).apply();

                    handler.post(() -> {
                        Toast.makeText(MainActivity.this, "¡Conexión Exitosa!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, ListadoActivity.class));
                        finish();
                    });
                } else {
                    handler.post(() -> {
                        Toast.makeText(MainActivity.this, "Error: Credenciales incorrectas (" + code + ")", Toast.LENGTH_LONG).show();
                        restaurarBoton();
                    });
                }
            } catch (Exception e) {
                handler.post(() -> {
                    Toast.makeText(MainActivity.this, "Error de red: ¿Está el servidor encendido?", Toast.LENGTH_LONG).show();
                    restaurarBoton();
                });
            }
        });
    }

    private void restaurarBoton() {
        btnLogin.setText("INICIAR SESIÓN");
        btnLogin.setEnabled(true);
    }
}