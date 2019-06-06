package br.com.task.bo;

import br.com.task.dao.DAOTask;
import br.com.task.fw.Data;
import br.com.task.fw.DateTime;
import br.com.task.fw.Guid;
import br.com.task.to.TOAccount;
import br.com.task.to.TOTask;

import java.sql.Connection;
import java.util.List;

public class BOTask {

    public static List<TOTask> listTasksProject(String idProject) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOTask.listTasksProject(c, idProject);
        }
    }

    public static void insert(TOAccount account, TOTask p) throws Exception {
        try (Connection c = Data.openConnection()) {

            p.setId(Guid.getString());
            DateTime d = DateTime.now();
            p.setCreatedAt(d.getTimestamp());
            p.setIdStatus(1);
            p.setIdAccountFrom(account.getId());

            DAOTask.insert(c, p);
        }
    }

    public static boolean update(TOTask t) throws Exception {
        try (Connection c = Data.openConnection()) {

            TOTask p = DAOTask.get(c, t);
            if (p != null) {

                if (t.getName() != null) {
                    p.setName(t.getName());
                }
                if (t.getIdAccountTo() != null) {
                    p.setIdAccountTo(t.getIdAccountTo());
                }
                if (t.getDescription() != null) {
                    p.setDescription(t.getDescription());
                }
                if (t.getIdStatus() != null) {
                    p.setIdStatus(t.getIdStatus());
                }
                if (t.getPriority() != null) {
                    p.setPriority(t.getPriority());
                }
                if (t.getDeliveredAt() != null) {
                    p.setDeliveredAt(t.getDeliveredAt());
                }
                if (t.getEstimate() != null) {
                    p.setEstimate(t.getEstimate());
                }
                if (t.getStartedAt() != null) {
                    p.setStartedAt(t.getStartedAt());
                }
                if (t.getTags() != null) {
                    p.setTags(t.getTags());
                }

                DAOTask.update(c, p);

                return true;
            } else {
                return false;
            }
        }
    }

    public static TOTask get(TOTask p) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOTask.get(c, p);
        }
    }

}
