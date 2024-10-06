pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}

plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "persona"

include(
    ":bmod:core",
    ":bmod:liquibase",
    ":third-parties:services:mim",
    ":third-parties:services:bindings:api",
    ":third-parties:services:bindings:liquibase",
    ":third-parties:services:domain-sync"
)