package dev.markstanden.userinput

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class RestrictKtTest {

    @Test
    fun `test with valid alphanumeric input string`() {
        val input = "abcABC0123789"
        val validChars = emptySet<Char>()

        val output = input.restrictToAlphasAnd(validChars)

        assertEquals(input, output)
    }

    @Test
    fun `test with invalid characters`() {
        val input = "-abcABC0123789-"
        val validChars = emptySet<Char>()

        val output = input.restrictToAlphasAnd(validChars)

        assertNotEquals(input, output)
    }

    @Test
    fun `test with valid characters in added set`() {
        val input = "-abcABC0123789-"
        val validChars = setOf('-')

        val output = input.restrictToAlphasAnd(validChars)

        assertEquals(input, output)
    }

    @Test
    fun `test with characters outside valid set`() {
        val input = "-abcABC:0123789-"
        val validChars = setOf('-')

        val output = input.restrictToAlphasAnd(validChars)

        assertNotEquals(input, output)
    }
}