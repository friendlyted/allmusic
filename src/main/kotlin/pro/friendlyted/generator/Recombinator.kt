package pro.friendlyted.generator

import pro.friendlyted.api.MidiPitch

class Recombinator {

    fun recombineMidiPitches(
        pitchesList: List<MidiPitch>,
        times: Int,
        chordValidator: (List<MidiPitch>, MidiPitch) -> Boolean
    ): List<List<MidiPitch>> {
        val chordPitches = ArrayList<List<MidiPitch>>()
        (0 until times).forEach {
            chordPitches.add(pitchesList)
        }

        val chordList = combineLists(
            chordPitches
        ) { chord, midiPitch ->
            if (chordValidator(chord, midiPitch)) chord.add(midiPitch)
        }

        for (pitchList in ArrayList<List<MidiPitch>>(chordList)) {
            if (pitchList.size != times) {
                chordList.remove(pitchList)
            }
        }

        return chordList
    }

    private fun <T> combineLists(
        sourceLists: List<List<T>>,
        combinator: (MutableList<T>, T) -> Unit
    ): MutableList<MutableList<T>> {
        var fullSize = 1
        for (list in sourceLists) {
            fullSize *= list.size
        }

        val result = ArrayList<MutableList<T>>()
        for (i in 0 until fullSize) {
            result.add(ArrayList())
        }

        enrichList(sourceLists, 0, result, 0, fullSize, combinator)

        return result
    }

    private fun <T> enrichList(
        sourceList: List<List<T>>,
        sourceDeep: Int,
        resultList: MutableList<MutableList<T>>,
        resultOffset: Int,
        blockSize: Int,
        combinator: (MutableList<T>, T) -> Unit
    ) {
        val list = sourceList[sourceDeep]
        val myBlockSize = blockSize / list.size
        for (myBlock in list.indices) {
            for (myOffset in 0 until myBlockSize) {
                val targetList = resultList[resultOffset + myBlock * myBlockSize + myOffset]
                val itemToAdd = list[myBlock]
                combinator(targetList, itemToAdd)
            }
            if (sourceList.size > sourceDeep + 1) {
                enrichList(
                    sourceList,
                    sourceDeep + 1,
                    resultList,
                    resultOffset + myBlock * myBlockSize,
                    myBlockSize,
                    combinator
                )
            }
        }
    }

}
