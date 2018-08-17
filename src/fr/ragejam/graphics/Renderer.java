package fr.ragejam.graphics;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

public class Renderer {

	public static void drawLine(float x1, float y1, float x2, float y2) {
	    GL11.glColor3f(0.7f, 1.0f, 1.0f);
	    GL11.glBegin(GL11.GL_LINE_STRIP);

	    GL11.glVertex2d(x1, y1);
	    GL11.glVertex2d(x2, y2);
	    GL11.glEnd();
	}

	public static void quadData(float x, float y, float w, float h, float[] color, int xo, int yo, float maxXo, float maxYo){
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f((0 + xo) / maxXo, (0 + yo) / maxYo); glVertex2f(x, y);
		glTexCoord2f((1 + xo) / maxXo, (0 + yo) / maxYo); ;glVertex2f(x + w, y);
		glTexCoord2f((1 + xo) / maxXo, (1 + yo) / maxYo); glVertex2f(x + w, y + h);
		glTexCoord2f((0 + xo) / maxXo, (1 + yo) / maxYo); glVertex2f(x, y + h);
		glEnd();
	}

	public static void quadData(float x, float y, float w, float h, int xo, int yo, float maxXo, float maxYo){
		glBegin(GL_QUADS);
		glColor4f(1,1,1,1);
		glTexCoord2f((0 + xo) / maxXo, (0 + yo) / maxYo); glVertex2f(x, y);
		glTexCoord2f((1 + xo) / maxXo, (0 + yo) / maxYo); ;glVertex2f(x + w, y);
		glTexCoord2f((1 + xo) / maxXo, (1 + yo) / maxYo); glVertex2f(x + w, y + h);
		glTexCoord2f((0 + xo) / maxXo, (1 + yo) / maxYo); glVertex2f(x, y + h);
		glEnd();
	}

	public static void quadData(float x, float y, float w, float h){
		glBegin(GL_QUADS);
		glColor4f(1, 1, 1, 1);
		glTexCoord2f(0,  0); glVertex2f(x, y);
		glTexCoord2f(1,  0); glVertex2f(x + w, y);
		glTexCoord2f(1,  1); glVertex2f(x + w, y + h);
		glTexCoord2f(0,  1); glVertex2f(x, y + h);
		glEnd();
	}

	public static void quadData(float x, float y, float w, float h, float[] color){
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(0,  0); glVertex2f(x, y);
		glTexCoord2f(1,  0); glVertex2f(x + w, y);
		glTexCoord2f(1,  1); glVertex2f(x + w, y + h);
		glTexCoord2f(0,  1); glVertex2f(x, y + h);
		glEnd();
	}

	public static void quadData(float x, float y, float w, float h, float[] color, float angle){
		glPushMatrix();

		glTranslatef(x + w / 2, y + h / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-x - w /2, -y - h /2, 0);	
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(0,  0); glVertex2f(x, y);
		glTexCoord2f(1,  0); glVertex2f(x + w, y);
		glTexCoord2f(1,  1); glVertex2f(x + w, y + h);
		glTexCoord2f(0,  1); glVertex2f(x, y + h);	
		glEnd();
		glPopMatrix();
	}

	public static void quadData(float x, float y, int w, int h, float angle){
		glPushMatrix();

		glTranslatef(x + w / 2, y + h / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-x - w /2, -y - h /2, 0);

		glBegin(GL_QUADS);
		glColor4f(1, 1, 1, 1);
		glTexCoord2f(0,  0); glVertex2f(x, y);
		glTexCoord2f(1,  0); glVertex2f(x + w, y);
		glTexCoord2f(1,  1); glVertex2f(x + w, y + h);
		glTexCoord2f(0,  1); glVertex2f(x, y + h);
		glEnd();
		glPopMatrix();
	}

	public static void quadData(float x, float y, int w, int h, float angle, int miror){
		glPushMatrix();

		glTranslatef(x + w / 2, y + h / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-x - w /2, -y - h /2, 0);

		if(miror == 1){
			glBegin(GL_QUADS);
			glColor4f(1, 1, 1, 1);
			glTexCoord2f(1,  0); glVertex2f(x, y);
			glTexCoord2f(0,  0); glVertex2f(x + w, y);
			glTexCoord2f(0,  1); glVertex2f(x + w, y + h);
			glTexCoord2f(1,  1); glVertex2f(x, y + h);
			glEnd();
			glPopMatrix();
		} else {
			glBegin(GL_QUADS);
			glColor4f(1, 1, 1, 1);
			glTexCoord2f(0,  0); glVertex2f(x, y);
			glTexCoord2f(1,  0); glVertex2f(x + w, y);
			glTexCoord2f(1,  1); glVertex2f(x + w, y + h);
			glTexCoord2f(0,  1); glVertex2f(x, y + h);
			glEnd();	
			glPopMatrix();
		}
	}

	public static void quadData(float x, float y, float w, float h, int xo, int yo, float maxXo, float maxYo, float angle){
		glPushMatrix();

		glTranslatef(x + w / 2, y + h / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-x - w /2, -y - h /2, 0);


		glBegin(GL_QUADS);
		glColor4f(1,1,1,1);
		glTexCoord2f((0 + xo) / maxXo, (0 + yo) / maxYo); glVertex2f(x, y);
		glTexCoord2f((1 + xo) / maxXo, (0 + yo) / maxYo); ;glVertex2f(x + w, y);
		glTexCoord2f((1 + xo) / maxXo, (1 + yo) / maxYo); glVertex2f(x + w, y + h);
		glTexCoord2f((0 + xo) / maxXo, (1 + yo) / maxYo); glVertex2f(x, y + h);
		glEnd();

		glPopMatrix();
	}

	public static void quadData(float x, float y, float w, float h, int xo, int yo, float maxXo, float maxYo, float angle, int miror){
		glPushMatrix();

		glTranslatef(x + w / 2, y + h / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-x - w /2, -y - h /2, 0);


		if(miror == 1){
			glBegin(GL_QUADS);
			glColor4f(1, 1, 1, 1);
			glTexCoord2f((1 + xo) / maxXo,  (0 + yo) / maxYo); glVertex2f(x, y);
			glTexCoord2f((0 + xo) / maxXo,  (0 + yo) / maxYo); glVertex2f(x + w, y);
			glTexCoord2f((0 + xo) / maxXo,  (1 + yo) / maxYo); glVertex2f(x + w, y + h);
			glTexCoord2f((1 + xo) / maxXo,  (1 + yo) / maxYo); glVertex2f(x, y + h);
			glEnd();
			glPopMatrix();
		} else {
			glBegin(GL_QUADS);
			glColor4f(1,1,1,1);
			glTexCoord2f((0 + xo) / maxXo, (0 + yo) / maxYo); glVertex2f(x, y);
			glTexCoord2f((1 + xo) / maxXo, (0 + yo) / maxYo); ;glVertex2f(x + w, y);
			glTexCoord2f((1 + xo) / maxXo, (1 + yo) / maxYo); glVertex2f(x + w, y + h);
			glTexCoord2f((0 + xo) / maxXo, (1 + yo) / maxYo); glVertex2f(x, y + h);
			glEnd();
			glPopMatrix();
		}
	}
	
	public static void quadData(float x, float y, float w, float h, int xo, int yo, float maxXo, float maxYo, float angle, int miror, float[] color){
		glPushMatrix();

		glTranslatef(x + w / 2, y + h / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-x - w /2, -y - h /2, 0);


		if(miror == 1){
			glBegin(GL_QUADS);
			glColor4f(color[0], color[1], color[2], color[3]);
			glTexCoord2f((1 + xo) / maxXo,  (0 + yo) / maxYo); glVertex2f(x, y);
			glTexCoord2f((0 + xo) / maxXo,  (0 + yo) / maxYo); glVertex2f(x + w, y);
			glTexCoord2f((0 + xo) / maxXo,  (1 + yo) / maxYo); glVertex2f(x + w, y + h);
			glTexCoord2f((1 + xo) / maxXo,  (1 + yo) / maxYo); glVertex2f(x, y + h);
			glEnd();
			glPopMatrix();
		} else {
			glBegin(GL_QUADS);
			glColor4f(color[0], color[1], color[2], color[3]);
			glTexCoord2f((0 + xo) / maxXo, (0 + yo) / maxYo); glVertex2f(x, y);
			glTexCoord2f((1 + xo) / maxXo, (0 + yo) / maxYo); ;glVertex2f(x + w, y);
			glTexCoord2f((1 + xo) / maxXo, (1 + yo) / maxYo); glVertex2f(x + w, y + h);
			glTexCoord2f((0 + xo) / maxXo, (1 + yo) / maxYo); glVertex2f(x, y + h);
			glEnd();
			glPopMatrix();
		}
	}







}
