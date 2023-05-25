fun main() {

//    Задача 1

    println(paymentComission("MasterCard", 75000, 6000))
}

fun paymentComission(typeOfCard: String, lastTransaction: Int, transaction: Int): Int {
    val maxDayCardLimit = 150000
    val maxMonthCardLimit = 600000
    val totalLimit = 75000
    var comission = 0
    val maxVkpayLimit = 15000
    val maxMonthVkpayLimit = 40000

    when (typeOfCard) {
        "MasterCard", "Maestro", "Visa", "Мир" -> {
            if (transaction > maxDayCardLimit) println("Превышен дневной лимит переводов")
            if (lastTransaction + transaction > maxMonthCardLimit) println("Превышен месячный лимит переводов")

            when (typeOfCard) {
                "MasterCard", "Maestro" -> {
                    val sumForComission =
                        if (lastTransaction + transaction > totalLimit) lastTransaction + transaction - totalLimit else 0
                    comission = if (sumForComission > 0) (sumForComission * 0.006 + 20).toInt() else 0
                }

                "Visa", "Мир" -> {
                    comission = if ((transaction * 0.0075).toInt() > 35) (transaction * 0.0075).toInt() else 35
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
