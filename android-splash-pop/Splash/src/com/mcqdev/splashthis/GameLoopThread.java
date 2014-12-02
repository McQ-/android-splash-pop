package com.mcqdev.splashthis;

import android.annotation.SuppressLint;
import android.graphics.Canvas;



public class GameLoopThread extends Thread {

       private GraphicsView view;
       private boolean running = false;

       public GameLoopThread(GraphicsView view) {

             this.view = view;
       }

       public void setRunning(boolean run) {

             running = run;
       }

       @SuppressLint("WrongCall")
	   @Override
       public void run() {

             while (running) {

                    Canvas canvas = null;
                    try {

                    	canvas = view.getHolder().lockCanvas();
                           synchronized (view.getHolder()) {
                                  view.onDraw(canvas);
                           }

                    } finally {

                           if (canvas != null) {
                                  view.getHolder().unlockCanvasAndPost(canvas);
                           }
                    }
             }
       }

}   
