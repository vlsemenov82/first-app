
import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test
    fun MastercardComissionLimit() {
        val result = paymentComission("MasterCard", 74000, 2000)
        assertEquals(26, result)
    }
    @Test
    fun MastercardComissionDayLimit() {
        val result = paymentComission("MasterCard", 0, 160000)
        assertEquals(530, result)
    }

    @Test
    fun MastercardComissionMonthLimit() {
        val result = paymentComission("MasterCard", 500000, 101000)
        assertEquals(3176, result)
    }

    @Test
    fun MastercardComissionNoLimit() {
        val result = paymentComission("MasterCard", 50000, 20000)
        assertEquals(0, result)
    }

    @Test
    fun MaestroComissionLimit() {
        val result = paymentComission("Maestro", 74000, 2000)
        assertEquals(26, result)
    }
    @Test
    fun MaestroComissionDayLimit() {
        val result = paymentComission("Maestro", 0, 160000)
        assertEquals(530, result)
    }

    @Test
    fun MaestroComissionMonthLimit() {
        val result = paymentComission("Maestro", 500000, 101000)
        assertEquals(3176, result)
    }

    @Test
    fun MaestroComissionNoLimit() {
        val result = paymentComission("Maestro", 50000, 20000)
        assertEquals(0, result)
    }


    @Test
    fun visaComissionLimit() {
        val result = paymentComission("Visa", 0, 2000)
        assertEquals(35, result)
    }

    @Test
    fun visaComissionNoLimit() {
        val result = paymentComission("Visa", 0, 10000)
        assertEquals(75, result)
    }

    @Test
    fun mirComissionLimit() {
        val result = paymentComission("Мир", 0, 2000)
        assertEquals(35, result)
    }

    @Test
    fun mirComissionNoLimit() {
        val result = paymentComission("Мир", 0, 10000)
        assertEquals(75, result)
    }

    @Test
    fun vkPayComissionDayLimit() {

        val result = paymentComission("Vkpay", 0, 16000)
        assertEquals(0, result)
    }

    @Test
    fun vkPayComissionNoDayLimit() {

        val result = paymentComission("Vkpay", 0, 10000)
        assertEquals(0, result)
    }

    @Test
    fun vkPayComissionMonthLimit() {

        val result = paymentComission("Vkpay", 0, 45000)
        assertEquals(0, result)
    }

    @Test
    fun vkPayComissionNoMonthLimit() {

        val result = paymentComission("Vkpay", 0, 35000)
        assertEquals(0, result)
    }
}