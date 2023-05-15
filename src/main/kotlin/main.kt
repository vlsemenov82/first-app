fun main() {

//    Задача 1
    val transaction = 12300
    val minComission = 35
    val comission = (transaction * 0.0075).toInt()
    val amount = if (comission > minComission) comission else minComission
    println(amount)

//    Задача 2 (Логика такая, если 1, 21, 31... 91, то человеку, всем остальным людям, 11, 111, 1111 и т.п. тоже людям)
    val likes = 1121
    val result = if (likes % 10 == 1 && likes % 100 !== 11) "человеку" else "людям"
    println("Понравилось $likes $result")

//    Задача 3
    val regularCustomer = true
    val sum = 10001
    val discount = if (sum > 10000) (sum * 0.05).toInt() else if (sum > 1000) 100 else 0
    println(discount)
    val CustomerDiscount = if (regularCustomer) ((sum - discount) * 0.01 + discount).toInt() else discount
    println("Скидка составила $CustomerDiscount рублей")


}