package hr.abysalto.hiring.mid.client;

import hr.abysalto.hiring.mid.dto.dummy.ExternalProduct;
import hr.abysalto.hiring.mid.dto.dummy.ExternalProductList;
import hr.abysalto.hiring.mid.exception.ExternalClientException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class DummyApiClient {

    private final RestClient restClient;

    public DummyApiClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://dummyjson.com").build();
    }

    public ExternalProductList getProducts(
            int limit,
            int skip,
            String sortBy,
            String order
    ) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> {
                        uriBuilder
                                .path("/products")
                                .queryParam("limit", limit)
                                .queryParam("skip", skip);

                        if (sortBy != null) {
                            uriBuilder
                                    .queryParam("sortBy", sortBy)
                                    .queryParam("order", order);
                        }
                        return uriBuilder.build();
                    })
                    .retrieve()
                    .body(ExternalProductList.class);

        } catch (RestClientException ex) {
            throw new ExternalClientException("Error calling DummyJSON API", ex);
        }
    }

    public ExternalProduct getProductById(Long id) {
        try {
            return restClient.get()
                    .uri("/products/{id}", id)
                    .retrieve()
                    .body(ExternalProduct.class);
        } catch (RestClientException ex) {
            throw new ExternalClientException("Error calling DummyJSON API", ex);
        }
    }
}
