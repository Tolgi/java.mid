package hr.abysalto.hiring.mid.dto.dummy;

import hr.abysalto.hiring.mid.dto.api.ProductResponse;

import java.util.List;

public record ExternalProductList(
        List<ProductResponse> products,
        int total,
        int skip,
        int limit
) {}