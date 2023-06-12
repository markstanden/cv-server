package dev.markstanden.routes

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.function.Executable

fun String.basePageTests() {
    Assertions.assertAll(
        Executable { assertTrue(this.contains("""<html lang="en">"""), "Returned page should contain an opening html tag")},
        Executable { assertTrue(this.contains("</html>"), "Returned page should contain a closing html tag")},
        Executable { assertTrue(this.contains("<head"), "Returned page should contain an opening head tag")},
        Executable { assertTrue(this.contains("</head>"), "Returned page should contain a closing head tag")},
        Executable { assertTrue(this.contains("<header"), "Returned page should contain an opening header tag")},
        Executable { assertTrue(this.contains("</header>"), "Returned page should contain a closing header tag")},
        Executable { assertTrue(this.contains("<main"), "Returned page should contain an opening main tag")},
        Executable { assertTrue(this.contains("</main>"), "Returned page should contain a closing main tag")},
    )
}