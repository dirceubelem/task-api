package br.com.task.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("version")
public class ServiceVersion {

    @GET
    public String getVersion() {
        return "1.9";
    }

}
