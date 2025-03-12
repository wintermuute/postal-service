package ru.wintermute.postal_service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.wintermute.postal_service.daos.MailDAO;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.PostageHistoryEntity;
import ru.wintermute.postal_service.repositories.MailRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MailServiceTest {
    private static final int ID = 1;

    @Mock
    private MailRepository mailRepository;
    @Mock
    private MailDAO mailDAO;

    @InjectMocks
    private MailService mailService;

//    @Test
//    void findAllShouldCallRepository() {
//
//        List<Postage> postageList = new ArrayList<>();
//        when(mailRepository.findAll()).thenReturn(postageList);
//
//        final List<Postage> actual = mailService.findAll();
//
//        Assertions.assertNotNull(actual);
//        Assertions.assertEquals(actual, postageList);
//        verify(mailRepository).findAll();
//    }

    @Test
    void findOneShouldCallRepository() {

        final Postage postage = Mockito.mock(Postage.class);
        when(mailRepository.findById(ID)).thenReturn(Optional.ofNullable(postage));

        final Postage actual = mailService.findOne(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(postage, actual);
        verify(mailRepository).findById(ID);

    }

    @Test
    void saveShouldCallRepository() {

        final Postage postage = Mockito.mock(Postage.class);

        mailService.save(postage);

        verify(mailRepository).save(postage);
    }

    @Test
    void getHistoryShouldCallDAO() {

        List<PostageHistoryEntity> postageHistoryEntityList = new ArrayList<>();
        when(mailDAO.getHistory(ID)).thenReturn(postageHistoryEntityList);

        final List<PostageHistoryEntity> actual = mailService.getHistory(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual,postageHistoryEntityList);
        verify(mailDAO).getHistory(ID);

    }
}
