package pro.friendlyted.allmusic.tools;

import pro.friendlyted.allmusic.model.Interval;
import pro.friendlyted.allmusic.model.MidiPitch;
import pro.friendlyted.allmusic.model.MusicPitch;
import pro.friendlyted.allmusic.model.Pitch;

public class IntervalBuilder {

    public static MusicPitch build(MusicPitch base, Interval interval) throws OutOfDiapasonException {
        final int lowerPitchValue = base.getMidiPitch().pitch();
        final int upperPitchValue = lowerPitchValue + interval.getSemitonesCount();

        final int lowerStep = base.getPitch().ordinal();
        final int upperStep = (lowerStep + interval.getStepCount()) % 7;
        final Pitch upperPitch = Pitch.values()[upperStep];

        final MusicPitch[] variants = MusicPitch.forPitch(MidiPitch.forPitch(upperPitchValue));
        for (MusicPitch variant : variants) {
            if (variant.getPitch() == upperPitch) {
                return variant;
            }
        }

        throw new OutOfDiapasonException("Cannot create " + interval + " from " + base);
    }
}
