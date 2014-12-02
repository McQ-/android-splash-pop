package com.mcqdev.splashthis;

import com.mcqdev.splashthis.SplashConfig.EffectType;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        

    }
    
    public void setConfig(SplashConfig splashConfig){
    	
    	setContentView(new GraphicsView(this,splashConfig));
    }
	
	
}
