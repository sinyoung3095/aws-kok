package com.example.kok.mybatis.handler;

import com.example.kok.enumeration.UserRole;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(UserRole.class)
public class UserRoleHandler implements TypeHandler<UserRole> {
    @Override
    public void setParameter(PreparedStatement ps, int i, UserRole parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public UserRole getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)) {
            case "member" -> UserRole.MEMBER;
            case "company" -> UserRole.COMPANY;
            case "admin" -> UserRole.ADMIN;
            default -> null;
        };
    }

    @Override
    public UserRole getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)) {
            case "member" -> UserRole.MEMBER;
            case "company" -> UserRole.COMPANY;
            case "admin" -> UserRole.ADMIN;
            default -> null;
        };
    }

    @Override
    public UserRole getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)) {
            case "member" -> UserRole.MEMBER;
            case "company" -> UserRole.COMPANY;
            case "admin" -> UserRole.ADMIN;
            default -> null;
        };
    }
}
