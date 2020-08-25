package com.github.mfamador.kathena

import org.apache.spark.sql.Dataset
import org.jetbrains.kotlinx.spark.api.*

const val MEANINGFUL_WORD_LENGTH = 4

fun main() {
    withSpark {
        spark
                .read()
                .textFile(this::class.java.classLoader.getResource("the-catcher-in-the-rye.txt")?.path)
                .map { it.split(Regex("\\s")) }
                .flatten()
                .cleanup()
                .groupByKey { it }
                .mapGroups { k, iter -> k to iter.asSequence().count() }
                .sort { arrayOf(it.col("second").desc()) }
                .limit(20)
                .map { it.second to it.first }
                .show(false)
    }
}

fun Dataset<String>.cleanup() =
        filter { it.isNotBlank() }
                .map { it.trim(',', ' ', '\n', ':', '.', ';', '?', '!', '"', '\'', '\t', '　') }
                .filter { !it.endsWith("n’t") }
                .filter { it.length >= MEANINGFUL_WORD_LENGTH }
