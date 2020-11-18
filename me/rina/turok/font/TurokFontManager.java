package me.rina.turok.font;

// Minecraft util.

// Java.
import java.awt.*;

// CFontRenderer.
import me.rina.turok.font.util.CFontRenderer;

// Render.
import me.rina.turok.render.TurokRenderGL;

/**
 * @author Rina.
 * @since 26/09/2020.
 **/
public class TurokFontManager {
	/* Types of custom fonts. */
	public static final CFontRenderer CFONT_SMALL_SIZE  = new CFontRenderer(new Font("Whitney", 0, 14), true, true);
	public static final CFontRenderer CFONT_NORMAL_SIZE = new CFontRenderer(new Font("Whitney", 0, 16), true, true);
	public static final CFontRenderer CFONT_SCALED_SIZE = new CFontRenderer(new Font("Whitney", 0, 19), true, true);
	public static final CFontRenderer CFONT_GIGANT_SIZE = new CFontRenderer(new Font("Whitney", 0, 46), true, true);

	/**
	 * @param cFontRenderer - Specified font renderer.
	 * @param string        - String to render.
	 * @param x             - Position x.
	 * @param y             - Position y.
	 * @param r             - Red value color.
	 * @param g             - Green value color.
	 * @param b             - Blue value color.
	 * @param alpha         - Alpha string.
	 * @param shadow        - String shadow boolean.
	 **/
	public static void renderString(CFontRenderer cFontRenderer, String string, int x, int y, int r, int g, int b, int alpha, boolean shadow) {
		Color color = new Color(r, g, b, alpha >= 255 ? 255 : alpha);

		TurokRenderGL.prepareTexture2D();
		TurokRenderGL.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

		if (shadow) {
			cFontRenderer.drawStringWithShadow(string, x, y, color.getRGB());
		} else {
			cFontRenderer.drawString(string, x, y, color.getRGB());
		}

		TurokRenderGL.releaseTexture2D();
	}

	public static void renderString(CFontRenderer cFontRenderer, String string, int x, int y, int r, int g, int b, boolean shadow) {
		renderString(cFontRenderer, string, x, y, r, g, b, 255, shadow);
	}

	/**
	 * @param cFontRenderer - Specified font renderer.
	 * @param string        - String to get width.
	 **/
	public static int getStringWidth(CFontRenderer cFontRenderer, String string) {
		return (int) cFontRenderer.getStringWidth(string);
	}

	/**
	 * @param cFontRenderer - Specified font renderer.
	 * @param string        - String to get height.
	 **/
	public static int getStringHeight(CFontRenderer cFontRenderer, String string) {
		return (int) cFontRenderer.getStringHeight(string);
	}
}
