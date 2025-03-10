package ru.wintermute.postal_service.util;

import org.springframework.jdbc.core.RowMapper;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.PostageHistoryEntity;
import ru.wintermute.postal_service.models.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostageMapper implements RowMapper<Postage> {
    @Override
    public Postage mapRow(ResultSet rs, int rowNum) throws SQLException {

        Postage postage = new Postage();
        postage.setId(rs.getInt("id"));
        postage.setTrackNumber(rs.getString("tracknumber"));
        //postage.setTimeofcreation(rs.getTimestamp("timeofcreation").toLocalDateTime());
        // postage.setWarehousename(rs.getString("warehouse"));
        postage.setStatus(Status.values()[rs.getInt("status")]);
        postage.setComment(rs.getString("comment"));

        return postage;
    }
}
