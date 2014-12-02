package com.mcqdev.splashthis;

import android.graphics.Bitmap;

public class SplashConfig {

	Bitmap logo;
	EffectType type;
	
	public void SpashConfig(){
		
		
	}
	
	public enum EffectType{
		LINE_VERTICAL, LINE_HORIZONTAL, GLITTER, DROP_AND_SHAKE
	}


	public Bitmap getLogo() {
		return logo;
	}


	public void setLogo(Bitmap logo) {
		this.logo = logo;
	}


	public EffectType getType() {
		return type;
	}


	public void setType(EffectType type) {
		this.type = type;
	}
}
