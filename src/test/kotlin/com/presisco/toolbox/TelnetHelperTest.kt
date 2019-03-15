import com.presisco.toolbox.net.TelnetHelper
import org.junit.Test

class TelnetHelperTest {
    @Test
    fun readAIXFreeSpace() {
        val telnetHelper = TelnetHelper("remote", 23)
        telnetHelper.send("user", "plain")
        telnetHelper.waitCmdInterval()
        telnetHelper.clearBuffer()
        telnetHelper.send("df -g /dev/archlv")
        telnetHelper.waitCmdInterval()
        println(telnetHelper.readRecording())
    }

}