package me.rina.turok.render.image;

// Minecraft.
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;

// Javax.
import javax.imageio.ImageIO;

// Java.
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Rina.
 * @since 27/09/2020.
 *
 **/
public class TurokImage extends DynamicTexture {
	private String path;

	private BufferedImage bufferedImage;

	private ResourceLocation resourceLocation;

	public int w;
	public int h;

	public TurokImage(String path, BufferedImage bufferedImage) {
		super(bufferedImage);

		this.bufferedImage = bufferedImage;

		this.path = path;
	
		this.resourceLocation = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("turok/gui", this);
	}

	public void setResourceLocation(ResourceLocation resourceLocation) {
		this.resourceLocation = resourceLocation;
	}

	public int getWidth() {
		return this.bufferedImage.getWidth();
	}

	public int getHeight() {
		return this.bufferedImage.getHeight();
	}

	public ResourceLocation getResourceLocation() {
		return resourceLocation;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public String getPath() {
		return path;
	}
}