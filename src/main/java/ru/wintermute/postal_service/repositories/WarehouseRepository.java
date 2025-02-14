package ru.wintermute.postal_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wintermute.postal_service.models.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {

}
