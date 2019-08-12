package br.com.task.dao;

import br.com.task.fw.Data;
import br.com.task.to.TOProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOProject {

    public static void insert(Connection c, TOProject p) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" insert into project (id, idAccountOwner, name, createdAt, active) values ");
        sql.append(" (?, ?, ?, ?, ?) ");

        Data.executeUpdate(c, sql.toString(), p.getId(), p.getIdAccountOwner(), p.getName(),
                p.getCreatedAt(), p.isActive());

    }

    public static void update(Connection c, TOProject p) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" update project set idAccountOwner = ?, name = ?, createdAt = ?, active = ? ");
        sql.append(" where id = ? ");

        Data.executeUpdate(c, sql.toString(), p.getIdAccountOwner(), p.getName(),
                p.getCreatedAt(), p.isActive(), p.getId());

    }

    public static TOProject get(Connection c, TOProject p) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, idAccountOwner, name, createdAt, active from project ");
        sql.append(" where ");
        sql.append(" id = ? and active ");

        try (ResultSet rs = Data.executeQuery(c, sql.toString(), p.getId())) {

            if (rs.next()) {
                p = new TOProject();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setIdAccountOwner(rs.getString("idAccountOwner"));
                p.setCreatedAt(rs.getTimestamp("createdAt"));
                p.setActive(rs.getBoolean("active"));

                return p;

            } else {
                return null;
            }

        }

    }

    public static List<TOProject> list(Connection c) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select p.id, p.idAccountOwner, p.name, p.createdAt, p.active, count(t.id) as tasks from project p  ");
        sql.append(" left join task t on p.id = t.idproject  ");
        sql.append(" where ");
        sql.append(" p.active ");
        sql.append(" group by p.id, p.idAccountOwner, p.name, p.createdAt, p.active ");
        sql.append(" order by p.name ");

        try (ResultSet rs = Data.executeQuery(c, sql.toString())) {

            List<TOProject> l = new ArrayList<>();

            TOProject p;

            while (rs.next()) {
                p = new TOProject();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setIdAccountOwner(rs.getString("idAccountOwner"));
                p.setCreatedAt(rs.getTimestamp("createdAt"));
                p.setActive(rs.getBoolean("active"));
                p.setTasks(rs.getInt("tasks"));

                l.add(p);

            }

            return l;

        }

    }

}
