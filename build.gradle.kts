import com.google.protobuf.gradle.id

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"

	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.google.protobuf") version "0.9.4"

	kotlin("plugin.jpa") version "1.9.25"
}

group = "ru.xdragon"
version = "0.1.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
	google()
}

val grpcVersion = "1.68.1"
val protobufVersion = "4.28.3"
val grpcKotlinVersion = "1.4.1"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// gRPC
	implementation("io.grpc:grpc-protobuf:$grpcVersion")
	implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")
	implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
	testImplementation("io.projectreactor:reactor-test")
	runtimeOnly("io.grpc:grpc-netty-shaded:$grpcVersion")

	runtimeOnly("org.postgresql:postgresql")
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:$protobufVersion"
	}

	plugins {
		create("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
		}

		create("grpckt") {
			artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk8@jar"
		}
	}

	generateProtoTasks {
		all().forEach {
			it.plugins {
				create("grpc")
				create("grpckt")
			}
			it.builtins {
				create("kotlin")
			}
		}
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
