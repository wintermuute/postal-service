package ru.wintermute.postal_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wintermute.postal_service.models.Warehouse;
import ru.wintermute.postal_service.repositories.WarehouseRepository;

@Service
@Transactional(readOnly = true)
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }
    @Transactional
    public void save(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }
}
