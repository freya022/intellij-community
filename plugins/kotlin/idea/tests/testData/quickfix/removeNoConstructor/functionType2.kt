// "Remove constructor call" "true"

abstract class B : (() -> Int)()<caret>
// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.RemoveNoConstructorFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.RemoveNoConstructorFix