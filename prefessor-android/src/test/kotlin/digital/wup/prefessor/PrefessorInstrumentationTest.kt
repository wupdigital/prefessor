package digital.wup.prefessor

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import kotlin.test.BeforeTest

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class PrefessorInstrumentationTest : AbstractPrefessorTest() {

    @BeforeTest
    override fun setup() {
        ContextContentProvider.ctx = InstrumentationRegistry.getTargetContext()
        super.setup()
    }
}