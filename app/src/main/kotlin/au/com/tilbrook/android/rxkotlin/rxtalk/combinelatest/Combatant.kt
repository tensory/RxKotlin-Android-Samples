package au.com.tilbrook.android.rxkotlin.rxtalk.combinelatest

import rx.Observable

class Combatant(val house: House,
                var debt: Double,
                var armySize: Int,
                var dragonCount: Int) {

    enum class House {
        BARATHEON,
        TARGARYEN,
        LANNISTER,
        STARK
    }

    fun getCreditScore(): Observable<Int> {
        return Observable.combineLatest(
                Observable.just(debt),
                Observable.just(armySize),
                Observable.just(dragonCount)
        ) { debt, armySize, dragons ->
            (dragons * 1000) + ((armySize / debt) * 100).toInt()
        }
    }

}