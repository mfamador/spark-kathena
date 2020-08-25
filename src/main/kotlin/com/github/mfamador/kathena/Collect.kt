package com.github.mfamador.kathena

import org.apache.spark.sql.Row
import org.jetbrains.kotlinx.spark.api.*

fun main() {
    withSpark {
        val sd = dsOf(1, 2, 3)
        sd.createOrReplaceTempView("ds")
        spark.sql("select * from ds")
                .withCached {
                    println("asList: ${toList<Int>()}")
                    println("asArray: ${toArray<Int>().contentToString()}")
                    this
                }
                .to<Int>()
                .withCached {
                    println("typed collect: " + (collect() as Array<Int>).contentToString())
                    println("type collectAsList: " + collectAsList())
                }

        dsOf(1, 2, 3)
                .map { c(it, it + 1, it + 2) }
                .to<Row>()
                .select("_1")
                .collectAsList()
                .forEach { println(it) }
    }
}
