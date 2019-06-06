package br.com.task.bo;

import br.com.task.dao.DAOTimeKeeper;
import br.com.task.fw.Data;
import br.com.task.fw.DateTime;
import br.com.task.fw.Guid;
import br.com.task.to.TOAccount;
import br.com.task.to.TOTimeKeeper;

import java.sql.Connection;

public class BOTimeKeeper {

    public static void insert(TOAccount account, TOTimeKeeper p) throws Exception {
        try (Connection c = Data.openConnection()) {

            p.setId(Guid.getString());
            DateTime d = DateTime.now();
            p.setStartedAt(d.getTimestamp());
            p.setIdAccount(account.getId());

            DAOTimeKeeper.insert(c, p);
        }
    }

    public static boolean update(TOTimeKeeper t) throws Exception {
        try (Connection c = Data.openConnection()) {

            TOTimeKeeper p = DAOTimeKeeper.get(c, t);
            if (p != null) {

                if (t.getFinalizedAt() != null) {
                    p.setFinalizedAt(t.getFinalizedAt());
                }
                if (t.getTime() != null) {
                    p.setTime(t.getTime());
                }

                DAOTimeKeeper.update(c, p);

                return true;
            } else {
                return false;
            }
        }
    }

}
