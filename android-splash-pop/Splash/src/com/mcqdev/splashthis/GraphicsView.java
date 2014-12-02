package com.mcqdev.splashthis;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GraphicsView extends SurfaceView {

    private Bitmap logo;
    private SurfaceHolder holder;
    private SplashConfig splashConfig;
    GameLoopThread gameLoopThread;
    ParticleSystem particleSystem;
    Paint paint;
    
    float xPos;
    float yPos;
    float xVec;
    float yVec;
    float wobbleIntensity;
    Rect sourceBounds;
    Rect drawBounds;
    
    int particlesN;

    float logoAspectRatio;
    float logoDrawWidth;
    
    long dtLong;
    long currentTime;
    long lastUpdate;
    float dt;
    float t;
    
    public GraphicsView(Context context, SplashConfig config) {
          super(context);
          
          this.logo = config.getLogo();
          init();
          gameLoopThread = new GameLoopThread(this);
          holder = getHolder();

          holder.addCallback(new SurfaceHolder.Callback() {


                 @Override

                 public void surfaceDestroyed(SurfaceHolder holder) {

                        boolean retry = true;
                        gameLoopThread.setRunning(false);
                        while (retry) {
                               try {
                                     gameLoopThread.join();
                                     retry = false;
                               } catch (InterruptedException e) {}
                        }
                 }

                 @Override
                 public void surfaceCreated(SurfaceHolder holder) {
                        gameLoopThread.setRunning(true);
                        gameLoopThread.start();
                 }

                 @Override
                 public void surfaceChanged(SurfaceHolder holder, int format,
                               int width, int height) {
                	 init();
                 }
          });

    }

    private void init(){
    	
        xPos = 10f;
        yPos = 10f;
        
        sourceBounds = new Rect();
        sourceBounds.set(0, 0, logo.getWidth(), logo.getHeight());
        
        drawBounds = new Rect();
        drawBounds.set( (int)xPos, (int)yPos, logo.getWidth(), logo.getHeight());
        
        logoAspectRatio = (float)logo.getWidth() / (float)logo.getHeight();
        
        t=0;
        dtLong = 0;
        wobbleIntensity = 0f;
        currentTime = System.currentTimeMillis();
        lastUpdate = currentTime;
        
        particlesN = 150;
        particleSystem = new ParticleSystem(particlesN,logo);
        
        paint = new Paint();
    	
    }

    @Override
    protected void onDraw(Canvas canvas) {
    	if(canvas==null)return;
    	
    	updateTime();
    	
    	
    	
    	logoDrawWidth = (float)canvas.getWidth() * 0.8f;
    	
    	
    	xPos = ( (canvas.getWidth()-logoDrawWidth) *0.5f) + (float)(Math.cos(t*12f)*wobbleIntensity*logoDrawWidth);
    	yPos = (canvas.getHeight() - (logoDrawWidth / logoAspectRatio)) * 0.5f;
    	
    	
    	drawBounds.set( (int)xPos, (int)yPos, (int)(xPos+logoDrawWidth),  (int)(yPos+logoDrawWidth/logoAspectRatio));
    	
          
    	canvas.drawColor(Color.BLACK);
    	
		  paint.setARGB(255, 255, 255, 255);
		  if(t>0.25f)particleSystem.draw(canvas, dt, paint);
		  
		  
		  //canvas.drawBitmap(logo, 200, 50, null);
		  canvas.drawBitmap(logo, sourceBounds, drawBounds, paint);
		  
		  lastUpdate = currentTime;

    }
    
    public void updateTime(){
    	
    	currentTime = System.currentTimeMillis();
    	dtLong = currentTime - lastUpdate;
    	dt = ((float)dtLong) / 1000f;
    	t += dt;
    	wobbleIntensity = 1.5f - t;
    	if(wobbleIntensity<0f)wobbleIntensity = 0f;
    	
    	//Log.w("LOG", "t: "+t);
    	//Log.w("LOG", "dt: "+dt);
    	//Log.w("LOG", "dtLong: "+dtLong);
    	
    }

}