package br.com.task.dao;

import br.com.task.fw.Data;
import br.com.task.to.TOAccount;
import br.com.task.to.TOTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOTask {

    public static void insert(Connection c, TOTask p) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" insert into task (id, idproject, idstatus, idaccountfrom, idaccountto, name, description, tags, estimate, createdat, startedat, deliveredat, priority) values ");
        sql.append(" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

        Data.executeUpdate(c, sql.toString(), p.getId(), p.getIdProject(), p.getIdStatus(), p.getIdAccountFrom(), p.getIdAccountTo(), p.getName(),
                p.getDescription(), p.getTags(), p.getEstimate(), p.getCreatedAt(), p.getStartedAt(), p.getDeliveredAt(), p.getPriority());

    }

    public static void update(Connection c, TOTask p) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" update task set idstatus = ?, idaccountto = ?, name = ?, description = ?, tags = ?, estimate = ?, startedat = ?, deliveredat = ?, priority = ? ");
        sql.append(" where id = ? ");

        Data.executeUpdate(c, sql.toString(), p.getIdStatus(), p.getIdAccountTo(), p.getName(),
                p.getDescription(), p.getTags(), p.getEstimate(), p.getStartedAt(), p.getDeliveredAt(), p.getPriority(),
                p.getId());

    }

    public static TOTask get(Connection c, TOTask p) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, idproject, idstatus, idaccountfrom, idaccountto, name, description, tags, estimate, createdat, startedat, deliveredat, priority from task ");
        sql.append(" where ");
        sql.append(" id = ? ");

        try (ResultSet rs = Data.executeQuery(c, sql.toString(), p.getId())) {

            if (rs.next()) {
                p = new TOTask();
                p.setId(rs.getString("id"));
                p.setIdProject(rs.getString("idproject"));
                p.setIdStatus(rs.getInt("idstatus"));
                p.setIdAccountFrom(rs.getString("idaccountfrom"));
                p.setIdAccountTo(rs.getString("idaccountto"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setTags(rs.getString("tags"));
                p.setEstimate(rs.getDouble("estimate"));
                p.setCreatedAt(rs.getTimestamp("createdat"));
                p.setStartedAt(rs.getTimestamp("startedat"));
                p.setDeliveredAt(rs.getTimestamp("deliveredat"));
                p.setPriority(rs.getInt("priority"));

                return p;

            } else {
                return null;
            }

        }

    }

    public static List<TOTask> listTasksProject(Connection c, String idProject) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, idproject, idstatus, idaccountfrom, idaccountto, name, description, tags, estimate, ");
        sql.append(" createdat, startedat, deliveredat, priority from task ");
        sql.append(" where ");
        sql.append(" idproject = ? ");
        sql.append(" order by priority ");

        try (ResultSet rs = Data.executeQuery(c, sql.toString(), idProject)) {

            List<TOTask> l = new ArrayList<>();

            TOTask p;

            while (rs.next()) {
                p = new TOTask();
                p.setId(rs.getString("id"));
                p.setIdProject(rs.getString("idproject"));
                p.setIdStatus(rs.getInt("idstatus"));
                p.setIdAccountFrom(rs.getString("idaccountfrom"));
                p.setIdAccountTo(rs.getString("idaccountto"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setTags(rs.getString("tags"));
                p.setEstimate(rs.getDouble("estimate"));
                p.setCreatedAt(rs.getTimestamp("createdat"));
                p.setStartedAt(rs.getTimestamp("startedat"));
                p.setDeliveredAt(rs.getTimestamp("deliveredat"));
                p.setPriority(rs.getInt("priority"));

                l.add(p);

            }

            return l;

        }

    }

    public static List<TOTask> myTasks(Connection c, TOAccount t) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, idproject, idstatus, idaccountfrom, idaccountto, name, description, tags, estimate, ");
        sql.append(" createdat, startedat, deliveredat, priority from task ");
        sql.append(" where ");
        sql.append(" (idaccountfrom = ? or idaccountto = ?) ");
        sql.append(" deliveredat is null ");
        sql.append(" order by priority ");

        try (ResultSet rs = Data.executeQuery(c, sql.toString(), t.getId(), t.getId())) {

            List<TOTask> l = new ArrayList<>();

            TOTask p;

            while (rs.next()) {
                p = new TOTask();
                p.setId(rs.getString("id"));
                p.setIdProject(rs.getString("idproject"));
                p.setIdStatus(rs.getInt("idstatus"));
                p.setIdAccountFrom(rs.getString("idaccountfrom"));
                p.setIdAccountTo(rs.getString("idaccountto"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setTags(rs.getString("tags"));
                p.setEstimate(rs.getDouble("estimate"));
                p.setCreatedAt(rs.getTimestamp("createdat"));
                p.setStartedAt(rs.getTimestamp("startedat"));
                p.setDeliveredAt(rs.getTimestamp("deliveredat"));
                p.setPriority(rs.getInt("priority"));

                l.add(p);

            }

            return l;

        }

    }

}
