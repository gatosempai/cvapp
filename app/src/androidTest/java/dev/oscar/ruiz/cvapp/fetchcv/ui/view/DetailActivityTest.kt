package dev.oscar.ruiz.cvapp.fetchcv.ui.view


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import dev.oscar.ruiz.cvapp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(DetailActivity::class.java)

    @Test
    fun detailActivityTest() {
        Thread.sleep(2000)
        val cardView = onView(
            allOf(
                withId(R.id.cv_root),
                childAtPosition(
                    allOf(
                        withId(R.id.rv_landing),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView.perform(click())

        Thread.sleep(500)
        val imageView = onView(
            allOf(
                withContentDescription("Avatar image"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.tv_detail_name), withText("Oscar Ruiz Palma"),
                childAtPosition(
                    allOf(
                        withId(R.id.ll_detail_title),
                        childAtPosition(
                            withId(R.id.fl_detail_title),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.tv_detail_job), withText("Software Engineer Sr"),
                childAtPosition(
                    allOf(
                        withId(R.id.ll_detail_title),
                        childAtPosition(
                            withId(R.id.fl_detail_title),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(
                withId(R.id.iv_detail_holder), withContentDescription("Image Holder"),
                childAtPosition(
                    allOf(
                        withId(R.id.ctl_detail),
                        childAtPosition(
                            withId(R.id.apl_detail),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val frameLayout = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isDisplayed()))

        val frameLayout2 = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        frameLayout2.check(matches(isDisplayed()))
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
