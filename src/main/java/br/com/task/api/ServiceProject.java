package br.com.task.api;

import br.com.task.bo.BOAccount;
import br.com.task.bo.BOProject;
import br.com.task.bo.BOTask;
import br.com.task.to.TOAccount;
import br.com.task.to.TOProject;
import br.com.task.to.TOTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("project")
public class ServiceProject {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @POST
    @Consumes("application/json;charset=utf-8")
    public void insert(@HeaderParam("token") String token, TOProject p) throws Exception {
        if (BOAccount.isValid(token)) {

            TOAccount account = BOAccount.me(token);

            BOProject.insert(account, p);

            response.sendError(HttpServletResponse.SC_CREATED);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void update(@HeaderParam("token") String token, TOProject p) throws Exception {
        if (BOAccount.isValid(token)) {
            if (BOProject.update(p)) {
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
    public TOProject get(@HeaderParam("token") String token, @PathParam("id") String id) throws Exception {
        if (BOAccount.isValid(token)) {
            TOProject p = new TOProject();
            p.setId(id);
            return BOProject.get(p);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TOProject> list(@HeaderParam("token") String token) throws Exception {
        if (BOAccount.isValid(token)) {
            return BOProject.list();
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @GET
    @Path("{id}/tasks")
    @Produces("application/json;charset=utf-8")
    public List<TOTask> listTasksProject(@HeaderParam("token") String token, @PathParam("id") String idProject) throws Exception {
        if (BOAccount.isValid(token)) {
            return BOTask.listTasksProject(idProject);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

}
