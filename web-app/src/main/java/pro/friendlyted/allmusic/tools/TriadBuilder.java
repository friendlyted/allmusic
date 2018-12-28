package pro.friendlyted.allmusic.tools;

import pro.friendlyted.allmusic.model.MusicPitch;
import pro.friendlyted.allmusic.model.Triad;


public class TriadBuilder {

    public static TriadChord build(MusicPitch base, Triad triad) throws OutOfDiapasonException {
        final MusicPitch third = IntervalBuilder.build(base, triad.getLower());
        final MusicPitch fifth = IntervalBuilder.build(third, triad.getUpper());

        final TriadChord result = new TriadChord();

        result.setRoot(base.getMidiPitch());
        result.setThird(third.getMidiPitch());
        result.setFifth(fifth.getMidiPitch());
        result.setTriad(triad);

        return result;
    }
}
