package br.com.task.api;

import br.com.task.bo.BOAccount;
import br.com.task.bo.BOTask;
import br.com.task.fw.Cache;
import br.com.task.fw.DateTime;
import br.com.task.to.TOAccount;
import br.com.task.to.TOTask;
import com.sun.corba.se.impl.oa.toa.TOA;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Path("account")
public class ServiceAccount {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @POST
    @Path("auth")
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public TOAccount auth(TOAccount u) throws Exception {

        TOAccount t = BOAccount.auth(u);

        if (t == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        return t;

    }

    @POST
    @Path("renew")
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public TOAccount renewToken(@HeaderParam("token") String token, TOAccount u) throws Exception {

        TOAccount t = BOAccount.renewToken(u, token);

        if (t == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        return t;

    }

    @POST
    @Path("forgot")
    @Consumes("application/json;charset=utf-8")
    public void forgot(TOAccount u) throws Exception {
        TOAccount t = BOAccount.forgot(u);
        if (t == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void update(@HeaderParam("token") String token, TOAccount u) throws Exception {
        if (BOAccount.isValid(token)) {
            BOAccount.update(u);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }


    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public String insert(TOAccount u) throws Exception {
        TOAccount t = BOAccount.insert(u);
        if (t != null) {
            JSONObject j = new JSONObject();
            j.put("id", t.getId());
            return j.toString();
        } else {
            response.sendError(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }


    @GET
    @Path("me")
    @Produces("application/json;charset=utf-8")
    public TOAccount me(@HeaderParam("token") String token) throws Exception {

        if (BOAccount.isValid(token)) {
            return BOAccount.me(token);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

    }

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TOAccount> accounts() throws Exception {
        return BOAccount.accounts();
    }

    @GET
    @Path("tasks")
    @Produces("application/json;charset=utf-8")
    public List<TOTask> listTasksProject(@HeaderParam("token") String token) throws Exception {
        if (BOAccount.isValid(token)) {

            TOAccount t = BOAccount.me(token);

            return BOTask.myTasks(t);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @POST
    @Path("photo")
    @Produces("application/json;charset=UTF-8")
    public TOAccount sendPhoto(@HeaderParam("token") String token) throws Exception {

        if (BOAccount.isValid(token)) {

            TOAccount a = BOAccount.me(token);

            if (a != null) {

                try {

                    DateTime now = DateTime.now();

                    String picture = a.getId() + "-" + now.toString("yyyyMMddHHmmss") + ".png";

                    String file = "/opt/tomcat/webapps/br.com.task.file/" + picture;
//                    String file = "/Users/dirceubelem/Desktop/" + picture;
                    getFile(request, file);

                    a.setPicture(picture);

                    BOAccount.updatePicture(a);

                    a = BOAccount.renewToken(a, token);

                    return a;

                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return null;
                }

            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    protected void getFile(HttpServletRequest rq, String file)
            throws ServletException, IOException, Exception {

        int MB = 1024 * 1024 * 1000000;
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(MB);

        List items = upload.parseRequest(rq);
        Iterator iter = items.iterator();

        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            item.write(new java.io.File(file));
        }
    }


}
