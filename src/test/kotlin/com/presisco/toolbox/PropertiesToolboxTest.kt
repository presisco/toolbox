import com.presisco.toolbox.PropertiesToolbox
import org.junit.Test
import java.util.*
import kotlin.test.expect

class PropertiesToolboxTest {
    private val samplePropString = "key1 = value1\nkey.set = value1, value2, value3\nkey3 = 123456\n"

    @Test
    fun validate() {
        val sampleProp = Properties()
        sampleProp.load(samplePropString.byteInputStream())
        val converted = PropertiesToolbox.props2Map(sampleProp)
        expect("value1", { converted["key1"] })
        expect("value1, value2, value3", { converted["key.set"] })
        expect("123456", { converted["key3"] })
    }
}