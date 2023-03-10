package com.example.carpark.common.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
@Accessors(chain = true)
public class PaginationQuery {
    private PageRequest pageRequest;
    private Filter filter;

    public PaginationQuery() {
        this.pageRequest = PageRequest.of(0, 10);
        this.filter = new Filter();
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class Filter {
        private String field;
        private String value;
    }

}
