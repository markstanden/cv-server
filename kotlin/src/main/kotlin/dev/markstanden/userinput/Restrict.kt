package dev.markstanden.userinput

/**
 * Returns a string stripped of characters that are not alphanumeric
 * or within the supplied set of Chars.
 */
fun String.restrictToAlphasAnd(allowed: Set<Char>): String {
    return this.filter { c: Char -> c.isLetterOrDigit() or (c in allowed) }
}