package hr.abysalto.hiring.mid.service;

import hr.abysalto.hiring.mid.client.DummyApiClient;
import hr.abysalto.hiring.mid.dto.api.ProductResponse;
import hr.abysalto.hiring.mid.dto.api.ProductListResponse;
import hr.abysalto.hiring.mid.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final DummyApiClient apiClient;
    private final ProductMapper mapper;

    @Cacheable(
            cacheNames = "productsList",
            key = "#skip + ':' + #limit + ':' + (#sortBy == null ? '' : #sortBy) + ':' + #order"
    )
    public ProductListResponse getProducts(int skip, int limit, String sortBy, String order) {
        return mapper.toListResponse(
                apiClient.getProducts(limit, skip, sortBy, order)
        );
    }

    @Cacheable(cacheNames = "productById", key = "#id")
    public ProductResponse getProductById(Long id) {
        return mapper.toResponse(apiClient.getProductById(id));
    }
}
