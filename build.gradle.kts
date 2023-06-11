// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.ClassPath.gradle)
        classpath(Dependencies.ClassPath.kotlinGradle)
        classpath(Dependencies.ClassPath.navArgs)
        classpath(Dependencies.ClassPath.hiltAndroid)
        classpath(Dependencies.ClassPath.googleMapSecret)
        classpath("com.android.tools.build:gradle:7.3.1")
    }
}

allprojects {
    repositories {
        google()

        mavenCentral()
        maven { url = uri(Dependencies.Repositories.jitpackUrl) }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}