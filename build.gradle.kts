plugins {
    id ("groovy")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation ("org.spockframework:spock-core:2.3-groovy-4.0")
    testImplementation ("org.apache.groovy:groovy:4.0.13")
    testImplementation ("net.bytebuddy:byte-buddy:1.14.9")
    testRuntimeOnly ("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
