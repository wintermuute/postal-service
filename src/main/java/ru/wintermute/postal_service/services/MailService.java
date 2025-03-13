package ru.wintermute.postal_service.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wintermute.postal_service.daos.MailDAO;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.PostageHistoryEntity;
import ru.wintermute.postal_service.repositories.MailRepository;
import ru.wintermute.postal_service.util.PostageHistoryEntityMapper;


import java.util.List;
import java.util.Objects;

@Service
public class MailService {


    private final MailRepository mailRepository;
    private final MailDAO mailDAO;

    @Autowired
    public MailService(MailRepository mailRepository, MailDAO mailDAO) {
        this.mailRepository = mailRepository;
        this.mailDAO = mailDAO;
    }

    @Transactional(readOnly = true)
    public Page<Postage> findAll(Pageable pageable, String trackNumber){

       // Pageable pageable = PageRequest.of(pageNumber - 1,POSTAGES_PER_PAGE);
        Specification<Postage> specification = new Specification<Postage>() {
            @Override
            public Predicate toPredicate(Root<Postage> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                Predicate predicate = criteriaBuilder.conjunction();
                if(Objects.nonNull(trackNumber)) {
                    predicate = criteriaBuilder.equal(root.get("trackNumber"),trackNumber);
                }
                return predicate;
            }
        };
        return mailRepository.findAll(specification,pageable);
//        if(trackNumber != null)
//            return mailRepository.findAll(trackNumber,pageable);
//
//        return mailRepository.findAll(pageable);

    }
    @Transactional(readOnly = true)
    public Postage findOne(int id) {
        return mailRepository.findById(id).get();
    }
    @Transactional
    public void save(Postage postage) {
        mailRepository.save(postage);
    }


    public List<Postage> getPostagesInStore(int id) {
        return mailDAO.findByCurrentWarehouse(id);
    }

    public List<PostageHistoryEntity> getHistory(int id) {
        return mailDAO.getHistory(id);
    }

    @Transactional
    public void update(int id, Postage updatedPostage) {
        updatedPostage.setId(id);
        mailRepository.save(updatedPostage);

    }

}
