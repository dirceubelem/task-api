package br.com.task.bo;

import br.com.task.dao.DAOProject;
import br.com.task.fw.Data;
import br.com.task.to.TOProject;

import java.sql.Connection;
import java.util.List;

public class BOProject {

    public static void insert(TOProject p) throws Exception {
        try(Connection c = Data.openConnection()){
            DAOProject.insert(c, p);
        }
    }

    public static void update(TOProject p) throws Exception {
        try(Connection c = Data.openConnection()){
            DAOProject.update(c, p);
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
