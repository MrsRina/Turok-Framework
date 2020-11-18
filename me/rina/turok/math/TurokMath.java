package me.rina.turok.math;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author SrRina
 * @since 21/10/2020
 */
public class TurokMath {
    public static int intClamp(int value, int minimum, int maximum) {
        if (value <= minimum) {
            return minimum;
        } else if (value >= maximum) {
            return maximum;
        }

        return value;
    }

    public static double doubleClamp(double value, double minimum, double maximum) {
        if (value <= minimum) {
            return minimum;
        } else if (value >= maximum) {
            return maximum;
        }

        return value;
    }

    public static float floatClamp(float value, float minimum, float maximum) {
        if (value <= minimum) {
            return minimum;
        } else if (value >= maximum) {
            return maximum;
        }

        return value;
    }

    public static double decimal(double vDouble) {
        BigDecimal decimal = new BigDecimal(vDouble);

        decimal = decimal.setScale(2, RoundingMode.HALF_UP);

        return decimal.doubleValue();
    }

    public static Vec3d lerp(Vec3d a, Vec3d b, float ticks) {
        return new Vec3d(
            a.x + (b.x - a.x) * ticks,
            a.y + (b.y - a.y) * ticks,
            a.z + (b.z - a.z) * ticks
        );
    }
}