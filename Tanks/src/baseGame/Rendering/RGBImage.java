package baseGame.Rendering;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import baseGame.*;
import javax.imageio.ImageIO;

public class RGBImage {
	private BufferedImage rgbBufferedImage;
	private int[] rgbPixels;
	
	private int canvasWidth;
	private int canvasHeight;
	public int getWidth() {
		return canvasWidth;
	}

	public int getHeight() {
		return canvasHeight;
	}

	public RGBImage(BufferedImage img){
		setBufferedImage(img);
	}
	private void setBufferedImage(BufferedImage img){
		if(img.getType() != BufferedImage.TYPE_INT_RGB){
			rgbBufferedImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
			
			rgbBufferedImage.getGraphics().drawImage(img ,0, 0, img.getWidth(), img.getHeight(),Color.white,null);
			}else{
				rgbBufferedImage = img;
			}
			canvasWidth= img.getWidth();
			canvasHeight = img.getHeight();
			rgbPixels = ((DataBufferInt)rgbBufferedImage.getRaster().getDataBuffer()).getData();
			
	}
	public RGBImage(File file){
		
		
		
		BufferedImage img = null;
		
		try {
			img= ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBufferedImage(img);
		
		
	}
	
	public void DrawRGBImage(int[] pixels,int x,int y, int width, int height){
		
		int realY = y*canvasWidth;
		
		if(x>=0 && y>=0 && y<= canvasHeight && x<=canvasWidth){
			
			for(int i =0;i<height;i++){
			
				for(int j = 0;j<width;j++){
					int adr = realY +x+ i*canvasWidth + j ;
				
					if(adr < rgbPixels.length){
					rgbPixels[adr] = pixels[i*width +j];
				
					}else{
						break;
					}
						
					
				}
			}
		
			}
		
	
	}
public void DrawRGBImage(int[] pixels,int mask,int x,int y, int width, int height){
		
		int realY = y*canvasWidth;
		
		
			
			for(int i =0;i<height;i++){
			
				for(int j = 0;j<width;j++){
					int adr = realY +x+ i*canvasWidth + j ;
					
					if((j +x)>canvasWidth| (j+x) <0 || (y + i) < 0 || adr >= rgbPixels.length)
						continue;
					
					int pixelColor = pixels[i*width +j];
					if(pixelColor !=mask){
					rgbPixels[adr] = pixelColor;
					
					}
					
						
					
				}
			}
		
			
		
	
	}
	//Midtpoint circle algorithm. Source:
	//http://en.wikipedia.org/wiki/Midpoint_circle_algorithm
	//Modded to fil a circle;
	public void DrawCircle(int color,int x0,int y0, int radius){
		int width = radius*2 +2;
		int[] pixels = new int[width*width];
		
		for(int i =0;i<pixels.length;i++){
			pixels[i] =-1;
		}
		
		int xf =radius;
		int yf = radius;
		
		int f = 1 - radius;
		  int ddF_x = 1;
		  int ddF_y = -2 * radius;
		  int x = 0;
		  int y = radius;
		  
		  setPixel(pixels,width,color,xf, yf + radius);
		  setPixel(pixels,width,color,xf, yf - radius);
		  setPixel(pixels,width,color,xf + radius, yf);
		  setPixel(pixels,width,color,xf - radius, yf);
		 
		  while(x < y)
		  {
		   
		    if(f >= 0) 
		    {
		      y--;
		      ddF_y += 2;
		      f += ddF_y;
		    }
		    x++;
		    ddF_x += 2;
		    f += ddF_x;    
		    setPixel(pixels,width,color,xf + x, yf + y);
		    setPixel(pixels,width,color,xf - x, yf + y);
		    setPixel(pixels,width,color,xf + x, yf - y);
		    setPixel(pixels,width,color,xf - x, yf - y);
		    setPixel(pixels,width,color,xf + y, yf + x);
		    setPixel(pixels,width,color,xf - y, yf + x);
		    setPixel(pixels,width,color,xf + y, yf - x);
		    setPixel(pixels,width,color,xf - y, yf - x);
		  }
		
		 boolean doFill = false;
		 int Y =0;
		for(int X  = 0; X<pixels.length*0.25;X++){
		
			if(X%(width/2) == 0){
				pixels[(X-1)%(width/2) + Y*width+1] = color;
				pixels[(X-1)%(width/2) + Y*width+1 + pixels.length/2] = color;
				
				
				Y++;
				doFill = false;
				
			}
			int posX = X%(width/2);
			int posY = Y*width;
			int pixelC = pixels[posX + posY];
			if(doFill){
				pixels[posX + posY] = color;
				pixels[width - posX + posY] = color;
				
				pixels[pixels.length - (posX+ posY)] = color;
				
				pixels[pixels.length - (width - posX + posY)] = color;
			}
			if(pixelC ==color){
				doFill = true;
			}
			
		} 
		pixels[width/2 * width + width/2] = color;
		
		
		DrawRGBImage(pixels,-1, x0,y0, width, width);
		  
	}
	public void setPixel(int[] pixels,int width,int color,int x,int y){
		
		
		int height = pixels.length/width;
		
		if(x>=0 && y>=0 && y<= height && x<=width){
			pixels[width*y + x] = color;
			}
	}
	public void setPixel(int color,int x, int y){
		if(x>=0 && y>=0 && y<= canvasHeight && x<=canvasWidth){
		rgbPixels[canvasWidth*y + x] = color;
		}
	}
	public int getPixel(int x,int y){
		
		return rgbPixels[canvasWidth*y + x];
				
	}
	Polygon poly = new Polygon();
	
	public BufferedImage getRgbBufferedImage() {
		return rgbBufferedImage;
	}
	

	public int[] getPixels() {
		return rgbPixels;
	}
	

}