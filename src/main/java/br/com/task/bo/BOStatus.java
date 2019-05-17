package br.com.task.bo;

import br.com.task.dao.DAOStatus;
import br.com.task.fw.Data;
import br.com.task.to.TOStatus;

import java.sql.Connection;
import java.util.List;

public class BOStatus {

    public static List<TOStatus> list() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOStatus.list(c);
        }
    }

}
