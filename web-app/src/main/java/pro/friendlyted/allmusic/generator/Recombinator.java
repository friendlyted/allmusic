package pro.friendlyted.allmusic.generator;

import pro.friendlyted.allmusic.model.Interval;
import pro.friendlyted.allmusic.model.MidiPitch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Recombinator {

    public static List<List<MidiPitch>> recombineMidiPitches(List<MidiPitch> pitchesList, int times) {
        final List<List<MidiPitch>> chordPitches = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            chordPitches.add(pitchesList);
        }

        final List<List<MidiPitch>> chordList = Recombinator.combineLists(chordPitches, Recombinator::addPitch);
        for (List<MidiPitch> pitchList : new ArrayList<>(chordList)) {
            if (pitchList.size() != times) {
                chordList.remove(pitchList);
            }
        }

        return chordList;
    }

    private static void addPitch(List<MidiPitch> chord, MidiPitch midiPitch) {
        //первый экстерном
        if (chord.isEmpty()) {
            chord.add(midiPitch);
            return;
        }

        //уже есть
        if (chord.contains(midiPitch)) {
            return;
        }

        //ниже имеющихся
        if (midiPitch.pitch() < chord.get(chord.size() - 1).pitch()) {
            return;
        }

        //больше октавы
        if (midiPitch.pitch() > chord.get(0).pitch() + Interval.p8.getSemitonesCount()) {
            return;
        }

        chord.add(midiPitch);

//        final int upperPitch = chord.get(chord.size() - 1).pitch();
//
//        if (midiPitch.pitch() >= upperPitch + 3 && midiPitch.pitch() <= upperPitch + 5 && midiPitch.pitch() < chord.get(0).pitch()+10) {
//            chord.add(midiPitch);
//        }
    }

    public static <T> List<List<T>> combineLists(List<List<T>> sourceLists, BiConsumer<List<T>, T> combinator) {
        int fullSize = 1;
        for (List list : sourceLists) {
            fullSize *= list.size();
        }

        final List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < fullSize; i++) {
            result.add(new ArrayList<>());
        }

        enrichList(sourceLists, 0, result, 0, fullSize, combinator);

        return result;
    }

    private static <T> void enrichList(List<List<T>> sourceList, int sourceDeep, List<List<T>> resultList, int resultOffset, int blockSize, BiConsumer<List<T>, T> combinator) {
        final List<T> list = sourceList.get(sourceDeep);
        final int myBlockSize = blockSize / list.size();
        for (int myBlock = 0; myBlock < list.size(); myBlock++) {
            for (int myOffset = 0; myOffset < myBlockSize; myOffset++) {
                final List<T> targetList = resultList.get(resultOffset + myBlock * myBlockSize + myOffset);
                final T itemToAdd = list.get(myBlock);
                combinator.accept(targetList, itemToAdd);
            }
            if (sourceList.size() > sourceDeep + 1) {
                enrichList(sourceList, sourceDeep + 1, resultList, resultOffset + myBlock * myBlockSize, myBlockSize, combinator);
            }
        }
    }
}
