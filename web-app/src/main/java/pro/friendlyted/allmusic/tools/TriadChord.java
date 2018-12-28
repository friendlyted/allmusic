package pro.friendlyted.allmusic.tools;

import pro.friendlyted.allmusic.model.MidiPitch;
import pro.friendlyted.allmusic.model.Triad;

import java.io.Serializable;

public class TriadChord implements Serializable {

    private Triad triad;
    private MidiPitch root;
    private MidiPitch third;
    private MidiPitch fifth;

    public TriadChord() {
    }

    public Triad getTriad() {
        return triad;
    }

    public void setTriad(Triad triad) {
        this.triad = triad;
    }

    public MidiPitch getRoot() {
        return root;
    }

    public void setRoot(MidiPitch root) {
        this.root = root;
    }

    public MidiPitch getThird() {
        return third;
    }

    public void setThird(MidiPitch third) {
        this.third = third;
    }

    public MidiPitch getFifth() {
        return fifth;
    }

    public void setFifth(MidiPitch fifth) {
        this.fifth = fifth;
    }
}
