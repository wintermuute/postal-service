package ru.wintermute.postal_service.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.wintermute.postal_service.models.Postage;

import java.util.List;


@Repository
public interface MailRepository extends JpaRepository<Postage, Integer>, JpaSpecificationExecutor<Postage> {





}
