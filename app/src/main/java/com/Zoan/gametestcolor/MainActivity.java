package com.Zoan.gametestcolor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView number1, number2, tiempo;
    ImageView imagen;
    List<Colors> colors;
    Integer contador1 =0, contador2 = 0;
    Integer val;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cronometro();

        recyclerView = findViewById(R.id.recyclerView);
        imagen = findViewById(R.id.imagen);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        tiempo = findViewById(R.id.tiempo);

        colors = new ArrayList<>();

        colors.add(new Colors(0, "#ff42a4"));
        colors.add(new Colors(1, "#532250"));
        colors.add(new Colors(2, "#af4d41"));
        colors.add(new Colors(3, "#872233"));
        colors.add(new Colors(4, "#5a94f4"));
        colors.add(new Colors(5, "#f1b93b"));
        colors.add(new Colors(6, "#51766d"));
        colors.add(new Colors(7, "#ebd18f"));
        colors.add(new Colors(8, "#000000"));
        colors.add(new Colors(9, "#6d5af4"));
        colors.add(new Colors(10, "#9a7b71"));
        colors.add(new Colors(11, "#3e3670"));

        Collections.shuffle(colors);

        Random random = new Random();
        val = random.nextInt(colors.size());

        adapter = new Adapter(colors, this, imagen, contador1,contador2,number1, number2, val);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void cronometro(){
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                tiempo.setText(""+l/1000);
            }

            @Override
            public void onFinish() {
               new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Puntuación")
                        .setMessage("Aciertos "+number1.getText().toString()+"\n"+"Errores "+number2.getText().toString())
                        .setPositiveButton("JUGAR DE NUEVO", (dialogInterface, i) -> {
                            cronometro();
                            number1.setText("0");
                            number2.setText("0");
                            contador1 = 0;
                            contador2 = 0;
                            adapter.reset();
                            dialogInterface.dismiss();})
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setCancelable(false)
                        .show();
            }
        }.start();
    }
}