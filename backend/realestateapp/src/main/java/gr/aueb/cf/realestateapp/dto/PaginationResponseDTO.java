package gr.aueb.cf.realestateapp.dto;

import java.util.List;

public record PaginationResponseDTO<T>(
        List<T> content,
        int currentPage,
        int totalPages,
        long totalElements,
        boolean hasNext,
        boolean hasPrevious
) {
}
