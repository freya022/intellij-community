// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.platform.ml.embeddings.utils

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class IndexingUtilsTest {
  @Test
  fun testIdentifiersSplitting() {
    val cases = listOf(
      "lowercase" to "lowercase",
      "Uppercase" to "Uppercase",
      "CamelCase" to "Camel Case",
      "snake_case" to "snake case",
      "Mixed_caseString" to "Mixed case String",
      "spaces in identifier" to "spaces in identifier",
      "HTML" to "HTML",
      "PDFLoader" to "PDF Loader",
      "AString" to "A String",
      "SimpleXMLParser" to "Simple XML Parser",
      "GL11Version" to "GL 11 Version",
      "русскоеСлово" to "русское Слово",
      "com.intellij.something" to "com intellij something",
      " with whitespace in front" to "with whitespace in front",
      "with whitespace in back " to "with whitespace in back",
      "multiple   spaces" to "multiple spaces",
      "path/with/multiple/foldersUnderEachOther" to "path with multiple folders Under Each Other",
      "register..." to "register",
    )

    for ((input, expected) in cases) {
      assertEquals(expected.lowercase(), splitIdentifierIntoTokens(input))
      assertEquals(expected, splitIdentifierIntoTokens(input, lowercase = false))
    }
  }
}