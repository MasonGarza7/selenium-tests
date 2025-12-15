plugins {
    kotlin("jvm") version "1.9.24"
    java
}

group = "selenium.kotlin"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {

    // Kotlin
    testImplementation(kotlin("test"))

    // Selenium
    testImplementation("org.seleniumhq.selenium:selenium-java:4.20.0")

    // TestNG
    testImplementation("org.testng:testng:7.10.2")

    // Logging
    testImplementation("ch.qos.logback:logback-classic:1.5.6")

    // Reporting
    testImplementation("com.aventstack:extentreports:5.1.1")
}

tasks.test {
    useTestNG()
}

tasks.test {
    useTestNG()
    outputs.upToDateWhen { false }
}
