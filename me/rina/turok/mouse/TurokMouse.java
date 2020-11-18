package me.rina.turok.mouse;

import me.rina.turok.mouse.impl.TurokMouseHelper;

/**
 * @author Rina.
 * @since 02/10/2020.
 **/
public class TurokMouse implements TurokMouseHelper {
    private int x;
    private int y;

    private int scroll;

    public TurokMouse() {}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScroll() {
        return scroll;
    }

    @Override
    public void onSyncPosition(int mx, int my) {
        this.x = mx;
        this.y = my;
    }

    @Override
    public void onSyncScroll(int scroll) {
        this.scroll = scroll;
    }
}
