package ru.xdragon.carsgrpcsevicebackend.grpc

import CarTypeServiceGrpc
import CarTypeServiceOuterClass.CarTypeRequest
import CarTypeServiceOuterClass.CarTypeResponse
import io.grpc.stub.StreamObserver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.xdragon.carsgrpcsevicebackend.entity.CarTypeEntity
import ru.xdragon.carsgrpcsevicebackend.repository.CarTypeRepository

@Service
class CarTypeGrpc : CarTypeServiceGrpc.CarTypeServiceImplBase() {
    @Autowired
    private lateinit var carTypeRepository: CarTypeRepository

    override fun addNewCarType(request: CarTypeRequest, responseObserver: StreamObserver<CarTypeResponse>) {
        val carTypeEntity = carTypeRepository.save(CarTypeEntity(
            value = request.value,
        ))

        val carTypeResponse = CarTypeResponse
            .newBuilder()
            .setId(carTypeEntity.id)
            .setValue(carTypeEntity.value)
            .build()

        responseObserver.onNext(carTypeResponse)
        responseObserver.onCompleted()
    }
}