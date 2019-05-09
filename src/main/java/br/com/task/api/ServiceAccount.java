package br.com.task.api;

import br.com.task.bo.BOAccount;
import br.com.task.to.TOAccount;

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
