package br.com.task.dao;

import br.com.task.fw.Data;
import br.com.task.to.TOStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOStatus {

    public static List<TOStatus> list(Connection c) throws Exception {

        StringBuilder s = new StringBuilder();
        s.append(" select id, name from status ");
        s.append(" order by name ");

        List<TOStatus> l = new ArrayList<>();

        try (ResultSet rs = Data.executeQuery(c, s.toString())) {

            while (rs.next()) {
                TOStatus t = new TOStatus();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                l.add(t);
            }

        }

        return l;
    }

}
