package com.mcqdev.splashthis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class ParticleSystem {

	List<Particle> particles;
	int particlesN;
	Bitmap particleImage;
	boolean initiated;
	Random random;
	
	public ParticleSystem(int particlesN, Bitmap particleImage){
		
		this.particlesN = particlesN;
		this.particleImage = particleImage;
		
		random = new Random();
		
		initiated = false;
		
	}
	
	public void draw(Canvas canvas,float dt, Paint paint){
		
		if(!initiated)init(canvas);
		
		for(Particle particle : particles){
			particle.draw(canvas,dt,paint);
		}
		
	}
	
	public void init(Canvas canvas){
		
		initiated = true;
		
		particles = new ArrayList<Particle>();
		
		for(int i=0; i<particlesN; i++){
			
			Particle particle = new Particle(canvas);
			particles.add(particle);
		}
	}
	
	
	
	class Particle{
		
		float xPos;
		float yPos;
		float xVec;
		float yVec;
		float rotation;
		float width;
		float height;
		
		float t;
		
		Rect sourceBounds;
		Rect drawBounds;
		
		public Particle(Canvas canvas){
			
			init(canvas);
		}
		
		public void init(Canvas canvas){
			
			width = (float)canvas.getWidth() * 0.03f;
			height = width;
			
			xPos = (canvas.getWidth() - width) * 0.5f;
			yPos = (canvas.getHeight() - height) * 0.5f;
			
			xVec = random.nextFloat() + 0.5f;
			if(random.nextBoolean())xVec = xVec * -1f;
			xVec = xVec * canvas.getWidth() * 0.74f;;
			
			yVec = random.nextFloat() + 0.5f;
			if(random.nextBoolean())yVec = yVec * -1f;
			yVec = yVec * canvas.getWidth() * 0.74f;
			
	        sourceBounds = new Rect();
	        sourceBounds.set(0, 0, particleImage.getWidth(), particleImage.getHeight());
	        
	        drawBounds = new Rect();
	        drawBounds.set( (int)xPos, (int)yPos, particleImage.getWidth(), particleImage.getHeight());
	        
	        t = random.nextFloat() * 6.28f;
			
		}
		
		public void draw(Canvas canvas, float dt, Paint paint){
			
			if(canvas==null)return;
			
			t+=dt;
			
	    	xPos = xPos + xVec*dt;
	    	yPos = yPos + yVec*dt;
	    	
			width = (float)canvas.getWidth() * 0.005f * (float)Math.cos(t*8f);
			height = width;
			
	    	if(xPos + (width*2f) < 0 && xVec < 0){
	    		init(canvas);
	    	} else if(xPos > canvas.getWidth() && xVec > 0){
	    		init(canvas);
	    	}
	    	
	    	if(yPos + (width*2f) < 0 && yVec < 0){
	    		init(canvas);
	    	} else if(xPos > canvas.getWidth() && yVec > 0){
	    		init(canvas);
	    	}
	    	
			Log.d("SPLASH", "drawing particle at x: "+xPos);
			Log.d("SPLASH", "drawing particle at y: "+yPos);
			Log.d("SPLASH", "drawing particle at width: "+width);
			
	    	drawBounds.set( (int)xPos, (int)yPos, (int)(xPos+width),  (int)(yPos+height));
			
	    	
	    	
	          //canvas.drawColor(Color.WHITE);
	          canvas.drawCircle(xPos, yPos, width, paint);
	          
	          //canvas.drawBitmap(logo, 200, 50, null);
	          //canvas.drawBitmap(particleImage, sourceBounds, drawBounds, null);
			
			
			
		}
		
	}
	
}
