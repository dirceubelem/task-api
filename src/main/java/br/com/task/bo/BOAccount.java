package br.com.task.bo;

import br.com.task.dao.DAOAccount;
import br.com.task.fw.*;
import br.com.task.to.TOAccount;
import com.sun.corba.se.impl.oa.toa.TOA;

import java.sql.Connection;
import java.util.List;

public class BOAccount {

    public static boolean isValid(String token) throws Exception {
        try (Connection c = Data.openConnection()) {
            TOAccount a = DAOAccount.getByToken(c, token);
            if (a != null) {

                DateTime now = DateTime.now();
                if (a.getExpiredAt().getTime() > now.getMillis()) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        }
    }

    public static TOAccount me(String token) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOAccount.getByToken(c, token);
        }
    }

    public static List<TOAccount> accounts() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOAccount.accounts(c);
        }
    }

    public static TOAccount forgot(TOAccount u) throws Exception {
        try (Connection c = Data.openConnection()) {

            TOAccount t = DAOAccount.getByEmail(c, u);
            if (t != null) {

                String novaSenha = Guid.getString().substring(0, 8);

                StringBuilder message = new StringBuilder();
                message.append("Olá ").append(t.getName()).append(",<br/><br/>");
                message.append("Recebemos um pedido de nova senha .... <b>").append(novaSenha).append("</b><br/><br/>");
                message.append("Obrigado");

                EmailPostmark email = new EmailPostmark("Esqueci minha senha - Task", message.toString(), u.getEmail());
                email.start();

                t.setPassword(Encrypt.sha1(novaSenha));
                DAOAccount.update(c, t);

            }
            return t;

        }
    }

    public static void update(TOAccount u) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOAccount.update(c, u);
        }
    }

    public static void updatePicture(TOAccount u) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOAccount.updatePicture(c, u);
        }
    }

    public static TOAccount insert(TOAccount u) throws Exception {
        try (Connection c = Data.openConnection()) {

            TOAccount t = DAOAccount.getByEmail(c, u);
            if (t == null) {

                u.setId(Guid.getString());
                u.setPassword(Encrypt.sha1(u.getPassword()));
                DAOAccount.insert(c, u);

                StringBuilder message = new StringBuilder();
                message.append("Olá ").append(u.getName()).append(",<br/><br/>");
                message.append("Seja bem vindo!<br/><br/>");
                message.append("Obrigado");

                EmailPostmark email = new EmailPostmark("Seja bem vindo ao Task", message.toString(), u.getEmail());
                email.start();

                return u;
            } else {
                return null;
            }

        }
    }

    public static TOAccount auth(TOAccount u) throws Exception {

        try (Connection c = Data.openConnection()) {

            u.setPassword(Encrypt.sha1(u.getPassword()));

            TOAccount t = DAOAccount.auth(c, u);
            if (t != null) {

                DateTime expiredAt = DateTime.now();
                expiredAt.addMinute(5);

                t.setExpiredAt(expiredAt.getTimestamp());

                t.setToken(Guid.getString());
                DAOAccount.updateToken(c, t);
            }

            return t;
        }

    }

    public static TOAccount renewToken(TOAccount u, String token) throws Exception {

        try (Connection c = Data.openConnection()) {

            TOAccount t = DAOAccount.getByToken(c, token);
            if (t != null && t.getEmail().equals(u.getEmail())) {

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
