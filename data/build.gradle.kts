import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvmToolchain(17)

    compilerOptions {
        apiVersion.set(KotlinVersion.KOTLIN_2_2)
        freeCompilerArgs = freeCompilerArgs.get() + "-Xcontext-parameters"
    }
    /*
    sourceSets.all {
        languageSettings.enableLanguageFeature("ExplicitBackingFields")
    }*/
    explicitApi()
}

dependencies {
    api(libs.k3dm)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
}
