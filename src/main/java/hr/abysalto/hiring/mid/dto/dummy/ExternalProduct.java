package hr.abysalto.hiring.mid.dto.dummy;

public record ExternalProduct(
        Long id,
        String title,
        String brand,
        String description,
        double price,
        String thumbnail
) {
}
