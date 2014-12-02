package com.example.splash;

import com.mcqdev.splashthis.SplashActivity;
import com.mcqdev.splashthis.SplashConfig;
import com.mcqdev.splashthis.SplashConfig.EffectType;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;




public class MainActivity extends SplashActivity {

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bitmap logo = BitmapFactory.decodeResource(getResources(),R.drawable.logo_1);
        
        SplashConfig splashConfig = new SplashConfig();
        splashConfig.setLogo(logo);
        splashConfig.setType(EffectType.GLITTER); 
        
         
        this.setConfig(splashConfig);
        
    }

}
