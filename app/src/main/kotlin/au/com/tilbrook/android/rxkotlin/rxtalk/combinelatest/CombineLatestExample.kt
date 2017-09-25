package au.com.tilbrook.android.rxkotlin.rxtalk.combinelatest

import rx.Observer
import timber.log.Timber

class CombineLatestExample {

    fun demoDebtVariable() {
        val combatant = Combatant(Combatant.House.BARATHEON,
                (900).toDouble(), 50, 0)

        combatant.getCreditScore()
                .subscribe(CreditObserver(combatant))
    }

    class CreditObserver(val combatant: Combatant): Observer<Int> {
        override fun onError(e: Throwable?) {
            Timber.e("Credit score error", e)
        }

        override fun onCompleted() {
            TODO("not implemented")
        }

        override fun onNext(result: Int?) {
            Timber.d("Credit score for ${combatant.house.name}: $result")
        }

    }
}
