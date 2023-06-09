package dev.markstanden.userinput

/**
 * returns a string stripped of all characters other than alphanumeric and '-' or '/'
 */
fun hardSanitise(dirty: String, maxLength: Int = 20): String {
    return dirty.substring(0, minOf(maxLength, dirty.length))
        .filter { c: Char -> (c.isLetterOrDigit()) or (c == '-') or (c == '/') }
}