package com.example.baynaught;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * This class displays a splash screen at the app's startup. The time that the splash screen is shown for can
 * be adjusted through the {@code _splashTime} variable.
 *
 * Displaying the splash screen can be aborted by pressing somewhere on the screen.
 * 
 * Copyright 2k11 Impressive Artworx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Manuel Schwarz (m.schwarz[at]impressive-artworx.de)
 */
public class SplashScreen extends Activity {

   private boolean mActive = true;
   private int mSplashTime = 4000; // display time in ms
   
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      
      // hide the app title and also the notification bar
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
      
      setContentView(R.layout.splash);

      // thread for displaying the splash screen
      Thread splashThread = new Thread() 
      {
         @Override
         public void run()
         {
            try 
            {
               int waited = 0;
               while(mActive && (waited < mSplashTime))
               {
                  sleep(100);
                  if(mActive)
                  {
                     waited += 100;
                  }
               }
            } 
            catch(InterruptedException e)
            {
               // do nothing
            } 
            finally 
            {
               Intent i = new Intent(getApplicationContext(),
                     MainActivity.class);
               startActivity(i);
               finish();
            }
         }
      };
      splashThread.start();
   }
   
   @Override
   public boolean onTouchEvent(MotionEvent event) {
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
         mActive = false;
      }
      return true;
   }
}