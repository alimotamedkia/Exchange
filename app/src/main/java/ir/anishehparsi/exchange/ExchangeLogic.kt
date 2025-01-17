package ir.anishehparsi.exchange

class ExchangeLogic {

    private val usd = 80000

    fun exchangeCalculate(amount: Double): Double {
        return try {
            amount / usd
        } catch (e: ArithmeticException) {
            -1.0
        }
    }

    fun exchangeReverse(amount: Double):Double{
        return try {
            amount * usd
        }catch (e:ArithmeticException){
            -1.0
        }

    }

}