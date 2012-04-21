package baseGame.Rendering;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import baseGame.Renderable;

import entity.Entity;


public class Renderer {		
	private int canvasWidth;
	private int canvasHeight;
	private int[] rgbPixels;
	private int backgroundColor;
	public Renderer(int[] rgbPixels,int backgroundColor,int canvasWidth,int canvasHeight){
		this.rgbPixels = rgbPixels;
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		this.backgroundColor = backgroundColor;
	}
	
	public void DrawImage(RGBImage img,int x,int y,int width,int height){
		
		
		
		DrawPixelArrayRGB(img.getPixels(), x, y, width, height);
		
	}
	
	
	public void DrawPixelArrayRGB(int[] pixels,int x,int y, int width, int height){
		int offset = y*canvasWidth;
		
		
			
			for(int i =0;i<height;i++){
				for(int j = 0;j<width;j++){
					int adr = offset +x+ i*canvasWidth + j ;
					if((j +x)>canvasWidth || (j+x) <0 || (i+y) <0 || adr >= rgbPixels.length)
						continue;
				
					rgbPixels[adr] = pixels[i*width +j];
					
					
					
						
					
				}
			}
		
			
		
	}
	public void DrawPixelArrayRGB(int[] pixels,int transparentColor,int x,int y, int width, int height){
		int offset = y*canvasWidth;
		
		
			for(int i =0;i<height;i++){
				for(int j = 0;j<width;j++){
					int adr = offset +x+ i*canvasWidth + j ;
					
					if((j +x)>canvasWidth || (j+x) <0 || (i+y) <0 || adr >= rgbPixels.length)
						continue;
					
						int c = pixels[i*width +j];
						if(c !=transparentColor){
						 rgbPixels[adr] =c ;
					}
						
					
				}
			}
			
		
	}
	
	public void clearAllPixelData(int clearColor){
		
		for(int i =0;i<rgbPixels.length;i++){
			if(rgbPixels[i] !=clearColor){
				rgbPixels[i] = clearColor;
			}
		}
	}
	public void makeTransparent(int color){
		for(int i =0;i<rgbPixels.length;i++){
			if(rgbPixels[i] == color){
				rgbPixels[i] = backgroundColor;
			}
		}
	}
}