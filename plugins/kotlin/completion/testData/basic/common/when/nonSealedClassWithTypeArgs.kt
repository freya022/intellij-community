// FIR_IDENTICAL
// FIR_COMPARISON
// IGNORE_K1
open class NON_SEALED
class AAAA<E, S>: NON_SEALED()
object BBBB: NON_SEALED()
class CCCC<E>: NON_SEALED()

fun foo(e: NON_SEALED) {
    when (e) {
        <caret>
    }
}

// EXIST: { lookupString: "is AAAA", tailText: "<*, *> -> " }
// EXIST: BBBB
// EXIST: { lookupString: "is CCCC", tailText: "<*> -> " }
// EXIST: NON_SEALED
// EXIST: { lookupString: "else -> "}
