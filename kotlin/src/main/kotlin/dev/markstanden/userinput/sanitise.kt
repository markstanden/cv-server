package dev.markstanden.userinput

val VALID_SPECIAL_CHARS = setOf<Char>('/', '-')

/**
 * returns a string stripped of all characters other than alphanumeric and '-' or '/'
 */
fun hardSanitise(dirty: String, maxLength: Int = 20): String {
    return dirty.maxLength(maxLength).restrictToAlphasAnd(VALID_SPECIAL_CHARS)
}

/**
 * Returns a string shortened to the maxLength, returns the original string if
 * the supplied string is shorter than maxLength.
 */
fun String.maxLength(maxLength: Int): String {
    return this.substring(0, minOf(maxLength, this.length))
}

/**
 * Returns a string stripped of characters that are not alphanumeric
 * or within the supplied set of Chars.
 */
fun String.restrictToAlphasAnd(allowed: Set<Char>): String {
    return this.filter { c: Char -> c.isLetterOrDigit() or (c in allowed) }
}