package hr.abysalto.hiring.mid.dto.api;

import java.util.List;

public record CartResponse(
        Long cartId,
        List<CartItemResponse> items
) {}
