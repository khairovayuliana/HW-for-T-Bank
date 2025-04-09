plugins {
    java
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.liquibase.gradle") version "2.2.0"
}

group = "ru.currency_tracking"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")


    runtimeOnly("org.postgresql:postgresql")


    implementation("org.liquibase:liquibase-core")


    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")


    developmentOnly("org.springframework.boot:spring-boot-devtools")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Конфигурация Liquibase
liquibase {
    activities {
        register("main") {
            this.arguments = mapOf(
                "changeLogFile" to "src/main/resources/db/changelog/db.changelog-master.xml",
                "url" to "jdbc:postgresql://localhost:5432/currency_db",
                "username" to "postgres",
                "password" to "postgres"
            )
        }
    }
}