package com.escodro.alkaa.framework

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import java.util.Calendar
import org.hamcrest.Matchers.not

/**
 * Handles all the test matchers.
 */
@Suppress("UndocumentedPublicFunction")
class Checkers {

    fun viewIsCompletelyDisplayed(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isCompletelyDisplayed()))
    }

    fun viewIsNotDisplayed(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(not(isCompletelyDisplayed())))
    }

    fun viewHasText(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).check(matches(withText(text)))
    }

    fun viewHasText(@IdRes viewId: Int, @StringRes stringRes: Int) {
        onView(withId(viewId)).check(matches(withText(stringRes)))
    }

    fun viewHasDate(@IdRes viewId: Int, calendar: Calendar) {
        onView(withId(viewId)).check(matches(Matchers.compareDates(calendar)))
    }

    fun listContainsItem(@IdRes viewId: Int, itemName: String) {
        onView(withId(viewId)).check(matches(hasDescendant(withText(itemName))))
    }

    fun listNotContainsItem(@IdRes viewId: Int, itemName: String) {
        onView(withId(viewId)).check(matches(not(hasDescendant(withText(itemName)))))
    }

    fun listContainsHint(@IdRes viewId: Int, @StringRes stringRes: Int) {
        onView(withId(viewId)).check(matches(hasDescendant(withHint(stringRes))))
    }

    fun listNotContainsHint(@IdRes viewId: Int, @StringRes stringRes: Int) {
        onView(withId(viewId)).check(matches(not(hasDescendant(withText(stringRes)))))
    }

    fun textHasFixedLines(@IdRes viewId: Int, numberOfLines: Int) {
        onView(withId(viewId))
            .check(matches(Matchers.isTextInLines(numberOfLines)))
    }

    fun viewIsChecked(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isChecked()))
    }

    fun viewIsChecked(@IdRes viewGroupId: Int, index: Int) {
        onView(Matchers.getChildAt(withId(viewGroupId), index)).check(matches(isChecked()))
    }

    fun viewIsNotChecked(@IdRes viewGroupId: Int, index: Int) {
        onView(Matchers.getChildAt(withId(viewGroupId), index)).check(matches(not(isChecked())))
    }

    fun drawerIsOpen(@IdRes drawerId: Int) {
        onView(withId(drawerId)).check(matches(DrawerMatchers.isOpen()))
    }

    fun drawerIsClosed(@IdRes drawerId: Int) {
        onView(withId(drawerId)).check(matches(DrawerMatchers.isClosed()))
    }

    fun viewHasBackgroundColor(@IdRes viewId: Int, color: Int) {
        onView(withId(viewId)).check(matches(Matchers.hasBackgroundColor(color)))
    }

    fun viewHasFocus(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(Matchers.hasFocus()))
    }

    fun snackbarIsVisible() {
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(isCompletelyDisplayed()))
    }
}
