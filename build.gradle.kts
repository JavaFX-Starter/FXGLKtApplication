import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.5.31"
}

group = "com.icuxika"
version = "1.0.0"

application {
    mainModule.set("application")
    mainClass.set("com.icuxika.MainAppKt")
}

repositories {
    mavenCentral()
}

val platform = when {
    OperatingSystem.current().isWindows -> {
        "win"
    }
    OperatingSystem.current().isMacOsX -> {
        "mac"
    }
    else -> {
        "linux"
    }
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.openjfx:javafx-base:17.0.0.1:${platform}")
    implementation("org.openjfx:javafx-controls:17.0.0.1:${platform}")
    implementation("org.openjfx:javafx-graphics:17.0.0.1:${platform}")
    implementation("org.openjfx:javafx-fxml:17.0.0.1:${platform}")
    implementation("org.openjfx:javafx-swing:17.0.0.1:${platform}")
    implementation("org.openjfx:javafx-media:17.0.0.1:${platform}")
    implementation("org.openjfx:javafx-web:17.0.0.1:${platform}")

    implementation("com.github.almasb:fxgl:11.17") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
    }

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}