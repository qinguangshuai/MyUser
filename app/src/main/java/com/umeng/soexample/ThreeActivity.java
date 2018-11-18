package com.umeng.soexample;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ThreeActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        image = findViewById(R.id.image4);
        Intent intent = getIntent();
        String image1 = intent.getStringExtra("image");
        Picasso.with(getApplicationContext()).load(image1).into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(v,"translationY",200);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(v,"alpha",0,1);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(v,"rotation",360);
                ObjectAnimator animator4 = ObjectAnimator.ofFloat(v,"scaleX",2,1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator1,animator2,animator3,animator4);
                set.setDuration(3000);
                set.start();
            }
        });
    }
}
