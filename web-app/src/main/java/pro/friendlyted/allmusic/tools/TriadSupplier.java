package pro.friendlyted.allmusic.tools;

import pro.friendlyted.allmusic.model.Accidental;
import pro.friendlyted.allmusic.model.MusicPitch;
import pro.friendlyted.allmusic.model.Octave;
import pro.friendlyted.allmusic.model.Triad;

public class TriadSupplier {

    public static TriadChord getTriad() {
        final MusicPitch lower = MusicPitch.forValues(
                Octave.C4,
                RandomValues.randomPitch(),
                Accidental.NATURAL
        );

        final Triad triad = RandomValues.randomTriad();
        try {
            return TriadBuilder.build(lower, triad);
        } catch (OutOfDiapasonException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static Quest getTriadQuest() {
        final TriadChord chord = getTriad();
        final String question = chord.getRoot().pitch() + "-" +
                chord.getThird().pitch() + "-" +
                chord.getFifth().pitch();
        final String answer = chord.getTriad().name();
        return new Quest(question, answer);
    }
}
