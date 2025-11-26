plugins {
	java
	id("org.springframework.boot") version "3.5.8"
	id("io.spring.dependency-management") version "1.1.7"
	id("jacoco")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Projecto Mutantes de MercadoLibre"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	//Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testCompileOnly("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")

	// Base de datos H2
	implementation("com.h2database:h2")

	// Swagger/OpenAPI
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.14")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
tasks.test {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

jacoco {
	toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required.set(true)
		html.required.set(true)
	}
}
