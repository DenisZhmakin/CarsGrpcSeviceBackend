package ru.xdragon.carsgrpcsevicebackend.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.xdragon.carsgrpcsevicebackend.entity.CarTypeEntity

interface CarTypeRepository : JpaRepository<CarTypeEntity, Long>
