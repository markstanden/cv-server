package dev.markstanden.userinput

val VALID_SPECIAL_CHARS = setOf<Char>('/', '-')

/**
 * returns a string stripped of all characters other than alphanumeric and '-' or '/'
 * Has a default restricted length of 20 characters.
 */
fun String?.sanitise(restrictedLength: Int = 20): String {
    if (this == null) return ""
    return this.truncate(restrictedLength).restrictToAlphasAnd(VALID_SPECIAL_CHARS)
}

