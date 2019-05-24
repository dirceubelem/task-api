package br.com.task.api;

import br.com.task.bo.BOStatus;
import br.com.task.fw.Cache;
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
//        return BOStatus.list();

        Object r = Cache.getCache("status", "list");

        List<TOStatus> l;

        if(r == null){
            l = BOStatus.list();
            Cache.setCache("status", "list", l,5);
        }else{
            l = (List<TOStatus>)r;
        }

        return l;


    }

}
