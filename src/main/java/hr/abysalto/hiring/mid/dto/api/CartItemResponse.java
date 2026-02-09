package hr.abysalto.hiring.mid.dto.api;

public record CartItemResponse(
        Long productId,
        int quantity,
        ProductResponse product
) {}
