import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask
import com.github.gradle.node.pnpm.task.PnpmTask
import com.github.gradle.node.yarn.task.YarnTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.liquibase.gradle") version "2.2.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
    id("com.github.node-gradle.node") version "7.0.1"
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
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    liquibaseRuntime("org.postgresql:postgresql")
    implementation("org.postgresql:postgresql")
    liquibaseRuntime("com.mysql:mysql-connector-j")
    implementation("com.mysql:mysql-connector-j")
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
            "logLevel" to "INFO"
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
            "logLevel" to "INFO"
        )
    }

    // runs against mysql & postgres
    runList = "mainMySql, mainPostgres"

    // runs only against mysql
    //runList = "mainMySql"

    // runs only against postgres
    //runList = "mainPostgres"
}

// based on https://github.com/srs/gradle-node-plugin/issues/292#issuecomment-562848790
node {
    download.set(true)
    version.set("20.8.0")
    npmVersion.set("10.1.0")
    pnpmVersion.set("8.10.2")
    yarnVersion.set("1.22.19")
    nodeProjectDir.set(project.file("ui"))
    workDir.set(project.file("${project.buildDir}/nodejs"))
    pnpmWorkDir.set(project.file("${project.buildDir}/pnpm"))
    npmWorkDir.set(project.file("${project.buildDir}/npm"))
    yarnWorkDir.set(project.file("${project.buildDir}/yarn"))
}

val addYarnExpress = task<YarnTask>("addYarnExpress") {
    description = "installs deps in modules folder and updates package.json file"
    group = "yarn"
    // add the express package only
    args.addAll("add", "express", "--dev")
    shouldRunAfter("nodeSetup", "yarnSetup")
}

val npmInit = task<NpmTask>("npmInit") {
    npmCommand.set(listOf("init"))
    args.addAll("-y")
    shouldRunAfter("nodeSetup", "npmSetup")
}

// https://learning.oreilly.com/library/view/next-js-quick-start/9781788993661/847c2413-daf8-4a0a-b59a-9bea81d2e81f.xhtml
val installNextDev = task<NpmTask>("installNextD") {
    args.addAll("add", "next@latest", "--save-dev")
    shouldRunAfter("nodeSetup", "npmSetup")
}

// https://learning.oreilly.com/library/view/next-js-quick-start/9781788993661/847c2413-daf8-4a0a-b59a-9bea81d2e81f.xhtml
val installReactAndReacrDom = task<NpmTask>("installReactAndReactDom") {
    args.addAll("add", "react@latest", "react-dom@latest", "--save")
    shouldRunAfter("nodeSetup", "npmSetup")
}

val addNpmExpress = task<NpmTask>("addNpmExpress") {
    // add the express package only
    args.addAll("add", "express", "--dev")
    shouldRunAfter("nodeSetup", "npmSetup")
}

val runNodeMon = task<NpxTask>("runNodeMon"){
    group = "npx"
    command.set("-exec")
    args.addAll("nodemon", "test")
    //shouldRunAfter("nodeSetup", "npmSetup")

}

val runPnpmInit = task<PnpmTask>("runPnpmInit"){
    group = "pnpm"
    pnpmCommand.add("init")
    args.addAll("-y")
    shouldRunAfter("nodeSetup", "pnpmInstall")
}

val runPnpmListLicenses = task<PnpmTask>("runPnpmListLicenses"){
    group = "pnpm"
    pnpmCommand.add("licenses")
    args.addAll("list")
    shouldRunAfter("nodeSetup", "pnpmInstall")
}

/*

val integrationTest = task<Test>("integrationTest") {
    description = "Task to run integration tests"
    group = "verification"

    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    shouldRunAfter("test")
}
*/

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
