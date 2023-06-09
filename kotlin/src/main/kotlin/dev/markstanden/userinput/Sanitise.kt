package dev.markstanden.userinput

val VALID_SPECIAL_CHARS = setOf<Char>('/', '-')

/**
 * returns a string stripped of all characters other than alphanumeric and '-' or '/'
 */
fun hardSanitise(dirty: String, maxLength: Int = 20): String {
    return dirty.maxLength(maxLength).restrictToAlphasAnd(VALID_SPECIAL_CHARS)
}

