plugins {
    id("common-libs.java-conventions")
    id("common-libs.publish-conventions")
}

repositories {
    // papermc repo
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(project(":tag-lib"))
    implementation("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
}

description = "An item library which targets tag-lib and Paper"
