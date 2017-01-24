import org.springframework.boot.gradle.SpringBootPluginExtension

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
        // Used for "all-open" plugin or "kotlin-spring"
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlin_version")
    }
}

apply {
    plugin("java")
    plugin("kotlin")
    // Among other things creates executable jar
    plugin("org.springframework.boot")
    // The following make classes annotated with some Spring annotations open by default for the framework only!
    // I.e. they cannot be inherited from the code. See https://github.com/Kotlin/KEEP/pull/40
    plugin("kotlin-spring")
}

// This can be omitted since there is only one Spring bean with main method
configure<SpringBootPluginExtension> {
    mainClass = "kms.KmsKt"
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
    compile("org.mongodb:mongo-java-driver:3.4.1")
    compile("org.springframework.boot:spring-boot-starter-web")
    testCompile("org.springframework.boot:spring-boot-starter-test:1.4.3.RELEASE")
}