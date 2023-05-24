
import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test
    fun sumForComission() {
        val lastTransaction = 74000
        val transaction = 2000
        val totalLimit = 75000

        val result = if (lastTransaction + transaction > totalLimit) lastTransaction + transaction - totalLimit else 0
        assertEquals(1000, result)
    }

    @Test
    fun maestroMastercardComission() {
        val sumForComission = 20000
        val comission = if (sumForComission > 0) (sumForComission * 0.06 + 20).toInt() else 0
        assertEquals(1220, comission)
    }

    @Test
    fun visaMirComission() {
        val transaction = 1000
        val comission = if ((transaction * 0.075).toInt() > 35) (transaction * 0.075).toInt() else 35
        assertEquals(75, comission)
    }

    @Test
    fun vkPayComission() {
        val transaction = 16000
        val maxVkpayLimit = 15000

        val result = if (transaction > maxVkpayLimit) "Превышен дневной лимит переводов" else "Операция проведена"
        assertEquals("Превышен дневной лимит переводов", result)
    }

    @Test
    fun mastercartPaymentLimit() {
        val transaction = 16000
        val maxDayCardLimit = 150000
        val lastTransaction = 20000
        val maxMonthCardLimit = 600000
        val resultDayLimit = if (transaction > maxDayCardLimit) "Превышен дневной лимит переводов" else "Операция проведена"
        val resultMonthLimit = if (lastTransaction + transaction > maxMonthCardLimit) "Превышен месячный лимит переводов" else "Операция проведена"
        assertEquals("Операция проведена", resultDayLimit)
        assertEquals("Операция проведена", resultMonthLimit)
    }
}