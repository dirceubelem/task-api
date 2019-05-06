package br.com.task.bo;

import br.com.task.dao.DAOAccount;
import br.com.task.fw.Encrypt;
import br.com.task.fw.Data;
import br.com.task.fw.Guid;
import br.com.task.to.TOAccount;

import java.sql.Connection;

public class BOAccount {

    public static TOAccount auth(TOAccount u) throws Exception {

        try(Connection c = Data.openConnection()){

            u.setPassword(Encrypt.sha1(u.getPassword()));

            TOAccount t = DAOAccount.auth(c, u);
            if(t != null){
                t.setToken(Guid.getString());
                DAOAccount.updateToken(c, t);
            }

            return t;
        }

    }

}
