package br.com.task.api;

import br.com.task.bo.BOAccount;
import br.com.task.bo.BOTask;
import br.com.task.to.TOAccount;
import br.com.task.to.TOTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("task")
public class ServiceTask {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @POST
    @Consumes("application/json;charset=utf-8")
    public void insert(@HeaderParam("token") String token, TOTask p) throws Exception {
        if (BOAccount.isValid(token)) {

            TOAccount account = BOAccount.me(token);

            BOTask.insert(account, p);

            response.sendError(HttpServletResponse.SC_CREATED);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void update(@HeaderParam("token") String token, TOTask p) throws Exception {
        if (BOAccount.isValid(token)) {
            if (BOTask.update(p)) {
                response.sendError(HttpServletResponse.SC_ACCEPTED);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @POST
    @Path("start")
    @Consumes("application/json;charset=utf-8")
    public void start(@HeaderParam("token") String token, TOTask p) throws Exception {
        if (BOAccount.isValid(token)) {

            TOAccount a = BOAccount.me(token);

            if (BOTask.startTask(p, a)) {
                response.sendError(HttpServletResponse.SC_ACCEPTED);
            } else {
                response.sendError(HttpServletResponse.SC_CONFLICT);
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @POST
    @Path("stop")
    @Consumes("application/json;charset=utf-8")
    public void stop(@HeaderParam("token") String token, TOTask p) throws Exception {
        if (BOAccount.isValid(token)) {

            TOAccount a = BOAccount.me(token);

            if (BOTask.stopTask(p, a)) {
                response.sendError(HttpServletResponse.SC_ACCEPTED);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @POST
    @Path("finished")
    @Consumes("application/json;charset=utf-8")
    public void finished(@HeaderParam("token") String token, TOTask p) throws Exception {
        if (BOAccount.isValid(token)) {

            TOAccount a = BOAccount.me(token);

            if (BOTask.finishedTask(p, a)) {
                response.sendError(HttpServletResponse.SC_ACCEPTED);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @GET
    @Path("{id}")
    @Produces("application/json;charset=utf-8")
    public TOTask get(@HeaderParam("token") String token, @PathParam("id") String id) throws Exception {
        if (BOAccount.isValid(token)) {
            TOTask p = new TOTask();
            p.setId(id);
            return BOTask.get(p);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

}
