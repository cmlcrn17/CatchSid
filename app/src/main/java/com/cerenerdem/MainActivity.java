package com.cerenerdem;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.Image;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cerenerdem.yakala.R;

import java.util.Random;

import javax.net.ssl.HandshakeCompletedEvent;

public class MainActivity extends AppCompatActivity {

    TextView txtScore;
    TextView txtTime;


    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;
    ImageView img9;

    int score;

    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtScore = (TextView) findViewById(R.id.txt_Score);
        txtTime = (TextView) findViewById(R.id.txt_Time);

        img1 = (ImageView) findViewById(R.id.img_sid);
        img2 = (ImageView) findViewById(R.id.img_sid2);
        img3 = (ImageView) findViewById(R.id.img_sid3);
        img4 = (ImageView) findViewById(R.id.img_sid4);
        img5 = (ImageView) findViewById(R.id.img_sid5);
        img6 = (ImageView) findViewById(R.id.img_sid6);
        img7 = (ImageView) findViewById(R.id.img_sid7);
        img8 = (ImageView) findViewById(R.id.img_sid8);
        img9 = (ImageView) findViewById(R.id.img_sid9);

        score = 0;


        imageArray = new ImageView[]
                {img1, img2, img3, img4, img5, img6, img7, img8, img9};

        hideImage();

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTime.setText("Time:" + millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                txtTime.setText("Game Over");
                txtTime.setTextColor(Color.RED);
                handler.removeCallbacks(runnable);


                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle(getString(R.string.Soru_DevamEtmekIsterMisin));
                dialogBuilder.setMessage(getString(R.string.Bilgi_DevamButonunaBasin));

                dialogBuilder.setNegativeButton(getString(R.string.Cevap_Istemiyorum), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                dialogBuilder.setPositiveButton(getString(R.string.Cevap_Istiyorum), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), getString(R.string.Mesaj_Tesekkurler), Toast.LENGTH_LONG).show();
                    }
                });

                dialogBuilder.show();


            }


        }.start();


    }


    //Score değerini 1 arttır
    public void increaseScor(View view) {

        txtScore.setText("Score:" + score++);

    }


    public void hideImage() {


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {

                    image.setVisibility(View.INVISIBLE);
                }


                Random r = new Random();
                int i = r.nextInt(8 - 0);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 500);
            }


        };


        handler.post(runnable);

    }


}
