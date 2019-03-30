package me.wm.hybrid.ro.util.Updater;

public enum UpdateTime {
    TICK1(1L), SECOND(20L), MINUTE(1200L);

    private long time;

    UpdateTime(long paramLong) {
        this.time = paramLong;
    }

    public long getTime() {
        return this.time;
    }
}
