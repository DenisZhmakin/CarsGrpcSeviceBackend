package ru.xdragon.carsgrpcsevicebackend.entity

import jakarta.persistence.*
import ru.xdragon.carsgrpcsevicebackend.entity.StatusEventsEntity

@Entity(name="statuses")
data class StatusTypeEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id: Long,
    val value: String,
    @OneToMany(mappedBy = "statusType", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val statusEvents: Set<StatusEventsEntity>
)
