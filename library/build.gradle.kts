@file:Suppress("MISSING_DEPENDENCY_CLASS_IN_LAMBDA_RECEIVER", "MISSING_DEPENDENCY_CLASS_IN_EXPRESSION_TYPE")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.vanniktech.mavenPublish)
    kotlin("plugin.serialization") version "2.3.20"
    id("maven-publish")
}

group = "dev.robaldo"
version = "0.0.3"
val artifactId = "viaggiatreno"

kotlin {
    jvm() {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    androidLibrary {
        namespace = "dev.robaldo.viaggiatreno"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withJava() // enable java compilation support
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(
                    JvmTarget.JVM_11
                )
            }
        }
    }
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()
//    linuxX64()
//    linuxArm64()
//    macosX64()
//    macosArm64()

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
            implementation("io.ktor:ktor-client-core:3.4.2")
            implementation("com.fleeksoft.ksoup:ksoup:0.2.6")
            implementation("com.fleeksoft.ksoup:ksoup-network:0.2.6")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.8.0")
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
        }

        jvmTest.dependencies {
            implementation(kotlin("test-junit5"))
            implementation("org.junit.jupiter:junit-jupiter:5.10.0")
        }
    }
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates(group.toString(), artifactId, version.toString())

    pom {
        name = "Trenitalia ViaggiaTreno API"
        description = "A library."
        inceptionYear = "2026"
        url = "https://github.com/c4lopsitta/trenitalia-viaggiatreno-api"
        licenses {
            license {
                name = "GNU Affero General Public License Version 3"
                url = "https://www.gnu.org/licenses/agpl-3.0.html"
                distribution = "https://www.gnu.org/licenses/agpl-3.0.html"
            }
        }
        developers {
            developer {
                id = "c4lopsitta"
                name = "Simone Robaldo"
                url = "https://github.com/c4lopsitta"
            }
        }
        scm {
            url = "https://github.com/c4lopsitta/trenitalia-viaggiatreno-api"
            connection = "scm:git:https://github.com/c4lopsitta/trenitalia-viaggiatreno-api.git"
            developerConnection = "scm:git:ssh://github.com/c4lopsitta/trenitalia-viaggiatreno-api.git"
        }
    }
}
