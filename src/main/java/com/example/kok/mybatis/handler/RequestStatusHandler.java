package com.example.kok.mybatis.handler;

import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

@MappedTypes(RequestStatus.class)
public class RequestStatusHandler implements TypeHandler<RequestStatus> {
    @Override
    public void setParameter(PreparedStatement ps, int i, RequestStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getValue(), Types.OTHER);
    }

    @Override
    public RequestStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)) {
            case "await" -> RequestStatus.AWAIT;
            case "accept" -> RequestStatus.ACCEPT;
            case "reject" -> RequestStatus.REJECT;
            default -> null;
        };
    }

    @Override
    public RequestStatus getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)) {
            case "await" -> RequestStatus.AWAIT;
            case "accept" -> RequestStatus.ACCEPT;
            case "reject" -> RequestStatus.REJECT;
            default -> null;
        };
    }

    @Override
    public RequestStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)) {
            case "await" -> RequestStatus.AWAIT;
            case "accept" -> RequestStatus.ACCEPT;
            case "reject" -> RequestStatus.REJECT;
            default -> null;
        };
    }
}
