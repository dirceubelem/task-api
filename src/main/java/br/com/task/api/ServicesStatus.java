package br.com.task.api;

import br.com.task.bo.BOStatus;
import br.com.task.to.TOStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("status")
public class ServicesStatus {

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TOStatus> list() throws Exception{
        return BOStatus.list();
    }

}
