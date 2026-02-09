package hr.abysalto.hiring.mid.dto.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(
        description = """
                Paginated list of products retrieved from DummyJSON.
                
                Pagination is controlled using 'skip' and 'limit' query parameters.
                """
)public record ProductListResponse(

        @Schema(description = "List of products for the current page.", implementation = ProductResponse.class)
        List<ProductResponse> products,

        @Schema(description = "Total number of products available in DummyJSON.", example = "100")
        int total,

        @Schema(description = "Number of products skipped (pagination offset).", example = "0")
        int skip,

        @Schema(description = "Maximum number of products returned.", example = "20")
        int limit
) {}