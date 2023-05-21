fun main() {

// Задача 1

    fun calculateMinute(time: Int): String {
        val result = when {
            time / 60 % 10 == 1 && time / 60 % 100 !== 11 -> "минуту"
            time / 60 % 10 == 2 || time / 60 % 10 == 3 || time / 60 % 10 == 4 -> "минуты"
            else -> "минут"
        }
        return result
    }

    fun calculateHour(time: Int): String {
        val result = when {
            time / 3600 % 10 == 1 && time / 3600 % 100 !== 11 -> "час"
            time / 3600 % 10 == 2 || time / 3600 % 10 == 3 || time / 3600 % 10 == 4 -> "часа"
            else -> "часов"
        }
        return result
    }

    fun agoToText(time: Int) {
        when (time) {
            in 0..60 -> println("Только что")
            in 61..60 * 60 -> println("${(time / 60).toInt()} ${calculateMinute(time)} назад")
            in 60 * 60 + 1..24 * 60 * 60 -> println("${(time / 60 / 60).toInt()} ${calculateHour(time)} назад")
            in 24 * 60 * 60 + 1..24 * 60 * 60 * 2 -> println("Вчера")
            in 24 * 60 * 60 * 2 + 1..24 * 60 * 60 * 3 -> println("Позавчера")
            else -> println("Давно")
        }
    }

    agoToText(60)
    agoToText(121)
    agoToText(60 * 60 + 1)
    agoToText(24 * 60 * 60 + 1)
    agoToText(24 * 60 * 60 * 2 + 1)

//    Задача 2

    val maxDayCardLimit = 150000
    val maxMonthCardLimit = 600000
    val maxVkpayLimit = 15000
    val maxMonthVkpayLimit = 40000
    val totalLimit = 75000
    var comission = 0

    fun paymentComission(typeOfCard: String, lastTransaction: Int, transaction: Int): Int {
        when (typeOfCard) {
            "MasterCard", "Maestro", "Visa", "Мир" -> {
                if (transaction > maxDayCardLimit) println("Превышен дневной лимит переводов")
                if (lastTransaction + transaction > maxMonthCardLimit) println("Превышен месячный лимит переводов")

                when (typeOfCard) {
                    "MasterCard", "Maestro" -> {
                        val sumForComission =
                            if (lastTransaction + transaction > totalLimit) lastTransaction + transaction - totalLimit else 0
                        comission = if (sumForComission > 0) (sumForComission * 0.06 + 20).toInt() else 0
                    }

                    "Visa", "Мир" -> {
                        comission = if ((transaction * 0.075).toInt() > 35) (transaction * 0.075).toInt() else 35
                    }
                }
            }

            "Vkpay" -> {
                if (transaction > maxVkpayLimit) println("Превышен дневной лимит переводов")
                if (lastTransaction + transaction > maxMonthVkpayLimit) println("Превышен месячный лимит переводов")
                comission = 0
            }
        }
        return comission
    }

    println(paymentComission("MasterCard", 75000, 6000))
}
