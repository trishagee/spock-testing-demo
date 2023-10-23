plugins {
    id("com.gradle.enterprise") version "3.15.1"
    id("com.gradle.common-custom-user-data-gradle-plugin") version "1.11.3"
}

gradleEnterprise {
    server = "https://18.212.219.143.nip.io"
    allowUntrustedServer = true
    buildScan {
        publishAlways()
    }
}

rootProject.name = "spock-testing"
