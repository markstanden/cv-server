package dev.markstanden.userinput

/**
 * Returns a string shortened to the maxLength, returns the original string if
 * the supplied string is shorter than maxLength.
 */
fun String.maxLength(maxLength: Int): String {
    return this.substring(0, minOf(maxLength, this.length))
}