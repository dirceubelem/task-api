package br.com.task.bo;

import br.com.task.dao.DAOUser;
import br.com.task.fw.Criptografia;
import br.com.task.fw.Data;
import br.com.task.fw.Guid;
import br.com.task.to.TOUser;

import java.sql.Connection;

public class BOUser {

    public static TOUser auth(TOUser u) throws Exception {

        try(Connection c = Data.openConnection()){

            u.setPassword(Criptografia.sha1(u.getPassword()));

            TOUser t = DAOUser.auth(c, u);
            if(t != null){
                t.setToken(Guid.getString());
                DAOUser.updateToken(c, t);
            }

            return t;
        }

    }

}
