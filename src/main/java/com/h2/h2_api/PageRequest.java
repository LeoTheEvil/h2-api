package com.h2.h2_api;

import org.springframework.data.domain.Pageable;

public class PageRequest implements Pageable {
    Pageable firstPageWithTenElements = PageRequest.of(0, 10);
    Pageable sortedByTitle =
            PageRequest.of(0, 3, Sort.by("title"));

    Pageable sortedByIdDesc =
            PageRequest.of(0, 3, Sort.by("id").descending());

    Pageable sortedByIdDescTitleAsc =
            PageRequest.of(0, 5, Sort.by("id").descending().and(Sort.by("title")));
}
