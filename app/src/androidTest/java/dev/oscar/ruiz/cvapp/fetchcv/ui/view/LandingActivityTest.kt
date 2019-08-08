package dev.oscar.ruiz.cvapp.fetchcv.ui.view


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import dev.oscar.ruiz.cvapp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class LandingActivityTest {

    @Rule
    var activityScenarioRule: ActivityScenarioRule<LandingActivity> = ActivityScenarioRule(LandingActivity::class.java)

    @Test
    fun landingActivityTest() {
        val textView = onView(
            allOf(
                withId(R.id.tv_landing_name_item), withText("Oscar Ruiz Palma"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cv_root),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.tv_landing_job_item), withText("Software Engineer Sr"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cv_root),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(R.id.tv_landing_location_item), withText("Mexico"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cv_root),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))

        val imageView = onView(
            allOf(
                withId(R.id.iv_landing_image_item),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cv_root),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val viewGroup = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.cv_root),
                        childAtPosition(
                            withId(R.id.rv_landing),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
