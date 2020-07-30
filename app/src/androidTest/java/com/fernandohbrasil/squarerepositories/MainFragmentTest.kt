package com.fernandohbrasil.squarerepositories

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.fernandohbrasil.squarerepositories.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class MainFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun successOnRepositoriesSearch() {
        //I Know this is not the best idea. I saw that espresso have something sor this
        //But I Still couldn't set this to work
        Thread.sleep(5000)

        Espresso.onView(withId(R.id.rvRepositories))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun exceptionOnRepositoriesSearch() {
//        I tried to create a test that emulate a connection fail, but I couldn't
//
//        Espresso.onView(withId(R.id.vwError))
//            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}