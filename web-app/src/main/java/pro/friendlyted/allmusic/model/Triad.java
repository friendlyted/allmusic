package pro.friendlyted.allmusic.model;

import static pro.friendlyted.allmusic.model.Interval.*;
import static pro.friendlyted.allmusic.model.TriadInvert.*;

public enum Triad {
    m53(m3, M3, FORM_53),
    m6(M3, p4, FORM_6),
    m64(p4, m3, FORM_64),
    M53(M3, m3, FORM_53),
    M6(m3, p4, FORM_6),
    M64(p4, M3, FORM_64),
    d53(m3, m3, FORM_53),
    A53(M3, M3, FORM_53);

    private final Interval lower;
    private final Interval upper;
    private final TriadInvert invert;

    Triad(Interval lower, Interval upper, TriadInvert invert) {
        this.lower = lower;
        this.upper = upper;
        this.invert = invert;
    }

    public Interval getLower() {
        return lower;
    }

    public Interval getUpper() {
        return upper;
    }

    public TriadInvert getInvert() {
        return invert;
    }
}
