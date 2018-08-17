package fr.ragejam.graphics;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

public class Font {

	public static String chars = "ABCDEFGHIJKLM"
			+ "NOPQRSTUVWXYZ"
			+ "0123456789"
			+ "!§#$%&'()*+,-"
			+ "./:;<=>?@[§]^";

	public static void render(String msg, float x, float y, float scale, float[] color) {
		msg = msg.toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			int charIndex = chars.indexOf(msg.charAt(i));
			if(msg.charAt(i) == '"'){
				charIndex = 37;
			} else if(msg.charAt(i) == '\\'){
				charIndex = 59;
			}
			if (charIndex >= 0) {
				Texture.font_classic.bind();
				glBegin(GL_QUADS);
				if(charIndex >= 49){
					Renderer.quadData(x, y, scale, scale, color, charIndex-49, 4, 13, 5);
				}
				if(charIndex >= 36){
					Renderer.quadData(x, y, scale, scale, color, charIndex-36, 3, 13, 5);
				}
				if(charIndex >= 26){
					Renderer.quadData(x, y, scale, scale, color, charIndex-26, 2, 13, 5);
				}
				if(charIndex >= 13){
					Renderer.quadData(x, y, scale, scale, color, charIndex -13, 1, 13, 5);
				}
				else {
					Renderer.quadData(x, y, scale, scale, color, charIndex, 0, 13, 5);
				}
				glEnd();
				Texture.font_classic.unbind();
				x = x+ scale + 0.5f;
			} else {
				x = x+ scale/2;
			}
		}
	}

	
	public static int getSpaceNumberInString(String s){
		int toReturn = 0;
		for(Character c : s.toCharArray()){
			if(c.equals(' ')) toReturn++;
		}
		return toReturn;
	}
	
	public static float getTextSize(String text, float scale){
		 return (text.length()-Font.getSpaceNumberInString(text)) * (scale+0.5f) + Font.getSpaceNumberInString(text)*(scale/2);
	}
}