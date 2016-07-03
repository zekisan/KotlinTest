package com.example.ezequiel.kotlintest

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.Espresso.onView

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    public val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkTest() {
        onView(withId(R.id.textView2)).check(matches(withText("Zeki")))
    }
}