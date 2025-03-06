package ru.wintermute.postal_service.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.wintermute.postal_service.models.PostageHistoryEntity;
import ru.wintermute.postal_service.util.PostageHistoryEntityMapper;

import java.util.List;

@Component
public class MailDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MailDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PostageHistoryEntity> getHistory(int id) {
        List<PostageHistoryEntity> story = jdbcTemplate.query(
                "select tracknumber, timeofcreation,timearrived, warehouse.name as warehouse, status.info as status, history.comment from history " +
                        "join warehouse on history.currentwarehouse = warehouse.id " +
                        "join status on  history.status = status.id  where history.id=? order by timearrived asc"
                ,new Object[]{id}, new PostageHistoryEntityMapper());
        return story;
    }
}
