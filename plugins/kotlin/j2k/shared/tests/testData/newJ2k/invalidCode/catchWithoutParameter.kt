// ERROR: A type annotation is required on a value parameter.
internal object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
        } catch (NO_NAME_PROVIDED) {
        }
    }
}
