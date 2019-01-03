/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.nibe.connector;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.FormContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.afriese.iot.configuration.MainConfiguration;
import de.afriese.iot.converter.Converter;
import de.afriese.iot.converter.ResponseType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NibeuplinkConnector implements UplinkConstants {

    @Autowired
    private MainConfiguration config;

    @Autowired
    private Converter converter;

    /**
     * HTTP client for asynchronous calls
     */
    private HttpClient client = new HttpClient(new SslContextFactory(true));

    private boolean loggedIn = false;

    private void logIn() throws Exception {
        if (!client.isStarted()) {
            client.start();
        }

        Fields fields = new Fields();
        fields.add(LOGIN_FIELD_EMAIL, config.getNibeUser());
        fields.add(LOGIN_FIELD_PASSWORD, config.getNibePassword());
        fields.add(LOGIN_FIELD_RETURN_URL, "");
        FormContentProvider cp = new FormContentProvider(fields);

        Request request = client.newRequest(LOGIN_URL);
        request.content(cp);
        request.followRedirects(false);
        request.method(HttpMethod.POST);

        try {
            ContentResponse contentResponse = request.send();
            String content = contentResponse.getContentAsString();

            int status = contentResponse.getStatus();
            log.debug("Status {}", status);
            log.trace(content);

            if (status == 302) {
                loggedIn = true;
            }
            else {
                loggedIn = false;
            }
        }
        catch (InterruptedException | TimeoutException | ExecutionException e) {
            log.error(e.getMessage());
        }

    }

    public Map<String, String> getValues() {

        try {
            if (!loggedIn) {
                logIn();
            }

            if (loggedIn) {

                Fields fields = new Fields();
                fields.add(DATA_API_FIELD_LAST_DATE, DATA_API_FIELD_LAST_DATE_DEFAULT_VALUE);
                fields.add(DATA_API_FIELD_ID, config.getNibeId());

                for (Integer i = 10000; i < 10500; i++) {
                    fields.add(DATA_API_FIELD_DATA, i.toString());
                }
                for (Integer i = 40000; i < 50000; i++) {
                    fields.add(DATA_API_FIELD_DATA, i.toString());
                }
                fields.add(DATA_API_FIELD_DATA, DATA_API_FIELD_DATA_DEFAULT_VALUE);
                FormContentProvider cp = new FormContentProvider(fields);

                Request request = client.newRequest(DATA_API_URL);
                request.content(cp);
                request.followRedirects(false);
                request.method(HttpMethod.POST);

                try {
                    ContentResponse contentResponse = request.send();
                    String content = contentResponse.getContentAsString();

                    return converter.convertJsonResponse(ResponseType.NIBE_UPLINK, content);
                }
                catch (InterruptedException | TimeoutException | ExecutionException e) {
                    log.error(e.getMessage());
                }
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        finally {
            try {
                client.stop();
                loggedIn = false;
            }
            catch (Exception e) {
                log.warn("Stopping http Client failed for unknown reason", e);
            }
        }
        return null;
    }

}
