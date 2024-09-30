package com.social.news_service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PaginationAndSortingHelper {

    public static Pageable createPageable(int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(sortDirection)
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return PageRequest.of(page, size, sort);
    }
}
