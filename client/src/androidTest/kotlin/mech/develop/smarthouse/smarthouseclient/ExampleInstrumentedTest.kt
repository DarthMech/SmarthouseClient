package mech.develop.smarthouse.smarthouseclient

import android.content.Context
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import mech.develop.smarthouse.smarthouseclient.mainscreen.presentation.activity.SmarthouseActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityRule: ActivityTestRule<SmarthouseActivity> = ActivityTestRule(
            SmarthouseActivity::class.java,
            true,
            false
    )

    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start(8080)

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                when (request.path) {
                    "/light/bedroomState", "/light/libraryState" -> return MockResponse().setResponseCode(200).setBody("true")
                    else -> return MockResponse().setResponseCode(200)
                }
            }
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun openFiltersTest() {
        activityRule.launchActivity(Intent())
        Thread.sleep(1000)

        onView(withId(R.id.switch_bedroom)).check(matches(isChecked()))
        onView(withId(R.id.switch_library)).check(matches(isChecked()))
        onView(withId(R.id.red_led_value_textView)).check(matches(withText("0")))

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                when (request.path) {
                    "/light/bedroomState", "/light/libraryState" -> return MockResponse().setResponseCode(200).setBody("false")
                    else -> return MockResponse().setResponseCode(200)
                }
            }
        }

        onView(withId(R.id.kill_all_button)).perform(ViewActions.click())

        onView(withId(R.id.switch_bedroom)).check(matches(isNotChecked()))
        onView(withId(R.id.switch_library)).check(matches(isNotChecked()))
        onView(withId(R.id.red_led_value_textView)).check(matches(withText("0")))
    }

}