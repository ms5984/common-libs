plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

description = "A collection of libraries for use in other projects"

nexusPublishing {
    repositories {
        create("sonatype") {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
