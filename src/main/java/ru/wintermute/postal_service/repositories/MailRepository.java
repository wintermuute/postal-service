package ru.wintermute.postal_service.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wintermute.postal_service.models.Postage;

@Repository
public interface MailRepository extends JpaRepository<Postage, Integer> {
}
