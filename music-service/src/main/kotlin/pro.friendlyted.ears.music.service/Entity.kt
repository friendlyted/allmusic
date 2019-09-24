package pro.friendlyted.ears.music.service

class Entity(val name: String, val eqFunc: (String) -> Boolean) {

    companion object {
        val entities = mapOf(
                "global" to listOf(
                        Entity("start") { it.nearToAny("сначала", "заново", "все сначала", "в начало") },
                        Entity("help") { it.nearToAny("помощь", "ты можешь", "ты умеешь", "умеет навык", "тут делать", "как работает навык", "помоги") }
                ),
                "mode" to listOf(
                        Entity("interval") { it.nearToAny("интервалы") },
                        Entity("triad") { it.nearToAny("трезвучия") },
                        Entity("chord7") { it.nearToAny("септаккорды") },
                        Entity("interval triad") { it.nearToAny("интервалы и трезвучия", "трезвучия и интервалы") },
                        Entity("interval chord7") { it.nearToAny("интервалы и септаккорды", "септаккорды и интервалы") },
                        Entity("triad chord7") { it.nearToAny("трезвучия и септаккорды", "септаккорды и трезвучия") },
                        Entity("interval triad chord7") {
                            it.nearToAny(
                                    "все", "любые",
                                    "интервалы трезвучия и септаккорды", "интервалы септаккорды и трезвучия",
                                    "трезвучия интервалы и септаккоды", "трезвучия септаккорды и интервалы",
                                    "септаккоды интервалы и трезвучия", "септаккоды трезвучия и интервалы"
                            )
                        }
                ),
                "quest" to listOf(
                        Entity("repeat") { it.nearToAny("повтори", "повторить", "еще раз", "сыграй еще раз") },
                        Entity("melodic") { it.nearToAny("мелодически", "по одной ноте", "по одной", "по очереди") },
                        Entity("harmonic") { it.nearToAny("гармонически", "вместе", "слитно") },
                        Entity("giveup") { it.nearToAny("не знаю", "сдаюсь", "ну и что", "что это", "что") },
                        Entity("next") { it.nearToAny("следующий", "дальше", "другой") }
                ),
                "interval" to listOf(
                        Entity("p1") { it.nearToAny("прима", "чистая прима", "прима чистая", "ч1", "ч 1") },
                        Entity("m2") { it.nearToAny("малая секунда", "секунда малая", "м2", "м 2") },
                        Entity("M2") { it.nearToAny("большая секунда", "секунда большая", "б2", "б 2") },
                        Entity("unsure_2") { it.nearTo("секунда") },
                        Entity("m3") { it.nearToAny("малая терция", "терция малая", "м3", "м 3", "трц малая", "малая трц") },
                        Entity("M3") { it.nearToAny("большая тецрия", "терция большая", "б3", "б 3") },
                        Entity("unsure_3") { it.nearTo("терция") },
                        Entity("p4") { listOf("кварта", "чистая кварта", "кварта чистая", "ч4", "ч 4").contains(it) },
                        Entity("tt") {
                            it.nearToAny(
                                    "тритон",
                                    "увеличенная кварта", "кварта увеличенная",
                                    "уменьшенная квинта", "квинта уменьшеннная"
                            )
                        },
                        Entity("p5") { listOf("квинта", "чистая квинта", "квинта чистая", "ч5", "ч 5").contains(it) },
                        Entity("m6") { it.nearToAny("малая секста", "секста малая", "м6", "м 6", "малая секса", "малая секта") },
                        Entity("M6") { it.nearToAny("большая секста", "секста большая", "б6", "б 6") },
                        Entity("unsure_6") { it.nearTo("секста") },
                        Entity("m7") { it.nearToAny("малая септима", "септима малая", "м7", "м 7") },
                        Entity("M7") { it.nearToAny("большая септима", "септима большая", "б7", "б 7") },
                        Entity("unsure_7") { it.nearTo("септима") },
                        Entity("p8") { it.nearToAny("октава", "чистая октава", "октава чистая", "ч8", "ч 8") },
                        Entity("unsure_p") { it.nearToAny("чистая") },
                        Entity("unsure_m") { it.nearToAny("малая") },
                        Entity("unsure_M") { it.nearToAny("большая") }
                ),
                "triad" to listOf(
                        Entity("M53") { listOf("большое трезвучие", "трезвучие большое", "мажорное трезвучие", "трезвучие мажорное").contains(it) || it.replace(" ", "").nearTo("б53") },
                        Entity("M6") { listOf("большой секстаккорд", "секстаккорд большой", "мажорный секстаккорд", "секстаккорд мажорный").contains(it) || it.replace(" ", "").nearTo("б6") },
                        Entity("M64") { listOf("большой квартсекстаккорд", "квартсекстаккорд большой", "мажорный квартсекстаккорд", "квартсекстаккорд мажорный").contains(it) || it.replace(" ", "").nearTo("б64") },
                        Entity("m53") { listOf("малое трезвучие", "трезвучие малое", "минорное трезвучие", "трезвучие минорное").contains(it) || it.replace(" ", "").nearTo("м53") },
                        Entity("m6") { listOf("малый секстаккорд", "секстаккорд малый", "минорный секстаккорд", "секстаккорд минорный").contains(it) || it.replace(" ", "").nearTo("м6") },
                        Entity("m64") { listOf("малый квартсекстаккорд", "квартсекстаккорд малый", "минорный квартсекстаккорд", "квартсекстаккорд минорный").contains(it) || it.replace(" ", "").nearTo("м64") },
                        Entity("d53") { listOf("уменьшенное", "уменьшенное трезвучие").contains(it) || it.replace(" ", "").nearTo("ум53") },
                        Entity("A53") { listOf("увеличенное", "увеличенное трезвучие").contains(it) || it.replace(" ", "").nearTo("ув53") },
                        Entity("unsure_53") { it.nearToAny("трезвучие") },
                        Entity("unsure_t6") { it.nearToAny("секстаккорд") },
                        Entity("unsure_64") { it.nearToAny("квартсекстаккорд") },
                        Entity("unsure_tM") { it.nearToAny("большое", "большой", "мажор", "мажорное", "мажорный", "мажорная") },
                        Entity("unsure_tm") { it.nearToAny("малое", "малый", "минор", "минорное", "минорный", "минорная") }
                ),
                "chord7" to listOf(
                        Entity("MMm7") { listOf("большой септаккорд с увеличенной квинтой", "увеличенный септаккорд").contains(it) || it.nearTo("бУв7") },
                        Entity("MMm65") { listOf("большой квинтсекстаккорд с увеличенной квинтой", "увеличенный квинтсекстаккорд").contains(it) || it.nearTo("бУв65") },
                        Entity("MMm43") { listOf("большой терцквартаккорд с увеличенной квинтой", "увеличенный терцквартаккорд").contains(it) || it.nearTo("бУв43") },
                        Entity("MMm2") { listOf("большой секундаккорд с увеличенной квинтой", "увеличенный секундаккорд").contains(it) || it.nearTo("бУв2") },

                        Entity("MmM7") { listOf("большой мажорный септаккорд").contains(it) || it.nearTo("бМаж7") },
                        Entity("MmM65") { listOf("большой мажорный квинтсекстаккорд").contains(it) || it.nearTo("бМаж65") },
                        Entity("MmM43") { listOf("большой мажорный терцквартаккорд").contains(it) || it.nearTo("бМаж43") },
                        Entity("MmM2") { listOf("большой мажорный секундаккорд").contains(it) || it.nearTo("бМаж2") },

                        Entity("Mmm7") { listOf("малый мажорный септаккорд").contains(it) || it.nearTo("мМаж7") },
                        Entity("Mmm65") { listOf("малый мажорный квинтсекстаккорд").contains(it) || it.nearTo("мМаж65") },
                        Entity("Mmm43") { listOf("малый мажорный терцквартаккорд").contains(it) || it.nearTo("мМаж43") },
                        Entity("Mmm2") { listOf("малый мажорный секундаккорд").contains(it) || it.nearTo("мМаж2") },

                        Entity("mMM7") { listOf("большой минорный септаккорд").contains(it) || it.nearTo("бМин7") },
                        Entity("mMM65") { listOf("большой минорный квинтсекстаккорд").contains(it) || it.nearTo("бМин65") },
                        Entity("mMM43") { listOf("большой минорный терцквартаккорд").contains(it) || it.nearTo("бМин43") },
                        Entity("mMM2") { listOf("большой минорный секундаккорд").contains(it) || it.nearTo("бМин2") },

                        Entity("mMm7") { listOf("малый минорный септаккорд").contains(it) || it.nearTo("мМин7") },
                        Entity("mMm65") { listOf("малый минорный квинтсекстаккорд").contains(it) || it.nearTo("мМин65") },
                        Entity("mMm43") { listOf("малый минорный терцквартаккорд").contains(it) || it.nearTo("мМин43") },
                        Entity("mMm2") { listOf("малый минорный секундаккорд").contains(it) || it.nearTo("мМин2") },

                        Entity("mmM7") { listOf("малый септаккорд с уменьшенной квинтой", "малый уменьшенный септаккорд").contains(it) || it.nearTo("мУм7") },
                        Entity("mmM65") { listOf("малый квинтсекстаккорд с уменьшенной квинтой", "малый уменьшенный квинтсекстаккорд").contains(it) || it.nearTo("мУм65") },
                        Entity("mmM43") { listOf("малый терцквартаккорд с уменьшенной квинтой", "малый уменьшенный терцквартаккорд").contains(it) || it.nearTo("мУм43") },
                        Entity("mmM2") { listOf("малый секундаккорд с уменьшенной квинтой", "малый уменьшенный секундаккорд").contains(it) || it.nearTo("мУм2") },

                        Entity("mmm7") { listOf("уменьшенный септаккорд").contains(it) || it.nearTo("ум7") }
                )
        )
    }
}

