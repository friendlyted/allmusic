package pro.friendlyted.service

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import pro.friendlyted.api.SupplierService
import javax.inject.Inject

@Configuration
@SpringBootApplication(
    scanBasePackages = ["pro.friendlyted.service"]
)
open class SupplierServiceImplTestConfig

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [SupplierServiceImplTestConfig::class])
class SupplierServiceImplTest {

    @Inject
    private val supplierService: SupplierService? = null

    @Test
    fun interval() {
        supplierService!!.interval()
    }

    @Test
    fun triad() {
        supplierService!!.triad()
    }

    @Test
    fun intervalLoadTest() {
        (1 until 1000000).forEach { interval() }
    }

    @Test
    fun triadLoadTest() {
        (1 until 1000000).forEach { triad() }
    }
}