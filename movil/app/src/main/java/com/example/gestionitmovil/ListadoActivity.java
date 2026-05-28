package com.example.gestionitmovil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListadoActivity extends AppCompatActivity {

    private LinearLayout contenedorLista;
    private ProgressBar progressBar;
    private Button btnRefrescar;
    private static final String URL_LISTADO = "http://10.0.2.2:8082/api/v1/incidencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        contenedorLista = findViewById(R.id.contenedorLista);
        progressBar = findViewById(R.id.progressBar);
        btnRefrescar = findViewById(R.id.btnRefrescar);

        findViewById(R.id.btnIrPerfil).setOnClickListener(v -> startActivity(new Intent(this, PerfilActivity.class)));
        findViewById(R.id.fabNueva).setOnClickListener(v -> startActivity(new Intent(this, CreacionActivity.class)));

        // Lógica de refresco manual
        btnRefrescar.setOnClickListener(v -> cargarIncidenciasDesdeAPI());

        cargarCacheLocal();

        cargarIncidenciasDesdeAPI();
    }
    private void cargarCacheLocal() {
        SharedPreferences prefs = getSharedPreferences("SesionIT", Context.MODE_PRIVATE);
        String cacheJson = prefs.getString("CACHE_INCIDENCIAS", null);

        if (cacheJson != null) {
            try {
                JSONArray incidenciasArray = new JSONArray(cacheJson);
                renderizarIncidencias(incidenciasArray);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarIncidenciasDesdeAPI() {
        // Muestra la animación de carga antes de arrancar el hilo
        progressBar.setVisibility(View.VISIBLE);
        btnRefrescar.setEnabled(false);

        SharedPreferences prefs = getSharedPreferences("SesionIT", Context.MODE_PRIVATE);
        String token = prefs.getString("TOKEN", "");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                URL url = new URL(URL_LISTADO);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setConnectTimeout(3000);

                int code = conn.getResponseCode();
                if (code == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) response.append(line);
                    in.close();

                    String jsonRespuesta = response.toString();

                    prefs.edit().putString("CACHE_INCIDENCIAS", jsonRespuesta).apply();

                    JSONArray incidenciasArray = new JSONArray(jsonRespuesta);

                    // Volvemos al hilo principal para actualizar la UI
                    handler.post(() -> {
                        progressBar.setVisibility(View.GONE);
                        btnRefrescar.setEnabled(true);
                        renderizarIncidencias(incidenciasArray);
                    });
                } else {
                    handler.post(() -> {
                        progressBar.setVisibility(View.GONE);
                        btnRefrescar.setEnabled(true);
                        Toast.makeText(this, "Sesión caducada o error del servidor (" + code + ")", Toast.LENGTH_SHORT).show();
                    });
                }
            } catch (Exception e) {
                handler.post(() -> {
                    progressBar.setVisibility(View.GONE);
                    btnRefrescar.setEnabled(true);

                    if (prefs.contains("CACHE_INCIDENCIAS")) {
                        Toast.makeText(this, "Modo sin conexión: Mostrando caché local", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Fallo de conexión: ¿Servidor encendido?", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void renderizarIncidencias(JSONArray array) {
        contenedorLista.removeAllViews();
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                // 1. Tarjeta
                CardView card = new CardView(this);
                LinearLayout.LayoutParams paramsCard = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsCard.setMargins(0, 0, 0, 32);
                card.setLayoutParams(paramsCard);
                card.setRadius(24f);
                card.setCardElevation(8f);

                // 2. Contenedor interno
                LinearLayout inner = new LinearLayout(this);
                inner.setLayoutParams(new android.widget.FrameLayout.LayoutParams(
                        android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
                        android.widget.FrameLayout.LayoutParams.WRAP_CONTENT));
                inner.setOrientation(LinearLayout.VERTICAL);
                inner.setPadding(48, 48, 48, 48);

                // 3. Título
                TextView tvTitulo = new TextView(this);
                tvTitulo.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                tvTitulo.setText("#" + obj.getInt("id") + " - " + obj.getString("titulo"));
                tvTitulo.setTextSize(18f);
                tvTitulo.setTextColor(Color.parseColor("#1A237E"));
                tvTitulo.setTypeface(null, android.graphics.Typeface.BOLD);
                tvTitulo.setPadding(0, 0, 0, 16);

                // 4. Estado
                TextView tvEstado = new TextView(this);
                tvEstado.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                tvEstado.setText("Estado: " + obj.getString("estado"));
                tvEstado.setTextColor(Color.parseColor("#E53935"));
                tvEstado.setTextSize(16f);
                tvEstado.setTypeface(null, android.graphics.Typeface.BOLD);

                // 5. Ensamblar
                inner.addView(tvTitulo);
                inner.addView(tvEstado);
                card.addView(inner);
                contenedorLista.addView(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}