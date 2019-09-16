package br.com.task.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileInputStream;

@Path("database")
public class ServiceDatabase {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @GET
    public void listen() throws Exception {

        response.setContentType("application/zip");
        byte[] buffer = new byte[4096];

        String file = "/opt/tomcat/webapps/br.com.task.file/task.zip";

        File f = new File(file);
        if (f.exists()) {

            long size = f.length();

            response.setHeader("Content-Disposition", "filename=\"task.zip\"");

            FileInputStream inputStream = null;

            try {

                long max = size;

                response.setHeader("Content-length", "" + max);

                inputStream = new FileInputStream(file);
                int bytesRead = 0;

                do {
                    bytesRead = inputStream.read(buffer, 0, buffer.length);
                    response.getOutputStream().write(buffer, 0, bytesRead);

                    if (bytesRead > max) {
                        break;
                    }

                } while (bytesRead == buffer.length);

                response.getOutputStream().flush();

            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }


        } else {
            response.sendError(403);
        }

    }

}
