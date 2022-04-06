package com.andrmobiledev.lint_rules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import org.jetbrains.uast.UImportStatement

class FilmsNamingPatternDetector : Detector(), Detector.UastScanner {
    override fun getApplicableUastTypes() = listOf(UImportStatement::class.java)

    override fun createUastHandler(context: JavaContext) = FilmsInvalidImportHandler(context)
}

class FilmsInvalidImportHandler(private val context: JavaContext) : UElementHandler() {
    override fun visitImportStatement(node: UImportStatement) {
        node.importReference?.let { importReference ->
            if (importReference.asSourceString().contains("com.andrmobiledev.")) {
                context.report(IssueFilmsImport, node, context.getLocation(importReference), "Forbidden import")
            }
        }
    }
}