package pro.friendlyted.allmusic.tools;

import pro.friendlyted.allmusic.model.Interval;
import pro.friendlyted.allmusic.model.MusicPitch;

import java.io.Serializable;

public class IntervalPitches implements Serializable {

    private Interval interval;
    private MusicPitch lower;
    private MusicPitch upper;

    public IntervalPitches() {
    }

    public IntervalPitches(Interval interval, MusicPitch lower, MusicPitch upper) {
        this.interval = interval;
        this.lower = lower;
        this.upper = upper;
    }

    public MusicPitch getLower() {
        return lower;
    }

    public void setLower(MusicPitch lower) {
        this.lower = lower;
    }

    public MusicPitch getUpper() {
        return upper;
    }

    public void setUpper(MusicPitch upper) {
        this.upper = upper;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }
}
