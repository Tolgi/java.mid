package hr.abysalto.hiring.mid.dto.api;

import java.util.List;

public record ProductListResponse(
        List<ProductResponse> products,
        int total,
        int skip,
        int limit
) {}