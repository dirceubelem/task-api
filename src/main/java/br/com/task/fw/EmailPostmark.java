package br.com.task.fw;

import br.com.task.fw.integration.Integration;
import br.com.task.fw.integration.Request;
import br.com.task.fw.integration.Response;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EmailPostmark extends Thread {

    private static final String URL = "https://api.postmarkapp.com/email";
    private String subject;
    private String message;
    private String sendTo;
    private STREAM stream = STREAM.OUTBOUND;

    public enum STREAM {
        OUTBOUND,
        BROADCAST
    }

    public EmailPostmark(String subject, String message, String sendTo) {
        this.subject = subject;
        this.message = message;
        this.sendTo = sendTo;
    }

    public EmailPostmark(String subject, String message, String sendTo, STREAM stream) {
        this.subject = subject;
        this.message = message;
        this.sendTo = sendTo;
        this.stream = stream;
    }

    public EmailPostmark() {
    }

    @Override
    public void run() {
        try {
            send();
        } catch (Exception e) {

        }
    }

    private void send() throws Exception {
        send(subject, message, sendTo, stream);
    }

    public static JSONObject send(String subject, String message, String sendTo, STREAM stream) throws Exception {
        Request req = new Request();
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("X-Postmark-Server-Token", ConfigParam.getConfigParam().getProperties("TOKEN"));

        JSONObject j = new JSONObject();
        j.put("From", "no-reply@soclicar.com.br");
        j.put("To", sendTo);
        j.put("Subject", subject);
        j.put("HtmlBody", message);
        if (stream == STREAM.OUTBOUND) {
            j.put("MessageStream", "outbound");
        } else {
            j.put("MessageStream", "broadcast");
        }

        req.setHeaders(headers);
        req.setUrl(URL);
        req.setBody(j.toString());
        Response res = Integration.requestPost(req);
        return res.getJSON();
    }
}
