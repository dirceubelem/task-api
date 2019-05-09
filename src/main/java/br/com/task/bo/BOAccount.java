package br.com.task.bo;

import br.com.task.dao.DAOAccount;
import br.com.task.fw.DateTime;
import br.com.task.fw.Encrypt;
import br.com.task.fw.Data;
import br.com.task.fw.Guid;
import br.com.task.to.TOAccount;

import java.sql.Connection;

public class BOAccount {

    public static boolean isValid(String token) throws Exception {
        try(Connection c = Data.openConnection()){
            TOAccount a = DAOAccount.getByToken(c, token);
            if(a != null){

                DateTime now = DateTime.now();
                if(a.getExpiredAt().getTime() > now.getMillis()){
                    return true;
                }else{
                    return false;
                }

            }else{
                return false;
            }
        }
    }

    public static TOAccount me(String token) throws Exception {
        try(Connection c = Data.openConnection()){
            return DAOAccount.getByToken(c, token);
        }
    }

    public static TOAccount auth(TOAccount u) throws Exception {

        try(Connection c = Data.openConnection()){

            u.setPassword(Encrypt.sha1(u.getPassword()));

            TOAccount t = DAOAccount.auth(c, u);
            if(t != null){

                DateTime expiredAt = DateTime.now();
                expiredAt.addMinute(5);

                t.setExpiredAt(expiredAt.getTimestamp());

                t.setToken(Guid.getString());
                DAOAccount.updateToken(c, t);
            }

            return t;
        }

    }

}
