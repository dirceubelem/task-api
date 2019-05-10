package br.com.task.fw;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email extends Thread {

    private String subject;
    private String message;
    private String sendTo;
    private String account;
    private String accountFrom;
    private String password;

    public Email(String subject, String message, String sendTo) {
        this.subject = subject;
        this.message = message;
        this.sendTo = sendTo;
        this.account = "fluosite@gmail.com";
        this.accountFrom = "fluosite@gmail.com";
        this.password = "Senha123";
    }

    @Override
    public void run() {
        try {
            Email.send(subject, message, sendTo, account, accountFrom, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void send(String subject, String message, String sendTo, String account, String accountFrom, String password) throws Exception {
        try {

            final String host = "smtp.gmail.com";
            final String username = account;
            final int porta = 587;

            if (!sendTo.trim().equals("")) {

                Properties props = new Properties();
                props.put("mail.smtps.auth", "true");
                props.put("mail.starttls.enable", "true");
                props.put("mail.smtp.port", porta);
                props.put("mail.from", accountFrom);
                props.put("mail.smtp.user", accountFrom);
                props.put("mail.debug", "true");

                Session session = Session.getDefaultInstance(props, null);

                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(accountFrom));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
                msg.setSubject(subject, "UTF-8");

                msg.setContent(message, "text/html; charset=ISO-8859-1");

                // set the message content here
                Transport t = session.getTransport("smtps");
                try {
                    t.connect(host, username, password);
                    t.sendMessage(msg, msg.getAllRecipients());
                } finally {
                    t.close();
                }
            } else {
                throw new Exception("Erro ao enviar e-mail");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}