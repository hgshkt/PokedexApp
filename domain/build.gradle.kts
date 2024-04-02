plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    // Paging
    val pagingVersion = "3.2.1"
    implementation("androidx.paging:paging-common:$pagingVersion")
}