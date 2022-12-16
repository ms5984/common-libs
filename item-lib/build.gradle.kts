plugins {
    id("common-libs.java-conventions")
    id("common-libs.publish-conventions")
    id("common-libs.paper-conventions")
}

dependencies {
    implementation(project(":tag-lib"))
}

description = "An item library which targets tag-lib and Paper"
