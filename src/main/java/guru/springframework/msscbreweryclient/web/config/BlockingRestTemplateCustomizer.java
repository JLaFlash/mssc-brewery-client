package guru.springframework.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jt on 2019-08-08.
 */

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
    private final int MAX_TOTAL ;
    private final int MAX_PER_ROUTE;
    private final int CONNECTION_REQUEST_TIMEOUT;
    private final int SOCKET_TIMEOUT;

    public BlockingRestTemplateCustomizer(@Value("${sfg.MaxTotal}") int max_total,
                                          @Value("${sfg.MaxPerRoute}") int max_per_route,
                                          @Value("${sfg.ConnectionRequestTimeout}") int connection_request_timeout,
                                          @Value("${sfg.SocketTimeout}") int socket_timeout) {
        MAX_TOTAL = max_total;
        MAX_PER_ROUTE = max_per_route;
        CONNECTION_REQUEST_TIMEOUT = connection_request_timeout;
        SOCKET_TIMEOUT = socket_timeout;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_TOTAL);
        connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
