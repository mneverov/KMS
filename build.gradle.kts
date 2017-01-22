import org.springframework.boot.gradle.SpringBootPluginExtension

version = "0.0.1-SNAPSHOT"
group = "KMS"

buildscript {
    val kotlin_version = "1.1.0-beta-17"
    val spring_boot_version = "1.4.3.RELEASE"
    extra["kotlin_version"] = kotlin_version
    extra["spring_boot_version"] = spring_boot_version

    repositories {
        gradleScriptKotlin()
        mavenCentral()
        maven { setUrl("http://dl.bintray.com/kotlin/kotlin-eap-1.1") }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$spring_boot_version")
        // Because of https://discuss.gradle.org/t/bug-in-gradle-2-14-rc1-no-service-of-type-styledtextoutputfactory/17638
        // Solution: https://github.com/spring-gradle-plugins/dependency-management-plugin/issues/87
        classpath("io.spring.gradle:dependency-management-plugin:0.6.0.RELEASE")

        classpath(kotlinModule("gradle-plugin"))
    }
}

apply {
    plugin("java")
    plugin("kotlin")
    // Among other things creates executable jar
    plugin("org.springframework.boot")
}

// This can be omitted since there is only one Spring bean with main method
configure<SpringBootPluginExtension> {
    mainClass = "rest.KmsKt"
}

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)
}

repositories {
    gradleScriptKotlin()
    mavenCentral()
}

val kotlin_version = extra["kotlin_version"]

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    compile("com.google.code.gson:gson:2.8.0")
    compile("org.mongodb:mongo-java-driver:3.4.1")
    compile("org.springframework.boot:spring-boot-starter-web")
    testCompile("junit:junit:4.11")
}