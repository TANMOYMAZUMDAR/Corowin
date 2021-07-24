package com.example.corowin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.corowin.databinding.ActivitySplashSreenBinding;
import com.example.corowin.view.MainActivity;

public class SplashScreen extends AppCompatActivity {

    ActivitySplashSreenBinding binding;
    Animation bottomAnim,sideAnim,scaleUp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashSreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sideAnim= AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        scaleUp= AnimationUtils.loadAnimation(this,R.anim.scale_up);

        binding.textview.setAnimation(sideAnim);
        binding.animationView.setAnimation(bottomAnim);
        binding.textview1.setAnimation(scaleUp);

        new Handler().postDelayed(new Runnable() {      //MultiThreading
            @Override
            public void run() {
                    Intent intent=new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
                    finishAffinity();
            }
        },5000);
    }
}
