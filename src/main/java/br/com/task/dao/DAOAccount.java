package br.com.task.dao;

import br.com.task.fw.Data;
import br.com.task.to.TOAccount;

import java.sql.Connection;
import java.sql.ResultSet;

public class DAOAccount {

    public static TOAccount auth(Connection c, TOAccount u) throws Exception {

        StringBuilder s = new StringBuilder();
        s.append(" select id, name, email, password, token, createdat, active from account ");
        s.append(" where ");
        s.append(" email = ? and password = ? ");
        s.append(" and active ");

        try(ResultSet rs = Data.executeQuery(c, s.toString(), u.getEmail(), u.getPassword())){

            if(rs.next()){
                u = new TOAccount();
                u.setId(rs.getString("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setToken(rs.getString("token"));
                u.setActive(rs.getBoolean("active"));
                u.setCreatedAt(rs.getTimestamp("createdat"));
                return u;
            }else{
                return null;
            }

        }

    }

    public static void updateToken(Connection c, TOAccount u) throws Exception {

        StringBuilder s = new StringBuilder();
        s.append(" update account ");
        s.append(" set token = ? ");
        s.append(" where id = ? ");

        Data.executeUpdate(c, s.toString(), u.getToken(), u.getId());

    }

}
