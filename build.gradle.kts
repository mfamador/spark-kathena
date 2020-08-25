import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
}
group = "com.github.mfamador"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotlinSpark = "1.0.0-preview1"
val sparkVersion = "3.0.0"
dependencies {
    implementation("org.jetbrains.kotlinx.spark:kotlin-spark-api-parent:$kotlinSpark")
    implementation("org.jetbrains.kotlinx.spark:kotlin-spark-api-3.0.0_2.12:$kotlinSpark")
    implementation("org.apache.spark:spark-sql_2.12:$sparkVersion")


    testImplementation(kotlin("test-junit5"))
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
