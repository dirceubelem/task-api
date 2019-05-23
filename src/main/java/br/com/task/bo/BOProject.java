package br.com.task.bo;

import br.com.task.dao.DAOProject;
import br.com.task.fw.Data;
import br.com.task.fw.DateTime;
import br.com.task.fw.Guid;
import br.com.task.to.TOAccount;
import br.com.task.to.TOProject;

import java.sql.Connection;
import java.util.List;

public class BOProject {

    public static void insert(TOAccount account, TOProject p) throws Exception {
        try(Connection c = Data.openConnection()){

            p.setId(Guid.getString());
            DateTime d = DateTime.now();
            p.setCreatedAt(d.getTimestamp());
            p.setActive(true);
            p.setIdAccountOwner(account.getId());

            DAOProject.insert(c, p);
        }
    }

    public static boolean update(TOProject t) throws Exception {
        try(Connection c = Data.openConnection()){

            TOProject p = DAOProject.get(c, t);
            if(p != null) {

                p.setName(t.getName());
                p.setActive(t.isActive());

                DAOProject.update(c, p);

                return true;
            }else{
                return false;
            }
        }
    }

    public static TOProject get(TOProject p) throws Exception {
        try(Connection c = Data.openConnection()){
            return DAOProject.get(c, p);
        }
    }

    public static List<TOProject> list() throws Exception {
        try(Connection c = Data.openConnection()){
            return DAOProject.list(c);
        }
    }

}
