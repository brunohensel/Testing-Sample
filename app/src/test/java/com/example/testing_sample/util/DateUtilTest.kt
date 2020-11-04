package com.example.testing_sample.util

import com.example.testing_sample.util.DateUtil.GET_MONTH_ERROR
import com.example.testing_sample.util.DateUtil.getCurrentTimeStamp
import com.example.testing_sample.util.DateUtil.getMonthFromNumber
import com.example.testing_sample.util.DateUtil.monthNumbers
import com.example.testing_sample.util.DateUtil.months
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.random.Random

class DateUtilTest {
    private val today = "11-2020"

    @Test
    fun `get current timestamp`() {
        assertDoesNotThrow {
            assertEquals(today, getCurrentTimeStamp())
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    fun `getMonthFromNumber return success`(monthNumber: Int) {
        assertEquals(months[monthNumber], getMonthFromNumber(monthNumbers[monthNumber]))
        println(monthNumbers[monthNumber] + " : " + months[monthNumber])
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    fun `getMonthFromNumber return error`(monthNumber: Int) {
        val randomInt = Random.nextInt(90) + 15
        assertEquals(getMonthFromNumber((monthNumber * randomInt).toString()), GET_MONTH_ERROR)
        println(monthNumbers[monthNumber] + " : " + GET_MONTH_ERROR)
    }
}