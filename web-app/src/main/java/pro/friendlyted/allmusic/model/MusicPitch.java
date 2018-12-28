package pro.friendlyted.allmusic.model;

import java.util.Arrays;
import java.util.stream.Collectors;

import static pro.friendlyted.allmusic.model.Accidental.*;
import static pro.friendlyted.allmusic.model.MidiPitch.*;

public enum MusicPitch {

    A0(Octave.C1, Pitch.A, NATURAL, PITCH_21),
    A0_SHARP(Octave.C1, Pitch.A, SHARP, PITCH_22),
    A0_DOUBLE_SHARP(Octave.C1, Pitch.A, DOUBLE_SHARP, PITCH_23),
    B0_DOUBLE_FLAT(Octave.C1, Pitch.B, DOUBLE_FLAT, PITCH_21),
    B0_FLAT(Octave.C1, Pitch.B, FLAT, PITCH_22),
    B0(Octave.C1, Pitch.B, NATURAL, PITCH_23),
    B0_SHARP(Octave.C1, Pitch.B, SHARP, PITCH_24),
    B0_DOUBLE_SHARP(Octave.C1, Pitch.B, DOUBLE_SHARP, PITCH_25),

    C1_DOUBLE_FLAT(Octave.C1, Pitch.C, DOUBLE_FLAT, PITCH_22),
    C1_FLAT(Octave.C1, Pitch.C, FLAT, PITCH_23),
    C1(Octave.C1, Pitch.C, NATURAL, PITCH_24),
    C1_SHARP(Octave.C1, Pitch.C, SHARP, PITCH_25),
    C1_DOUBLE_SHARP(Octave.C1, Pitch.C, DOUBLE_SHARP, PITCH_26),
    D1_DOUBLE_FLAT(Octave.C1, Pitch.D, DOUBLE_FLAT, PITCH_24),
    D1_FLAT(Octave.C1, Pitch.D, FLAT, PITCH_25),
    D1(Octave.C1, Pitch.D, NATURAL, PITCH_26),
    D1_SHARP(Octave.C1, Pitch.D, SHARP, PITCH_27),
    D1_DOUBLE_SHARP(Octave.C1, Pitch.D, DOUBLE_SHARP, PITCH_28),
    E1_DOUBLE_FLAT(Octave.C1, Pitch.E, DOUBLE_FLAT, PITCH_26),
    E1_FLAT(Octave.C1, Pitch.E, FLAT, PITCH_27),
    E1(Octave.C1, Pitch.E, NATURAL, PITCH_28),
    E1_SHARP(Octave.C1, Pitch.E, SHARP, PITCH_29),
    E1_DOUBLE_SHARP(Octave.C1, Pitch.E, DOUBLE_SHARP, PITCH_30),
    F1_DOUBLE_FLAT(Octave.C1, Pitch.F, DOUBLE_FLAT, PITCH_27),
    F1_FLAT(Octave.C1, Pitch.F, FLAT, PITCH_28),
    F1(Octave.C1, Pitch.F, NATURAL, PITCH_29),
    F1_SHARP(Octave.C1, Pitch.F, SHARP, PITCH_30),
    F1_DOUBLE_SHARP(Octave.C1, Pitch.F, DOUBLE_SHARP, PITCH_31),
    G1_DOUBLE_FLAT(Octave.C1, Pitch.G, DOUBLE_FLAT, PITCH_29),
    G1_FLAT(Octave.C1, Pitch.G, FLAT, PITCH_30),
    G1(Octave.C1, Pitch.G, NATURAL, PITCH_31),
    G1_SHARP(Octave.C1, Pitch.G, SHARP, PITCH_32),
    G1_DOUBLE_SHARP(Octave.C1, Pitch.G, DOUBLE_SHARP, PITCH_33),
    A1_DOUBLE_FLAT(Octave.C1, Pitch.A, DOUBLE_FLAT, PITCH_31),
    A1_FLAT(Octave.C1, Pitch.A, FLAT, PITCH_32),
    A1(Octave.C1, Pitch.A, NATURAL, PITCH_33),
    A1_SHARP(Octave.C1, Pitch.A, SHARP, PITCH_34),
    A1_DOUBLE_SHARP(Octave.C1, Pitch.A, DOUBLE_SHARP, PITCH_35),
    B1_DOUBLE_FLAT(Octave.C1, Pitch.B, DOUBLE_FLAT, PITCH_33),
    B1_FLAT(Octave.C1, Pitch.B, FLAT, PITCH_34),
    B1(Octave.C1, Pitch.B, NATURAL, PITCH_35),
    B1_SHARP(Octave.C1, Pitch.B, SHARP, PITCH_36),
    B1_DOUBLE_SHARP(Octave.C1, Pitch.B, DOUBLE_SHARP, PITCH_37),

    C2_DOUBLE_FLAT(Octave.C2, Pitch.C, DOUBLE_FLAT, PITCH_34),
    C2_FLAT(Octave.C2, Pitch.C, FLAT, PITCH_35),
    C2(Octave.C2, Pitch.C, NATURAL, PITCH_36),
    C2_SHARP(Octave.C2, Pitch.C, SHARP, PITCH_37),
    C2_DOUBLE_SHARP(Octave.C2, Pitch.C, DOUBLE_SHARP, PITCH_38),
    D2_DOUBLE_FLAT(Octave.C2, Pitch.D, DOUBLE_FLAT, PITCH_36),
    D2_FLAT(Octave.C2, Pitch.D, FLAT, PITCH_37),
    D2(Octave.C2, Pitch.D, NATURAL, PITCH_38),
    D2_SHARP(Octave.C2, Pitch.D, SHARP, PITCH_39),
    D2_DOUBLE_SHARP(Octave.C2, Pitch.D, DOUBLE_SHARP, PITCH_40),
    E2_DOUBLE_FLAT(Octave.C2, Pitch.E, DOUBLE_FLAT, PITCH_38),
    E2_FLAT(Octave.C2, Pitch.E, FLAT, PITCH_39),
    E2(Octave.C2, Pitch.E, NATURAL, PITCH_40),
    E2_SHARP(Octave.C2, Pitch.E, SHARP, PITCH_41),
    E2_DOUBLE_SHARP(Octave.C2, Pitch.E, DOUBLE_SHARP, PITCH_42),
    F2_DOUBLE_FLAT(Octave.C2, Pitch.F, DOUBLE_FLAT, PITCH_39),
    F2_FLAT(Octave.C2, Pitch.F, FLAT, PITCH_40),
    F2(Octave.C2, Pitch.F, NATURAL, PITCH_41),
    F2_SHARP(Octave.C2, Pitch.F, SHARP, PITCH_42),
    F2_DOUBLE_SHARP(Octave.C2, Pitch.F, DOUBLE_SHARP, PITCH_43),
    G2_DOUBLE_FLAT(Octave.C2, Pitch.G, DOUBLE_FLAT, PITCH_41),
    G2_FLAT(Octave.C2, Pitch.G, FLAT, PITCH_42),
    G2(Octave.C2, Pitch.G, NATURAL, PITCH_43),
    G2_SHARP(Octave.C2, Pitch.G, SHARP, PITCH_44),
    G2_DOUBLE_SHARP(Octave.C2, Pitch.G, DOUBLE_SHARP, PITCH_45),
    A2_DOUBLE_FLAT(Octave.C2, Pitch.A, DOUBLE_FLAT, PITCH_43),
    A2_FLAT(Octave.C2, Pitch.A, FLAT, PITCH_44),
    A2(Octave.C2, Pitch.A, NATURAL, PITCH_45),
    A2_SHARP(Octave.C2, Pitch.A, SHARP, PITCH_46),
    A2_DOUBLE_SHARP(Octave.C2, Pitch.A, DOUBLE_SHARP, PITCH_47),
    B2_DOUBLE_FLAT(Octave.C2, Pitch.B, DOUBLE_FLAT, PITCH_45),
    B2_FLAT(Octave.C2, Pitch.B, FLAT, PITCH_46),
    B2(Octave.C2, Pitch.B, NATURAL, PITCH_47),
    B2_SHARP(Octave.C2, Pitch.B, SHARP, PITCH_48),
    B2_DOUBLE_SHARP(Octave.C2, Pitch.B, DOUBLE_SHARP, PITCH_49),

    C3_DOUBLE_FLAT(Octave.C3, Pitch.C, DOUBLE_FLAT, PITCH_46),
    C3_FLAT(Octave.C3, Pitch.C, FLAT, PITCH_47),
    C3(Octave.C3, Pitch.C, NATURAL, PITCH_48),
    C3_SHARP(Octave.C3, Pitch.C, SHARP, PITCH_49),
    C3_DOUBLE_SHARP(Octave.C3, Pitch.C, DOUBLE_SHARP, PITCH_50),
    D3_DOUBLE_FLAT(Octave.C3, Pitch.D, DOUBLE_FLAT, PITCH_48),
    D3_FLAT(Octave.C3, Pitch.D, FLAT, PITCH_49),
    D3(Octave.C3, Pitch.D, NATURAL, PITCH_50),
    D3_SHARP(Octave.C3, Pitch.D, SHARP, PITCH_51),
    D3_DOUBLE_SHARP(Octave.C3, Pitch.D, DOUBLE_SHARP, PITCH_52),
    E3_DOUBLE_FLAT(Octave.C3, Pitch.E, DOUBLE_FLAT, PITCH_50),
    E3_FLAT(Octave.C3, Pitch.E, FLAT, PITCH_51),
    E3(Octave.C3, Pitch.E, NATURAL, PITCH_52),
    E3_SHARP(Octave.C3, Pitch.E, SHARP, PITCH_53),
    E3_DOUBLE_SHARP(Octave.C3, Pitch.E, DOUBLE_SHARP, PITCH_54),
    F3_DOUBLE_FLAT(Octave.C3, Pitch.F, DOUBLE_FLAT, PITCH_51),
    F3_FLAT(Octave.C3, Pitch.F, FLAT, PITCH_52),
    F3(Octave.C3, Pitch.F, NATURAL, PITCH_53),
    F3_SHARP(Octave.C3, Pitch.F, SHARP, PITCH_54),
    F3_DOUBLE_SHARP(Octave.C3, Pitch.F, DOUBLE_SHARP, PITCH_55),
    G3_DOUBLE_FLAT(Octave.C3, Pitch.G, DOUBLE_FLAT, PITCH_53),
    G3_FLAT(Octave.C3, Pitch.G, FLAT, PITCH_54),
    G3(Octave.C3, Pitch.G, NATURAL, PITCH_55),
    G3_SHARP(Octave.C3, Pitch.G, SHARP, PITCH_56),
    G3_DOUBLE_SHARP(Octave.C3, Pitch.G, DOUBLE_SHARP, PITCH_57),
    A3_DOUBLE_FLAT(Octave.C3, Pitch.A, DOUBLE_FLAT, PITCH_55),
    A3_FLAT(Octave.C3, Pitch.A, FLAT, PITCH_56),
    A3(Octave.C3, Pitch.A, NATURAL, PITCH_57),
    A3_SHARP(Octave.C3, Pitch.A, SHARP, PITCH_58),
    A3_DOUBLE_SHARP(Octave.C3, Pitch.A, DOUBLE_SHARP, PITCH_59),
    B3_DOUBLE_FLAT(Octave.C3, Pitch.B, DOUBLE_FLAT, PITCH_57),
    B3_FLAT(Octave.C3, Pitch.B, FLAT, PITCH_58),
    B3(Octave.C3, Pitch.B, NATURAL, PITCH_59),
    B3_SHARP(Octave.C3, Pitch.B, SHARP, PITCH_60),
    B3_DOUBLE_SHARP(Octave.C3, Pitch.B, DOUBLE_SHARP, PITCH_61),

    C4_DOUBLE_FLAT(Octave.C4, Pitch.C, DOUBLE_FLAT, PITCH_58),
    C4_FLAT(Octave.C4, Pitch.C, FLAT, PITCH_59),
    C4(Octave.C4, Pitch.C, NATURAL, PITCH_60),
    C4_SHARP(Octave.C4, Pitch.C, SHARP, PITCH_61),
    C4_DOUBLE_SHARP(Octave.C4, Pitch.C, DOUBLE_SHARP, PITCH_62),
    D4_DOUBLE_FLAT(Octave.C4, Pitch.D, DOUBLE_FLAT, PITCH_60),
    D4_FLAT(Octave.C4, Pitch.D, FLAT, PITCH_61),
    D4(Octave.C4, Pitch.D, NATURAL, PITCH_62),
    D4_SHARP(Octave.C4, Pitch.D, SHARP, PITCH_63),
    D4_DOUBLE_SHARP(Octave.C4, Pitch.D, DOUBLE_SHARP, PITCH_64),
    E4_DOUBLE_FLAT(Octave.C4, Pitch.E, DOUBLE_FLAT, PITCH_62),
    E4_FLAT(Octave.C4, Pitch.E, FLAT, PITCH_63),
    E4(Octave.C4, Pitch.E, NATURAL, PITCH_64),
    E4_SHARP(Octave.C4, Pitch.E, SHARP, PITCH_65),
    E4_DOUBLE_SHARP(Octave.C4, Pitch.E, DOUBLE_SHARP, PITCH_66),
    F4_DOUBLE_FLAT(Octave.C4, Pitch.F, DOUBLE_FLAT, PITCH_63),
    F4_FLAT(Octave.C4, Pitch.F, FLAT, PITCH_64),
    F4(Octave.C4, Pitch.F, NATURAL, PITCH_65),
    F4_SHARP(Octave.C4, Pitch.F, SHARP, PITCH_66),
    F4_DOUBLE_SHARP(Octave.C4, Pitch.F, DOUBLE_SHARP, PITCH_67),
    G4_DOUBLE_FLAT(Octave.C4, Pitch.G, DOUBLE_FLAT, PITCH_65),
    G4_FLAT(Octave.C4, Pitch.G, FLAT, PITCH_66),
    G4(Octave.C4, Pitch.G, NATURAL, PITCH_67),
    G4_SHARP(Octave.C4, Pitch.G, SHARP, PITCH_68),
    G4_DOUBLE_SHARP(Octave.C4, Pitch.G, DOUBLE_SHARP, PITCH_69),
    A4_DOUBLE_FLAT(Octave.C4, Pitch.A, DOUBLE_FLAT, PITCH_67),
    A4_FLAT(Octave.C4, Pitch.A, FLAT, PITCH_68),
    A4(Octave.C4, Pitch.A, NATURAL, PITCH_69),
    A4_SHARP(Octave.C4, Pitch.A, SHARP, PITCH_70),
    A4_DOUBLE_SHARP(Octave.C4, Pitch.A, DOUBLE_SHARP, PITCH_71),
    B4_DOUBLE_FLAT(Octave.C4, Pitch.B, DOUBLE_FLAT, PITCH_69),
    B4_FLAT(Octave.C4, Pitch.B, FLAT, PITCH_70),
    B4(Octave.C4, Pitch.B, NATURAL, PITCH_71),
    B4_SHARP(Octave.C4, Pitch.B, SHARP, PITCH_72),
    B4_DOUBLE_SHARP(Octave.C4, Pitch.B, DOUBLE_SHARP, PITCH_73),

    C5_DOUBLE_FLAT(Octave.C5, Pitch.C, DOUBLE_FLAT, PITCH_70),
    C5_FLAT(Octave.C5, Pitch.C, FLAT, PITCH_71),
    C5(Octave.C5, Pitch.C, NATURAL, PITCH_72),
    C5_SHARP(Octave.C5, Pitch.C, SHARP, PITCH_73),
    C5_DOUBLE_SHARP(Octave.C5, Pitch.C, DOUBLE_SHARP, PITCH_74),
    D5_DOUBLE_FLAT(Octave.C5, Pitch.D, DOUBLE_FLAT, PITCH_72),
    D5_FLAT(Octave.C5, Pitch.D, FLAT, PITCH_73),
    D5(Octave.C5, Pitch.D, NATURAL, PITCH_74),
    D5_SHARP(Octave.C5, Pitch.D, SHARP, PITCH_75),
    D5_DOUBLE_SHARP(Octave.C5, Pitch.D, DOUBLE_SHARP, PITCH_76),
    E5_DOUBLE_FLAT(Octave.C5, Pitch.E, DOUBLE_FLAT, PITCH_74),
    E5_FLAT(Octave.C5, Pitch.E, FLAT, PITCH_75),
    E5(Octave.C5, Pitch.E, NATURAL, PITCH_76),
    E5_SHARP(Octave.C5, Pitch.E, SHARP, PITCH_77),
    E5_DOUBLE_SHARP(Octave.C5, Pitch.E, DOUBLE_SHARP, PITCH_78),
    F5_DOUBLE_FLAT(Octave.C5, Pitch.F, DOUBLE_FLAT, PITCH_75),
    F5_FLAT(Octave.C5, Pitch.F, FLAT, PITCH_76),
    F5(Octave.C5, Pitch.F, NATURAL, PITCH_77),
    F5_SHARP(Octave.C5, Pitch.F, SHARP, PITCH_78),
    F5_DOUBLE_SHARP(Octave.C5, Pitch.F, DOUBLE_SHARP, PITCH_79),
    G5_DOUBLE_FLAT(Octave.C5, Pitch.G, DOUBLE_FLAT, PITCH_77),
    G5_FLAT(Octave.C5, Pitch.G, FLAT, PITCH_78),
    G5(Octave.C5, Pitch.G, NATURAL, PITCH_79),
    G5_SHARP(Octave.C5, Pitch.G, SHARP, PITCH_80),
    G5_DOUBLE_SHARP(Octave.C5, Pitch.G, DOUBLE_SHARP, PITCH_81),
    A5_DOUBLE_FLAT(Octave.C5, Pitch.A, DOUBLE_FLAT, PITCH_79),
    A5_FLAT(Octave.C5, Pitch.A, FLAT, PITCH_80),
    A5(Octave.C5, Pitch.A, NATURAL, PITCH_81),
    A5_SHARP(Octave.C5, Pitch.A, SHARP, PITCH_82),
    A5_DOUBLE_SHARP(Octave.C5, Pitch.A, DOUBLE_SHARP, PITCH_83),
    B5_DOUBLE_FLAT(Octave.C5, Pitch.B, DOUBLE_FLAT, PITCH_81),
    B5_FLAT(Octave.C5, Pitch.B, FLAT, PITCH_82),
    B5(Octave.C5, Pitch.B, NATURAL, PITCH_83),
    B5_SHARP(Octave.C5, Pitch.B, SHARP, PITCH_84),
    B5_DOUBLE_SHARP(Octave.C5, Pitch.B, DOUBLE_SHARP, PITCH_85),

    C6_DOUBLE_FLAT(Octave.C6, Pitch.C, DOUBLE_FLAT, PITCH_82),
    C6_FLAT(Octave.C6, Pitch.C, FLAT, PITCH_83),
    C6(Octave.C6, Pitch.C, NATURAL, PITCH_84),
    C6_SHARP(Octave.C6, Pitch.C, SHARP, PITCH_85),
    C6_DOUBLE_SHARP(Octave.C6, Pitch.C, DOUBLE_SHARP, PITCH_86),
    D6_DOUBLE_FLAT(Octave.C6, Pitch.D, DOUBLE_FLAT, PITCH_84),
    D6_FLAT(Octave.C6, Pitch.D, FLAT, PITCH_85),
    D6(Octave.C6, Pitch.D, NATURAL, PITCH_86),
    D6_SHARP(Octave.C6, Pitch.D, SHARP, PITCH_87),
    D6_DOUBLE_SHARP(Octave.C6, Pitch.D, DOUBLE_SHARP, PITCH_88),
    E6_DOUBLE_FLAT(Octave.C6, Pitch.E, DOUBLE_FLAT, PITCH_86),
    E6_FLAT(Octave.C6, Pitch.E, FLAT, PITCH_87),
    E6(Octave.C6, Pitch.E, NATURAL, PITCH_88),
    E6_SHARP(Octave.C6, Pitch.E, SHARP, PITCH_89),
    E6_DOUBLE_SHARP(Octave.C6, Pitch.E, DOUBLE_SHARP, PITCH_90),
    F6_DOUBLE_FLAT(Octave.C6, Pitch.F, DOUBLE_FLAT, PITCH_87),
    F6_FLAT(Octave.C6, Pitch.F, FLAT, PITCH_88),
    F6(Octave.C6, Pitch.F, NATURAL, PITCH_89),
    F6_SHARP(Octave.C6, Pitch.F, SHARP, PITCH_90),
    F6_DOUBLE_SHARP(Octave.C6, Pitch.F, DOUBLE_SHARP, PITCH_91),
    G6_DOUBLE_FLAT(Octave.C6, Pitch.G, DOUBLE_FLAT, PITCH_89),
    G6_FLAT(Octave.C6, Pitch.G, FLAT, PITCH_90),
    G6(Octave.C6, Pitch.G, NATURAL, PITCH_91),
    G6_SHARP(Octave.C6, Pitch.G, SHARP, PITCH_92),
    G6_DOUBLE_SHARP(Octave.C6, Pitch.G, DOUBLE_SHARP, PITCH_93),
    A6_DOUBLE_FLAT(Octave.C6, Pitch.A, DOUBLE_FLAT, PITCH_91),
    A6_FLAT(Octave.C6, Pitch.A, FLAT, PITCH_92),
    A6(Octave.C6, Pitch.A, NATURAL, PITCH_93),
    A6_SHARP(Octave.C6, Pitch.A, SHARP, PITCH_94),
    A6_DOUBLE_SHARP(Octave.C6, Pitch.A, DOUBLE_SHARP, PITCH_95),
    B6_DOUBLE_FLAT(Octave.C6, Pitch.B, DOUBLE_FLAT, PITCH_93),
    B6_FLAT(Octave.C6, Pitch.B, FLAT, PITCH_94),
    B6(Octave.C6, Pitch.B, NATURAL, PITCH_95),
    B6_SHARP(Octave.C6, Pitch.B, SHARP, PITCH_96),
    B6_DOUBLE_SHARP(Octave.C6, Pitch.B, DOUBLE_SHARP, PITCH_97),

    C7_DOUBLE_FLAT(Octave.C7, Pitch.C, DOUBLE_FLAT, PITCH_94),
    C7_FLAT(Octave.C7, Pitch.C, FLAT, PITCH_95),
    C7(Octave.C7, Pitch.C, NATURAL, PITCH_96),
    C7_SHARP(Octave.C7, Pitch.C, SHARP, PITCH_97),
    C7_DOUBLE_SHARP(Octave.C7, Pitch.C, DOUBLE_SHARP, PITCH_98),
    D7_DOUBLE_FLAT(Octave.C7, Pitch.D, DOUBLE_FLAT, PITCH_96),
    D7_FLAT(Octave.C7, Pitch.D, FLAT, PITCH_97),
    D7(Octave.C7, Pitch.D, NATURAL, PITCH_98),
    D7_SHARP(Octave.C7, Pitch.D, SHARP, PITCH_99),
    D7_DOUBLE_SHARP(Octave.C7, Pitch.D, DOUBLE_SHARP, PITCH_100),
    E7_DOUBLE_FLAT(Octave.C7, Pitch.E, DOUBLE_FLAT, PITCH_98),
    E7_FLAT(Octave.C7, Pitch.E, FLAT, PITCH_99),
    E7(Octave.C7, Pitch.E, NATURAL, PITCH_100),
    E7_SHARP(Octave.C7, Pitch.E, SHARP, PITCH_101),
    E7_DOUBLE_SHARP(Octave.C7, Pitch.E, DOUBLE_SHARP, PITCH_102),
    F7_DOUBLE_FLAT(Octave.C7, Pitch.F, DOUBLE_FLAT, PITCH_99),
    F7_FLAT(Octave.C7, Pitch.F, FLAT, PITCH_100),
    F7(Octave.C7, Pitch.F, NATURAL, PITCH_101),
    F7_SHARP(Octave.C7, Pitch.F, SHARP, PITCH_102),
    F7_DOUBLE_SHARP(Octave.C7, Pitch.F, DOUBLE_SHARP, PITCH_103),
    G7_DOUBLE_FLAT(Octave.C7, Pitch.G, DOUBLE_FLAT, PITCH_101),
    G7_FLAT(Octave.C7, Pitch.G, FLAT, PITCH_102),
    G7(Octave.C7, Pitch.G, NATURAL, PITCH_103),
    G7_SHARP(Octave.C7, Pitch.G, SHARP, PITCH_104),
    G7_DOUBLE_SHARP(Octave.C7, Pitch.G, DOUBLE_SHARP, PITCH_105),
    A7_DOUBLE_FLAT(Octave.C7, Pitch.A, DOUBLE_FLAT, PITCH_103),
    A7_FLAT(Octave.C7, Pitch.A, FLAT, PITCH_104),
    A7(Octave.C7, Pitch.A, NATURAL, PITCH_105),
    A7_SHARP(Octave.C7, Pitch.A, SHARP, PITCH_106),
    A7_DOUBLE_SHARP(Octave.C7, Pitch.A, DOUBLE_SHARP, PITCH_107),
    B7_DOUBLE_FLAT(Octave.C7, Pitch.B, DOUBLE_FLAT, PITCH_105),
    B7_FLAT(Octave.C7, Pitch.B, FLAT, PITCH_106),
    B7(Octave.C7, Pitch.B, NATURAL, PITCH_107),
    B7_SHARP(Octave.C7, Pitch.B, SHARP, PITCH_108),

    C8_DOUBLE_FLAT(Octave.C8, Pitch.C, DOUBLE_FLAT, PITCH_106),
    C8_FLAT(Octave.C8, Pitch.C, FLAT, PITCH_107),
    C8(Octave.C8, Pitch.C, NATURAL, PITCH_108);

    private final Octave octave;
    private final Pitch pitch;
    private final Accidental accidental;
    private final MidiPitch midiPitch;

    MusicPitch(Octave octave, Pitch pitch, Accidental accidental, MidiPitch midiPitch) {
        this.octave = octave;
        this.pitch = pitch;
        this.accidental = accidental;
        this.midiPitch = midiPitch;
    }

    public MidiPitch getMidiPitch() {
        return midiPitch;
    }

    public Octave getOctave() {
        return octave;
    }

    public Accidental getAccidental() {
        return accidental;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public static MusicPitch[] firstOctave() {
        return new MusicPitch[]{
            C4_FLAT, C4, C4_SHARP,
            D4_FLAT, D4, D4_SHARP,
            E4_FLAT, E4, E4_SHARP,
            F4_FLAT, F4, F4_SHARP,
            G4_FLAT, G4, G4_SHARP,
            A4_FLAT, A4, A4_SHARP,
            B4_FLAT, B4, B4_SHARP
        };
    }

    public static MusicPitch[] naturals() {
        return new MusicPitch[]{
            A0, B0,
            C1, D1, E1, F1, G1, A1, B1,
            C2, D2, E2, F2, G2, A2, B2,
            C3, D3, E3, F3, G3, A3, B3,
            C4, D4, E4, F4, G4, A4, B4,
            C5, D5, E5, F5, G5, A5, B5,
            C6, D6, E6, F6, G6, A6, B6,
            C7, D7, E7, F7, G7, A7, B7, C8
        };
    }

    public static MusicPitch[] forPitch(MidiPitch pitch) {
        return Arrays.stream(values())
            .filter(mn -> mn.getMidiPitch() == pitch)
            .collect(Collectors.toList())
            .toArray(new MusicPitch[0]);
    }

    public static MusicPitch forValues(Octave octave, Pitch pitch, Accidental accidental) {
        final String name = pitch.name() + octave.getNumber() + ((accidental == null || accidental == NATURAL) ? "" : "_" + accidental.name());
        return valueOf(name);
    }

}
