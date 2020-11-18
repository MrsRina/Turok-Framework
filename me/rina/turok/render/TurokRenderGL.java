package me.rina.turok.render;

// Minecraft.
import me.rina.turok.display.TurokDisplay;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.*;
import net.minecraft.client.Minecraft;

// OpenGL.
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL32;

// Java.

// Math.
import me.rina.turok.math.TurokRect;

/**
 * @author Rina.
 * @since 26/09/2020.
 **/
public class TurokRenderGL {
	private static final Minecraft mc = Minecraft.getMinecraft();

	public static void color(int r, int g, int b, int a) {
		GL11.glColor4f((float) r / 255, (float) g / 255, (float) b / 255, (float) a / 255);
	}

	public static void color(int r, int g, int b) {
		GL11.glColor3f((float) r / 255, (float) g / 255, (float) b / 255);
	}

	public static void renderScissor(int x, int y, int w, int h, TurokDisplay display) {
		int interpolatedX = x;
		int interpolatedY = y;

		int interpolatedW = interpolatedX + w;
		int interpolatedH = interpolatedY + h;

		GL11.glScissor((int) (interpolatedX * display.getScalaManager().getScaleFactor()), (int) (Minecraft.getMinecraft().displayHeight - (interpolatedH * display.getScalaManager().getScaleFactor())), (int) ((interpolatedW - interpolatedX) * display.getScalaManager().getScaleFactor()), (int) ((interpolatedH - interpolatedY) * display.getScalaManager().getScaleFactor()));;
	}

	public static void drawTexture(float x, float y, float width, float height) {
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2d(x, y);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2d(x, y + height);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2d(x + width, y + height);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2d(x + width, y);
		}

		GL11.glEnd();
	}

	public static void drawTextureInterpolated(float x, float y, float xx, float yy, float width, float height, float ww, float hh) {
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0 + xx, 0 + hh);
			GL11.glVertex2d(x, y);
			GL11.glTexCoord2f(0 + xx, 1 + hh);
			GL11.glVertex2d(x, y + height);
			GL11.glTexCoord2f(1 + ww, 1 + hh);
			GL11.glVertex2d(x + width, y + height);
			GL11.glTexCoord2f(1 + ww, 0 + hh);
			GL11.glVertex2d(x + width, y);
		}

		GL11.glEnd();
	}

	public static void drawUpTriangle(float x, float y, float width, float height, int offsetX) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		{
			GL11.glVertex2d(x + width, y + height);
			GL11.glVertex2d(x + width, y);
			GL11.glVertex2d(x - offsetX, y);
		}

		GL11.glEnd();
	}

	public static void drawDownTriangle(float x, float y, float width, float height, int offsetX) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		{
			GL11.glVertex2d(x, y);
			GL11.glVertex2d(x, y + height);
			GL11.glVertex2d(x + width + offsetX, y + height);
		}

		GL11.glEnd();
	}

	public static void drawArc(float cx, float cy, float r, float start_angle, float end_angle, float num_segments) {
		GL11.glBegin(GL11.GL_TRIANGLES);

		for (int i = (int) (num_segments / (360 / start_angle)) + 1; i <= num_segments / (360 / end_angle); i++) {
			double previousangle = 2 * Math.PI * (i - 1) / num_segments;
			double angle         = 2 * Math.PI * i / num_segments;

			GL11.glVertex2d(cx, cy);
			GL11.glVertex2d(cx + Math.cos(angle) * r, cy + Math.sin(angle) * r);
			GL11.glVertex2d(cx + Math.cos(previousangle) * r, cy + Math.sin(previousangle) * r);
		}

		GL11.glEnd();
	}

	public static void drawArc(float x, float y, float radius) {
		drawArc(x, y, radius, 0, 360, 40);
	}

	public static void drawArcOutline(float cx, float cy, float r, float start_angle, float end_angle, float num_segments) {
		GL11.glBegin(GL11.GL_LINE_LOOP);

		for (int i = (int) (num_segments / (360 / start_angle)) + 1; i <= num_segments / (360 / end_angle); i++) {
			double angle = 2 * Math.PI * i / num_segments;

			GL11.glVertex2d(cx + Math.cos(angle) * r, cy + Math.sin(angle) * r);
		}

		GL11.glEnd();
	}

	public static void drawArcOutline(float x, float y, float radius) {
		drawArcOutline(x, y, radius, 0, 360, 40);
	}

	public static void drawOutlineRect(float x, float y, float width, float height) {
		float interpolatedX = x + 0.5f;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glLineWidth(1f);

		GL11.glBegin(GL11.GL_LINE_LOOP);
		{
			GL11.glVertex2d(interpolatedX, y);
			GL11.glVertex2d(interpolatedX,y + height + 0.5f);
			GL11.glVertex2d(x + width, y + height);
			GL11.glVertex2d(x + width, y);
		}

		GL11.glEnd();
	}

	public static void drawOutlineRoundedRect(float x, float y, float width, float height, float radius, float dR, float dG, float dB, float dA, float line_width) {
		drawRoundedRect(x, y, width, height, radius);

		GL11.glColor4f(dR, dG, dB, dA);

		drawRoundedRect(x + line_width, y + line_width, width - line_width * 2, height - line_width * 2, radius);
	}

	public static void drawRoundedRect(float x, float y, float width, float height, float radius) {
		GL11.glEnable(GL11.GL_BLEND);

		drawArc((x + width - radius), (y + height - radius), radius, 0, 90, 16);
		drawArc((x + radius), (y + height - radius), radius, 90, 180, 16);
		drawArc(x + radius, y + radius, radius, 180, 270, 16);
		drawArc((x + width - radius), (y + radius), radius, 270, 360, 16);

		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			GL11.glVertex2d(x + width - radius, y);
			GL11.glVertex2d(x + radius, y);

			GL11.glVertex2d(x + width - radius, y + radius);
			GL11.glVertex2d(x + width - radius, y + radius);

			GL11.glVertex2d(x + radius, y);
			GL11.glVertex2d(x + radius, y + radius);

			GL11.glVertex2d(x + width, y + radius);
			GL11.glVertex2d(x, y + radius);

			GL11.glVertex2d(x, y + height - radius);
			GL11.glVertex2d(x + width, y + radius);

			GL11.glVertex2d(x, y + height-radius);
			GL11.glVertex2d(x + width, y + height - radius);

			GL11.glVertex2d(x + width - radius, y + height - radius);
			GL11.glVertex2d(x + radius, y + height - radius);

			GL11.glVertex2d(x + width - radius, y + height);
			GL11.glVertex2d(x + width - radius, y + height);

			GL11.glVertex2d(x + radius, y + height - radius);
			GL11.glVertex2d(x + radius, y + height);
		}

		GL11.glEnd();
	}

	public static void drawRoundedRect(TurokRect rect, float size) {
		drawRoundedRect((float) rect.getX(), (float) rect.getY(), (float) (rect.getWidth()), (float) (rect.getHeight()), size);
	}

	public static void drawOutlineRect(int x, int y, int width, int height) {
		drawOutlineRect((float) x, (float) y, (float) width, (float) height);
	}

	public static void drawOutlineRect(TurokRect rect) {
		drawOutlineRect((float) rect.getX(), (float) rect.getY(), (float) (rect.getWidth()), (float) (rect.getHeight()));
	}

	public static void drawSolidRect(float x, float y, float width, float height) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2d(x, y);
			GL11.glVertex2d(x, y + height);
			GL11.glVertex2d(x + width, y + height);
			GL11.glVertex2d(x + width, y);
		}

		GL11.glEnd();
	}

	public static void drawSolidRect(int x, int y, int width, int height) {
		drawSolidRect((float) x, (float) y, (float) width, (float) height);
	}

	public static void drawSolidRect(TurokRect rect) {
		drawSolidRect((float) rect.getX(), (float) rect.getY(), (float) (rect.getWidth()), (float) (rect.getHeight()));
	}

	public static void drawLine3D(double x, double y, double z, double x1, double y1, double z1, int r, int g, int b, int a, float line) {
		GlStateManager.pushMatrix();
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.shadeModel(GL11.GL_SMOOTH);

		GL11.glLineWidth(line);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);

		GlStateManager.disableDepth();

		GL11.glEnable(GL32.GL_DEPTH_CLAMP);

		final Tessellator tessellator    = Tessellator.getInstance();
		final BufferBuilder buffer_build = tessellator.getBuffer();

		buffer_build.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
		buffer_build.pos(x, y, z).color(r, g, b, a).endVertex();
		buffer_build.pos(x1, y1, z1).color(r, g, b, a).endVertex();

		tessellator.draw();

		GlStateManager.shadeModel(GL11.GL_FLAT);

		GL11.glDisable(GL11.GL_LINE_SMOOTH);

		GlStateManager.enableDepth();

		GL11.glDisable(GL32.GL_DEPTH_CLAMP);

		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	}

	public static void prepareScissor() {
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
	}

	public static void releaseScissor() {
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}

	public static void prepareTexture2D() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
	}

	public static void releaseTexture2D() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public static void fixScreen(float scaled_width, float scaled_height) {
		GL11.glPushMatrix();
		GL11.glTranslated(scaled_width, scaled_height, 0);
		GL11.glScaled(0.5, 0.5, 0.5);
		GL11.glPopMatrix();
	}

	public static void prepare2D() {
		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);

		GlStateManager.enableBlend();

		GL11.glPopMatrix();
	}

	public static void release2D() {
		GlStateManager.enableCull();
		GlStateManager.depthMask(true);
		GlStateManager.enableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.enableDepth();
	}

	public static void prepare3D(float size) {
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.glLineWidth(size);
		GlStateManager.disableTexture2D();
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		GlStateManager.disableDepth();
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.enableAlpha();
		GlStateManager.color(1, 1, 1);
	}

	public static void release3D() {
		GlStateManager.enableCull();
		GlStateManager.depthMask(true);
		GlStateManager.enableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.enableDepth();
	}

	public static void prepareImage2D() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	public static void releaseImage2D() {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
}