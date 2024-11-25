package ru.xdragon.carsgrpcsevicebackend.service

import org.springframework.stereotype.Service
import CarTypeServiceGrpcKt.CarTypeServiceCoroutineImplBase as CarTypeServiceCoroutine
import CarTypeServiceOuterClass.CarTypeRequest as CarTypeRequest
import CarTypeServiceOuterClass.CarTypeResponse as CarTypeResponse

@Service
class CarTypeService : CarTypeServiceCoroutine() {
    override suspend fun addNewCarType(request: CarTypeRequest): CarTypeResponse {
        println(request.value)

        val response = CarTypeResponse
            .newBuilder()
            .setId(1)
            .setValue("Личная")
            .build()

        return response
    }
}