package dev.markstanden.userinput

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class RemoveTrailingKtTest {

    @Test
    fun `test without the default trailing character present`() {
        val input = "-abcABC0123789-"
        val expected = "-abcABC0123789-"

        val output = input.removeTrailing()

        assertEquals(output, expected)
    }
    @Test
    fun `test with the default trailing character present`() {
        val input = "-abcABC0123789-/"
        val expected = "-abcABC0123789-"

        val output = input.removeTrailing()

        assertEquals(output, expected)
    }
    @Test
    fun `test without the trailing character present`() {
        val input = "-abcABC0123789-"
        val trailingChar = '#'
        val expected = "-abcABC0123789-"

        val output = input.removeTrailing(trailingChar)

        assertEquals(output, expected)
    }

    @Test
    fun `test with the trailing character present`() {
        val input = "-abcABC0123789-#"
        val trailingChar = '#'
        val expected = "-abcABC0123789-"

        val output = input.removeTrailing(trailingChar)

        assertEquals(output, expected)
    }

    @Test
    fun `test with trailing character within the string, but not trailing`() {
        val input = "-abc?ABC?0123789-"
        val trailingChar = '?'
        val expected = "-abc?ABC?0123789-"

        val output = input.removeTrailing(trailingChar)

        assertEquals(output, expected)
    }

    @Test
    fun `test with trailing character within the string, and also trailing`() {
        val input = "-abc?ABC?0123789-?"
        val trailingChar = '?'
        val expected = "-abc?ABC?0123789-"

        val output = input.removeTrailing(trailingChar)

        assertEquals(output, expected)
    }
}