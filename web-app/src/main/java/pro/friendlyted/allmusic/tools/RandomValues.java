package pro.friendlyted.allmusic.tools;

import pro.friendlyted.allmusic.model.Accidental;
import pro.friendlyted.allmusic.model.Interval;
import pro.friendlyted.allmusic.model.Pitch;
import pro.friendlyted.allmusic.model.Triad;

import java.util.Random;

public class RandomValues {

    private static Random RANDOM = new Random();

    public static Pitch randomPitch() {
        return randomFrom(Pitch.class);
    }

    public static Accidental randomAccidental() {
        return randomFrom(Accidental.class);
    }

    public static Interval randomInterval() {
        final Interval result = randomFrom(Interval.class);
        if (result == Interval.p1) {
            //прима?
            return randomInterval();
        }
        if (result.getSemitonesCount() == 6 && RANDOM.nextBoolean()) {
            //пореже
            return randomInterval();
        }
        return result;
    }

    public static Triad randomTriad() {
        return randomFrom(Triad.class);
    }

    private static <E extends Enum<E>> E randomFrom(Class<E> enumClass) {
        final E[] values = enumClass.getEnumConstants();
        return values[RANDOM.nextInt(values.length)];
    }
}
