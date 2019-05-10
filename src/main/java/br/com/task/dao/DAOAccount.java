package br.com.task.dao;

import br.com.task.fw.Data;
import br.com.task.to.TOAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOAccount {

    public static void insert(Connection c, TOAccount t) throws Exception {
        StringBuilder s = new StringBuilder();
        s.append(" insert into account(id, name, email, password, createdat, active) values ");
        s.append(" (?, ?, ?, ?, now(), true) ");
        Data.executeUpdate(c, s.toString(), t.getId(), t.getName(), t.getEmail(), t.getPassword());
    }

    public static void update(Connection c, TOAccount t) throws Exception {
        StringBuilder s = new StringBuilder();
        s.append(" update account set name = ?, email = ?, password = ? ");
        s.append(" where id = ? ");
        Data.executeUpdate(c, s.toString(), t.getName(), t.getEmail(), t.getPassword(), t.getId());
    }

    public static TOAccount getByToken(Connection c, String token) throws Exception {

        StringBuilder s = new StringBuilder();
        s.append(" select id, name, email, password, token, createdat, active, expiredat from account ");
        s.append(" where ");
        s.append(" token = ? ");
        s.append(" and active ");

        try (ResultSet rs = Data.executeQuery(c, s.toString(), token)) {

            if (rs.next()) {
                TOAccount u = new TOAccount();
                u.setId(rs.getString("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setToken(rs.getString("token"));
                u.setActive(rs.getBoolean("active"));
                u.setCreatedAt(rs.getTimestamp("createdat"));
                u.setExpiredAt(rs.getTimestamp("expiredat"));
                return u;
            } else {
                return null;
            }

        }
    }

    public static TOAccount getByEmail(Connection c, TOAccount t) throws Exception {

        StringBuilder s = new StringBuilder();
        s.append(" select id, name, email, password, token, createdat, active, expiredat from account ");
        s.append(" where ");
        s.append(" email = ? ");
        s.append(" and active ");

        try (ResultSet rs = Data.executeQuery(c, s.toString(), t.getEmail())) {

            if (rs.next()) {
                TOAccount u = new TOAccount();
                u.setId(rs.getString("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setToken(rs.getString("token"));
                u.setActive(rs.getBoolean("active"));
                u.setCreatedAt(rs.getTimestamp("createdat"));
                u.setExpiredAt(rs.getTimestamp("expiredat"));
                return u;
            } else {
                return null;
            }

        }
    }

    public static List<TOAccount> accounts(Connection c) throws Exception {

        StringBuilder s = new StringBuilder();
        s.append(" select id, name, email, password, token, createdat, active, expiredat from account ");
        s.append(" where ");
        s.append(" active ");
        s.append(" order by name ");

        List<TOAccount> l = new ArrayList<>();

        try (ResultSet rs = Data.executeQuery(c, s.toString())) {

            while (rs.next()) {
                TOAccount u = new TOAccount();
                u.setId(rs.getString("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setToken(rs.getString("token"));
                u.setActive(rs.getBoolean("active"));
                u.setCreatedAt(rs.getTimestamp("createdat"));
                l.add(u);
            }

        }

        return l;

    }

    public static TOAccount auth(Connection c, TOAccount u) throws Exception {

        StringBuilder s = new StringBuilder();
        s.append(" select id, name, email, password, token, createdat, active, expiredat from account ");
        s.append(" where ");
        s.append(" email = ? and password = ? ");
        s.append(" and active ");

        try (ResultSet rs = Data.executeQuery(c, s.toString(), u.getEmail(), u.getPassword())) {

            if (rs.next()) {
                u = new TOAccount();
                u.setId(rs.getString("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setToken(rs.getString("token"));
                u.setActive(rs.getBoolean("active"));
                u.setCreatedAt(rs.getTimestamp("createdat"));
                return u;
            } else {
                return null;
            }

        }

    }

    public static void updateToken(Connection c, TOAccount u) throws Exception {

        StringBuilder s = new StringBuilder();
        s.append(" update account ");
        s.append(" set token = ?, expiredat = ? ");
        s.append(" where id = ? ");

        Data.executeUpdate(c, s.toString(), u.getToken(), u.getExpiredAt(), u.getId());

    }

}
