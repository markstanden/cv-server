package dev.markstanden.userinput

/**
 * Returns a string shortened to the *length*, returns the original string if
 * the supplied string is shorter than *length*.
 */
fun String.truncate(length: Int): String {
    return this.substring(0, minOf(length, this.length))
}