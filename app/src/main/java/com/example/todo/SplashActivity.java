package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Membuat status bar berwarna putih
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat windowInsetsController = ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController != null) {
            windowInsetsController.setAppearanceLightStatusBars(true); // Teks gelap pada status bar
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));
        }

        // Dapatkan referensi ke elemen UI
        ConstraintLayout mainLayout = findViewById(R.id.main);
        ImageView imageView = findViewById(R.id.imageView3);
        TextView textView = findViewById(R.id.textView);

        // Muat animasi fade in dan fade out
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        // Mulai animasi fade in untuk semua elemen
        mainLayout.startAnimation(fadeIn);
        imageView.startAnimation(fadeIn);
        textView.startAnimation(fadeIn);

        // Jalankan fade out setelah delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainLayout.startAnimation(fadeOut);
                imageView.startAnimation(fadeOut);
                textView.startAnimation(fadeOut);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                }, fadeOut.getDuration());
            }
        }, fadeIn.getDuration() + 1000); // Tambahkan sedikit jeda untuk menampilkan teks lebih lama
    }
}
