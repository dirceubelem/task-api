package br.com.task.api;

import br.com.task.bo.BOAccount;
import br.com.task.to.TOAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("user")
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

        if(t == null){
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        return t;

    }


}
