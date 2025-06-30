package net.mcdev.mcadditions.util;

public class MCAUtils {
    /// 200 ticks means that 1 item will be burned
    public static final int BURN_TICKS = 200;
    public static final int TICKS_PER_SECOND = 20;

    public static int getTicksBySeconds(int seconds) {
        assert seconds > 0;

        return TICKS_PER_SECOND * seconds;
    }
}
