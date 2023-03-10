package com.example.carpark.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    private T payload;
    private String message;
    private String status;
    private Object error;
    private Object metadata;

    public static <T> Response<T> success(T data) {
        return new Response<T>()
                .setPayload(data)
                .setStatus("success");
    }

    public static <T> Response<T> notFound() {
        return new Response<T>()
                .setStatus("not_found");
    }

    public static <T> Response<T> badRequest() {
        return new Response<T>()
                .setStatus("bad_request");
    }

    public static <T> Response<T> badRequest(String message) {
        return new Response<T>()
                .setStatus("bad_request")
                .setMessage(message);
    }

    public static <T> Response<T> internalServerError() {
        return new Response<T>()
                .setStatus("internal_server_error");
    }

    public static  Response paging(Page page) {
        return new Response()
                .setMetadata(new PageMetadata(page.getSize(), page.getTotalElements(), page.getTotalPages(), page.getNumber()))
                .setStatus("success")
        .setPayload(page.getContent());
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageMetadata {
        private final int size;
        private final long totalElements;
        private final int totalPages;
        private final int number;

        public PageMetadata(int size, long totalElements, int totalPages, int number) {
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.number = number;
        }
    }

}
