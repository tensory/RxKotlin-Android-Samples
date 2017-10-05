package au.com.tilbrook.android.rxkotlin.rxtalk

import au.com.tilbrook.android.rxkotlin.rxtalk.combinelatest.Combatant
import rx.Observable
import java.util.*
import java.util.concurrent.TimeUnit

class BattleProvider {
    companion object {
        const val INTERVAL: Int = 1;
    }

    private val houseRandom = Random(Combatant.House.values().size.toLong())
    private val debtRandom = Random(1000000)
    private val armySizeRandom = Random(1000000)
    private val dragonsRandom = Random(4)

    inner class Battle(val combatants: Set<Combatant>)

    fun getBattles(): Observable<Battle> {
        return Observable
                .interval(INTERVAL.toLong(), TimeUnit.SECONDS)
                .timeInterval()
                .flatMap { it -> Observable.just(Battle(getRandomCombatants())) }
    }

    private fun getRandomCombatants(): Set<Combatant> {
        val firstIndex = houseRandom.nextInt()
        var secondIndex: Int
        do {
            secondIndex = houseRandom.nextInt()
        } while (secondIndex == firstIndex)
        
        return listOf(firstIndex, secondIndex).map { enumIndex: Int ->
            Combatant(Combatant.House.values().get(enumIndex),
                    debtRandom.nextDouble(), 
                      armySizeRandom.nextInt(), 
                      dragonsRandom.nextInt())
        }
        .toSet()
    }
}
