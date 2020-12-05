package me.rina.turok.util;

/**
 * @author SrRina
 * @since 03/12/20 at 10:49pm
 */
public class TurokClass {
    public static Enum getEnumByName(Enum _enum, String name) {
        for (Enum enums : _enum.getClass().getEnumConstants()) {
            if (_enum.name().equals(name)) {
                return _enum;
            }
        }

        return null;
    }
}
