package dev.markstanden.userinput

/**
 * Removes the trailing supplied character from a String if present,
 * otherwise returns the original unaltered.
 */
fun String.removeTrailing(character: Char = '/'): String {
    if (this.last() == character) {
        return this.substring(0, lastIndex) //substring return ends before the lastIndex
    }
    else return this
}