buildscript {
    val extra = project.extensions.extraProperties
    extra["kotlin_version"] = "1.1.0-beta-17"
    extra["spring_boot_version"] = "1.4.3.RELEASE"

    repositories {
        gradleScriptKotlin()
        mavenCentral()
        maven { setUrl("http://dl.bintray.com/kotlin/kotlin-eap-1.1") }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin_version"]}")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${extra["spring_boot_version"]}")
        // because of https://discuss.gradle.org/t/bug-in-gradle-2-14-rc1-no-service-of-type-styledtextoutputfactory/17638
        // solution: https://github.com/spring-gradle-plugins/dependency-management-plugin/issues/87
        classpath("io.spring.gradle:dependency-management-plugin:0.6.0.RELEASE")

        classpath(kotlinModule("gradle-plugin"))
    }
}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("org.springframework.boot")
}

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)

}

repositories {
    gradleScriptKotlin()
    mavenCentral()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:${extra["kotlin_version"]}")
    compile("com.google.code.gson:gson:2.8.0")
    compile("org.mongodb:mongo-java-driver:3.4.1")
    compile("org.springframework.boot:spring-boot-starter-web")
    testCompile("junit:junit:4.11")
}