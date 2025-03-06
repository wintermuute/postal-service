package ru.wintermute.postal_service.util;

import org.springframework.jdbc.core.RowMapper;
import ru.wintermute.postal_service.models.PostageHistoryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;



public class PostageHistoryEntityMapper implements RowMapper<PostageHistoryEntity> {

    @Override
    public PostageHistoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        PostageHistoryEntity historyEntity = new PostageHistoryEntity();
        historyEntity.setTrackNumber(rs.getString("tracknumber"));
        historyEntity.setTimeofcreation(rs.getTimestamp("timeofcreation").toLocalDateTime());
        historyEntity.setWarehousename(rs.getString("warehouse"));
        historyEntity.setStatus(rs.getString("status"));
        historyEntity.setComment(rs.getString("comment"));

        return historyEntity;
    }

}
