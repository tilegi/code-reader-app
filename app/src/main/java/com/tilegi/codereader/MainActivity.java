package com.tilegi.codereader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startButton;  //iki tane buton tanımladım.
    private Button showList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton); // tanıladığım butonları xml dosyasındaki(activity_main.xml) butonlara bağladım
        showList = findViewById(R.id.showList);

        startButton.setOnClickListener(new View.OnClickListener() { //Butonun basma özelliği
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SaveInfoActivity.class); //burada butona basıldığında hangi syafaya gideceğimizi tnaımlıyoruz
                startActivity(intent); //bu da diğer sayfaya geçmemizi sağlayan fonksiyon
            }
        });

        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InfoList.class); //Info list sayfasına git
                startActivity(intent);
            }
        });

    }
}