plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // Paging
    val pagingVersion = "3.2.1"
    implementation("androidx.paging:paging-common:$pagingVersion")
}