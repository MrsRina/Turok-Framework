package me.rina.turok;

import net.minecraft.network.PacketThreadUtil;
import org.lwjgl.opengl.GL11;

/**
 * @author SrRina
 * @since 19/11/20 at 8:14pm
 *
 * - Turok framework is one helper and util to Minecraft modding.
 */
public class Turok {
    public static String AUTHOR  = "SrRina";
    public static String VERSION = "5.0.0 Official Version";
    public static String NAME    = "Turok Framework";

    public static String getAuthor() {
        return AUTHOR;
    }

    public static String getVersion() {
        return VERSION;
    }

    public static String getName() {
        return NAME;
    }
}
