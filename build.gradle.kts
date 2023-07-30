import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.liquibase.gradle") version "2.2.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
}

group = "jw.org"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.yaml:snakeyaml:2.0")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    liquibaseRuntime("org.liquibase:liquibase-core")
    liquibaseRuntime("info.picocli:picocli:4.6.1")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.+")
    liquibaseRuntime("org.postgresql:postgresql")
    liquibaseRuntime("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")

    // https://mvnrepository.com/artifact/org.telegram/telegrambots-spring-boot-starter
    implementation("org.telegram:telegrambots-spring-boot-starter:6.7.0")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}



val props = org.yaml.snakeyaml.Yaml()
    .load<Map<String, String>>(FileInputStream("src/main/resources/application.yml"))


liquibase {
    activities.register("mainMySql") {
        // https://docs.liquibase.com/parameters/home.html
        this.arguments = mapOf(
            "changelogFile" to "src/main/resources/liquibase/changelog.xml",
            "url" to "jdbc:mysql://localhost:3306/db",
            "username" to "mysql",
            "password" to "mysql",
            //"driver" to "com.mysql.cj.jdbc.Driver",
            "databaseChangelogLockTableName" to "liquibase_lock",
            "databaseChangelogTableName" to "liquibase",
            "logLevel" to "FINE"
        )
    }
    activities.register("mainPostgres") {
        // https://docs.liquibase.com/parameters/home.html
        this.arguments = mapOf(
            "changelogFile" to "src/main/resources/liquibase/changelog.xml",
            "url" to "jdbc:postgresql://localhost:5432/?currentSchema=public",
            "username" to "postgres",
            "password" to "postgres",
            //"driver" to "com.mysql.cj.jdbc.Driver",
            "databaseChangelogLockTableName" to "liquibase_lock",
            "databaseChangelogTableName" to "liquibase",
            "logLevel" to "FINE"
        )
    }
    runList = "mainMySql, mainPostgres" // runs against mysql & postgres
    //runList = "mainMySql" // runs only against mysql
    //runList = "mainPostgres" // runs only against postgres
}




/*
// https://mokkapps.de/blog/how-to-generate-angular-and-spring-code-from-open-api-specification
openApiValidate {
    inputSpec.set("http://localhost:8080/api-docs")
}

openApiGenerate {
    generatorName.set("spring")
    library.set("spring-boot")
    inputSpec.set("${rootDir}/openapi/schema.yaml")
    outputDir.set("${rootDir}/backend/openapi")
    systemProperties.putAll(mapOf(
        "modelDocs" to "false",
        "models" to "",
        "apis" to "",
        "supportingFiles" to "false"
    ))
    configOptions.putAll(mapOf(
        "useOptional"          to "true",
        "swaggerDocketConfig"  to "false",
        "performBeanValidation" to "false",
        "useBeanValidation"    to "false",
        "useTags"              to "true",
        "singleContentTypes"   to "true",
        "basePackage"          to "de.mokkapps.gamenews.api",
        "configPackage"        to "de.mokkapps.gamenews.api",
        "title"                to rootProject.name,
        "java8"                to "false",
        "dateLibrary"          to "java8",
        "serializableModel"    to "true",
        "artifactId"           to rootProject.name,
        "apiPackage"           to "de.mokkapps.gamenews.api",
        "modelPackage"         to "de.mokkapps.gamenews.api.model",
        "invokerPackage"       to "de.mokkapps.gamenews.api",
        "interfaceOnly"        to "true"
    ))
}
*/
