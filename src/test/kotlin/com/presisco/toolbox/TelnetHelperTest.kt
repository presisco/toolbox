import com.presisco.toolbox.net.TelnetHelper
import org.junit.After
import org.junit.Test

class TelnetHelperTest {
    private val telnetHelper = TelnetHelper("192.168.1.82")

    @Test
    fun login() {
        telnetHelper.login("buildbot", "plain")
    }

    @After
    fun close() {
        telnetHelper.close()
    }
}