import org.gradle.internal.impldep.org.fusesource.jansi.AnsiRenderer.test

plugins {
    java
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation("org.junit.jupiter:junit-jupiter-api:5.6.0")    // JUnit
    implementation("org.junit.jupiter:junit-jupiter-engine")    // JUnit

    implementation("org.springframework.boot:spring-boot-starter-aop")  // aop
    implementation("org.springframework.boot:spring-boot-starter-web")  // web
    implementation("org.springframework.boot:spring-boot-starter-validation") // validation
    implementation("org.projectlombok:lombok:1.18.22")  // lombok

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}