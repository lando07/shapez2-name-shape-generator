plugins {
    id("java")
}

group = "com.shapez2"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.jar.configure {
    manifest {
        attributes(mapOf("Main-Class" to "com.shapez2.Main"))
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

dependencies {
    implementation("org.jetbrains:annotations:15.0")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}