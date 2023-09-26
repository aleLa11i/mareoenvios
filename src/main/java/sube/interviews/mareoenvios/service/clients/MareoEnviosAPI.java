package sube.interviews.mareoenvios.service.clients;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MareoEnviosAPI {

    private final static Logger LOGGER = LogManager.getLogger(MareoEnviosAPI.class);

    public void patchState(Long id, String state) throws IOException{
        HttpClient httpClient = HttpClients.createDefault();
        HttpPatch httpPatch = new HttpPatch("http://localhost:8080/mareoenvios/api/shippings/" + id );
        String requestBody =  String.format("{\"transition\": \"%s\"}", state );
        httpPatch.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
        HttpResponse response = httpClient.execute(httpPatch);
        LOGGER.info(EntityUtils.toString(response.getEntity()));
        httpClient.getConnectionManager().shutdown();
    }

}
