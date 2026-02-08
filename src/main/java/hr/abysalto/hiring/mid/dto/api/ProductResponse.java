package hr.abysalto.hiring.mid.dto.api;

public record ProductResponse(
     Long id,
     String title,
     String brand,
     String description,
     double price,
     String thumbnail
) {}
