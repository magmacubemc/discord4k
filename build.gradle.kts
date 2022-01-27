import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
    id ("com.github.johnrengelman.shadow") version "7.0.0"
    kotlin("plugin.serialization") version "1.6.10"
}

group = "ru.magmacube"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://m2.dv8tion.net/releases")
    maven("https://jitpack.io/")
}

val jdaVer = "5.0.0-alpha.4"
val lavaPlayerVer = "1.3.77"
dependencies {
    implementation("com.charleskorn.kaml:kaml:0.40.0")
    implementation("net.dv8tion:JDA:$jdaVer")
    implementation("com.sedmelluq:lavaplayer:$lavaPlayerVer")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("com.github.MinnDevelopment:jda-reactor:1.5.0")
    implementation("com.github.minndevelopment:jda-ktx:d3c6b4d")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}