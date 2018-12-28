package pro.friendlyted.allmusic.tools;

import pro.friendlyted.allmusic.model.Accidental;
import pro.friendlyted.allmusic.model.Interval;
import pro.friendlyted.allmusic.model.MusicPitch;
import pro.friendlyted.allmusic.model.Octave;


public class IntervalSupplier {

    public static IntervalPitches getInterval() {
        final MusicPitch lower = MusicPitch.forValues(
                Octave.C4,
                RandomValues.randomPitch(),
                Accidental.NATURAL
        );

        final Interval randomInterval = RandomValues.randomInterval();
        try {
            final MusicPitch upper = IntervalBuilder.build(lower, randomInterval);
            return new IntervalPitches(randomInterval, lower, upper);
        } catch (OutOfDiapasonException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static Quest getIntervalQuest() {
        final IntervalPitches interval = getInterval();
        final String question = interval.getLower().getMidiPitch().pitch() + "-" + interval.getUpper().getMidiPitch().pitch();
        final String answer = interval.getInterval().name();
        return new Quest(question, answer);
    }
}
