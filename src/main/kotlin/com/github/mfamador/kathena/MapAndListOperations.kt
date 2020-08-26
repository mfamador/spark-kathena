package com.github.mfamador.kathena

import org.jetbrains.kotlinx.spark.api.*

fun main() {
    withSpark(props = mapOf("spark.sql.codegen.wholeStage" to true)) {
        dsOf(mapOf(1 to c(1, 2, 3), 2 to c(1, 2, 3)), mapOf(3 to c(1, 2, 3), 4 to c(1, 2, 3)))
            .flatMap { it.toList().map { p -> listOf(p.first, p.second._1, p.second._2, p.second._3) }.iterator() }
            .flatten()
            .map { c(it) }
            .also { it.printSchema() }
            .distinct()
            .sort("_1")
            .debugCodegen()
            .show()
    }
}

