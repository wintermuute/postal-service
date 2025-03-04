package ru.wintermute.postal_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.repositories.MailRepository;

@Service
@Transactional(readOnly = true)
public class MailService {

    private final MailRepository mailRepository;

    @Autowired
    public MailService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    @Transactional
    public void save(Postage postage) {
        mailRepository.save(postage);
    }


}
