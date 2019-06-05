package br.com.task.api;

import br.com.task.bo.BOAccount;
import br.com.task.fw.Cache;
import br.com.task.to.TOAccount;
import com.sun.corba.se.impl.oa.toa.TOA;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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


}
