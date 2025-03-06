package ru.wintermute.postal_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wintermute.postal_service.daos.MailDAO;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.PostageHistoryEntity;
import ru.wintermute.postal_service.repositories.MailRepository;
import ru.wintermute.postal_service.util.PostageHistoryEntityMapper;


import java.util.List;

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
    public List<Postage> findAll(){
        return mailRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Postage findOne(int id) {
        return mailRepository.findById(id).get();
    }
    @Transactional
    public void save(Postage postage) {
        mailRepository.save(postage);
    }

    public List<PostageHistoryEntity> getHistory(int id) {
        return mailDAO.getHistory(id);
    }




}
