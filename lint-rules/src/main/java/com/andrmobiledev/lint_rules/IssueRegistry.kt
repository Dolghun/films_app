package com.andrmobiledev.lint_rules

import com.android.tools.lint.detector.api.Category.Companion.CORRECTNESS
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import java.util.*

class IssueRegistry : com.android.tools.lint.client.api.IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(IssueFilmsImport)

    override val api: Int = com.android.tools.lint.detector.api.CURRENT_API
}

val IssueFilmsImport = Issue.create("FilmsImport",
    "Films is deprecated",
    "Test films",
    CORRECTNESS,
    5,
    Severity.WARNING,
    Implementation(FilmsNamingPatternDetector::class.java,
        EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES))
)