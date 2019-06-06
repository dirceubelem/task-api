package br.com.task.dao;

import br.com.task.fw.Data;
import br.com.task.to.TOTimeKeeper;

import java.sql.Connection;
import java.sql.ResultSet;

public class DAOTimeKeeper {

    public static void insert(Connection c, TOTimeKeeper p) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" insert into timekeeper (id, idtask, idaccount, startedat, finalizedat, time) values ");
        sql.append(" (?, ?, ?, ?, ?, ?) ");

        Data.executeUpdate(c, sql.toString(), p.getId(), p.getIdTask(), p.getIdAccount(), p.getStartedAt(), p.getFinalizedAt(), p.getTime());

    }

    public static void update(Connection c, TOTimeKeeper p) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" update timekeeper set finalizedat = ?, time = ? ");
        sql.append(" where id = ? ");

        Data.executeUpdate(c, sql.toString(), p.getFinalizedAt(), p.getTime(), p.getId());

    }

    public static TOTimeKeeper hasStartedAndNotFinalized(Connection c, String idTask, String idAccount) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, idtask, idaccount, startedat, finalizedat, time from timekeeper ");
        sql.append(" where ");
        sql.append(" id = ? and finalizedat is null and idaccount = ? ");

        try (ResultSet rs = Data.executeQuery(c, sql.toString(), idTask, idAccount)) {

            if (rs.next()) {
                TOTimeKeeper p = new TOTimeKeeper();
                p.setId(rs.getString("id"));
                p.setIdTask(rs.getString("idtask"));
                p.setIdAccount(rs.getString("idaccount"));
                p.setStartedAt(rs.getTimestamp("startedat"));
                p.setFinalizedAt(rs.getTimestamp("finalizedat"));
                p.setTime(rs.getInt("time"));

                return p;
            } else {
                return null;
            }

        }
    }

    public static TOTimeKeeper get(Connection c, TOTimeKeeper p) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, idtask, idaccount, startedat, finalizedat, time from timekeeper ");
        sql.append(" where ");
        sql.append(" id = ? ");

        try (ResultSet rs = Data.executeQuery(c, sql.toString(), p.getId())) {

            if (rs.next()) {
                p = new TOTimeKeeper();
                p.setId(rs.getString("id"));
                p.setIdTask(rs.getString("idtask"));
                p.setIdAccount(rs.getString("idaccount"));
                p.setStartedAt(rs.getTimestamp("startedat"));
                p.setFinalizedAt(rs.getTimestamp("finalizedat"));
                p.setTime(rs.getInt("time"));

                return p;

            } else {
                return null;
            }

        }

    }

}
