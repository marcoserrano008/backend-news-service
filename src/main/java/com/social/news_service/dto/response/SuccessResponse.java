package com.social.news_service.dto.response;

import lombok.Getter;

@Getter
public class SuccessResponse<T> extends CommonResponse {

    private T data;

    public SuccessResponse(T data) {
        super(true, "Success");
        this.data = data;
    }

    public SuccessResponse() {
        super(false, null);
    }
}
