package ru.wintermute.postal_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wintermute.postal_service.models.Warehouse;
import ru.wintermute.postal_service.repositories.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    //CRUD
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public Warehouse findOne(int id) {
        Optional<Warehouse> foundWarehouse = warehouseRepository.findById(id);
        return foundWarehouse.orElse(null);
    }

    @Transactional
    public void save(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    @Transactional
    public void update(int id,Warehouse updatedWarehouse) {
        updatedWarehouse.setId(id);
        warehouseRepository.save(updatedWarehouse);
    }
    @Transactional
    public void delete(int id) {
        warehouseRepository.deleteById(id);
    }

}
