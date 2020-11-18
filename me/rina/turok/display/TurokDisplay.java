package me.rina.turok.display;

import me.rina.turok.display.impl.TurokDisplayHelper;
import net.minecraft.client.gui.ScaledResolution;

/**
 * @author Rina.
 * @since 02/10/2020.
 **/
public class TurokDisplay implements TurokDisplayHelper {
    private int nonScaledWidth;
    private int nonScaledHeight;

    private int scaledWidth;
    private int scaledHeight;

    private ScaledResolution scalaManager;

    public TurokDisplay() {}

    public void setScalaManager(ScaledResolution scalaManager) {
        this.scalaManager = scalaManager;
    }

    public int getScaledWidth() {
        return scaledWidth;
    }

    public int getScaledHeight() {
        return scaledHeight;
    }

    public int getNonScaledWidth() {
        return nonScaledWidth;
    }

    public int getNonScaledHeight() {
        return nonScaledHeight;
    }

    public ScaledResolution getScalaManager() {
        return scalaManager;
    }

    @Override
    public void onSyncSize(int screenWidth, int screenHeight, int scaledWidth, int scaledHeight) {
        this.nonScaledWidth  = screenWidth;
        this.nonScaledHeight = screenHeight;

        this.scaledWidth  = scaledWidth;
        this.scaledHeight = scaledHeight;
    }
}
