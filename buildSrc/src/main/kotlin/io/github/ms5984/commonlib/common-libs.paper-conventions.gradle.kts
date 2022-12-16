plugins {
    id("common-libs.java-conventions")
}

val paperApiVersion by extra("1.19.2-R0.1-SNAPSHOT")

repositories {
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
}

dependencies {
    implementation("io.papermc.paper:paper-api:$paperApiVersion")
}
