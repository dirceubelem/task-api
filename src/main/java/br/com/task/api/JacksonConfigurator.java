package br.com.task.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("application/json")
public class JacksonConfigurator implements ContextResolver<ObjectMapper> {

    final ObjectMapper objectMapper;

    public JacksonConfigurator() {
        objectMapper = new ObjectMapper();

        /* Register JodaModule to handle Joda DateTime Objects. */
//        objectMapper.registerModule(new JodaModule());
        /* We want dates to be treated as ISO8601 not timestamps. */
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public ObjectMapper getContext(Class<?> arg0) {
        return objectMapper;
    }

}
