package dev.markstanden.userinput

val VALID_SPECIAL_CHARS = setOf<Char>('/', '-')

/**
 * returns a string stripped of all characters other than alphanumeric and '-' or '/'
 * Has a default restricted length of 20 characters.
 */
fun hardSanitise(userInput: String, restrictedLength: Int = 20): String {
    return userInput.maxLength(restrictedLength).restrictToAlphasAnd(VALID_SPECIAL_CHARS)
}

