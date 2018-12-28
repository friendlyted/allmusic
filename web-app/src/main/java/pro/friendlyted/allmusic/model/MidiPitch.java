package pro.friendlyted.allmusic.model;

public enum MidiPitch {
    PITCH_21(21),
    PITCH_22(22),
    PITCH_23(23),
    PITCH_24(24),
    PITCH_25(25),
    PITCH_26(26),
    PITCH_27(27),
    PITCH_28(28),
    PITCH_29(29),
    PITCH_30(30),
    PITCH_31(31),
    PITCH_32(32),
    PITCH_33(33),
    PITCH_34(34),
    PITCH_35(35),
    PITCH_36(36),
    PITCH_37(37),
    PITCH_38(38),
    PITCH_39(39),
    PITCH_40(40),
    PITCH_41(41),
    PITCH_42(42),
    PITCH_43(43),
    PITCH_44(44),
    PITCH_45(45),
    PITCH_46(46),
    PITCH_47(47),
    PITCH_48(48),
    PITCH_49(49),
    PITCH_50(50),
    PITCH_51(51),
    PITCH_52(52),
    PITCH_53(53),
    PITCH_54(54),
    PITCH_55(55),
    PITCH_56(56),
    PITCH_57(57),
    PITCH_58(58),
    PITCH_59(59),
    PITCH_60(60),
    PITCH_61(61),
    PITCH_62(62),
    PITCH_63(63),
    PITCH_64(64),
    PITCH_65(65),
    PITCH_66(66),
    PITCH_67(67),
    PITCH_68(68),
    PITCH_69(69),
    PITCH_70(70),
    PITCH_71(71),
    PITCH_72(72),
    PITCH_73(73),
    PITCH_74(74),
    PITCH_75(75),
    PITCH_76(76),
    PITCH_77(77),
    PITCH_78(78),
    PITCH_79(79),
    PITCH_80(80),
    PITCH_81(81),
    PITCH_82(82),
    PITCH_83(83),
    PITCH_84(84),
    PITCH_85(85),
    PITCH_86(86),
    PITCH_87(87),
    PITCH_88(88),
    PITCH_89(89),
    PITCH_90(90),
    PITCH_91(91),
    PITCH_92(92),
    PITCH_93(93),
    PITCH_94(94),
    PITCH_95(95),
    PITCH_96(96),
    PITCH_97(97),
    PITCH_98(98),
    PITCH_99(99),
    PITCH_100(100),
    PITCH_101(101),
    PITCH_102(102),
    PITCH_103(103),
    PITCH_104(104),
    PITCH_105(105),
    PITCH_106(106),
    PITCH_107(107),
    PITCH_108(108);

    private final int pitch;

    MidiPitch(int pitch) {
        this.pitch = pitch;
    }

    public int pitch() {
        return pitch;
    }

    public MidiPitch add(int pitches) {
        return forPitch(pitch + pitches);
    }

    public static MidiPitch forPitch(int pitch) {
        return valueOf("PITCH_" + pitch);
    }
}
