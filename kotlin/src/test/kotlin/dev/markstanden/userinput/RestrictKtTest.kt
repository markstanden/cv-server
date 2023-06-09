package dev.markstanden.userinput

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class RestrictKtTest {

    @Test
    fun `test with valid alphanumeric input string`() {
        val test = "abcABC0123789"
        val validChars = emptySet<Char>()
        assertTrue(test.restrictToAlphasAnd(validChars) == test)
    }
    @Test
    fun `test with invalid characters`() {
        val test = "-abcABC0123789-"
        val validChars = emptySet<Char>()
        assertFalse(test.restrictToAlphasAnd(validChars) == test)
    }

    @Test
    fun `test with valid characters in added set`() {
        val test = "-abcABC0123789-"
        val validChars = setOf('-')
        assertTrue(test.restrictToAlphasAnd(validChars) == test)
    }
}