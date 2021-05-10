package com.example.dessert_club_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MenuActivity extends AppCompatActivity {

    private  CardView cardPudding;
    private CardView cardCake;
    private CardView cardIce;
    private CardView cardMousse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

      cardPudding = (CardView) findViewById(R.id.cardPudding);
        cardPudding.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                openCardPudding();
            }
        });

        cardCake = (CardView) findViewById(R.id.cardCake);
        cardCake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                openCardCake();
            }
        });

        cardIce = (CardView) findViewById(R.id.cardIce);
        cardIce.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                openCardIce();
            }
        });
        cardMousse = (CardView) findViewById(R.id.cardMousse);
        cardMousse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                openCardMousse();
            }
        });

    }
     public void openCardPudding(){
        Intent intent = new Intent( this, PuddingActivity.class);
        startActivity(intent);
    }
    public void openCardCake(){
        Intent intent = new Intent(this,CakeActivity.class);
        startActivity(intent);
    }
    public void openCardIce(){
        Intent intent = new Intent(this,IcecreamActivity.class);
        startActivity(intent);
    }
    public void openCardMousse(){
        Intent intent = new Intent(this,MousseActivity.class);
        startActivity(intent);
    }
}