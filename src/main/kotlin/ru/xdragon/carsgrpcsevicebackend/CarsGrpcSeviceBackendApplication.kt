package ru.xdragon.carsgrpcsevicebackend

import io.grpc.Server
import io.grpc.ServerBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import ru.xdragon.carsgrpcsevicebackend.service.CarTypeService

@SpringBootApplication
class CarsGrpcSeviceBackendApplication

class GrpcServer(private val port: Int) {
	private val server: Server = ServerBuilder.forPort(port)
		.addService(CarTypeService())
		.build()

	fun start() {
		server.start()
		println("Server started, listening on $port")
		Runtime.getRuntime().addShutdownHook(Thread {
			println("Received Shutdown Request")
			this.stop()
			println("Successfully stopped the server")
		})
	}

	private fun stop() {
		server.shutdown()
	}

	fun blockUntilShutdown() {
		server.awaitTermination()
	}
}


fun main(args: Array<String>) {
	runApplication<CarsGrpcSeviceBackendApplication>(*args)

	val server = GrpcServer(9090)
	server.start()
	server.blockUntilShutdown()
}
