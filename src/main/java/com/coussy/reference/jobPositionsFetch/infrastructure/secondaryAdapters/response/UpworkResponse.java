package com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.response;

import java.time.LocalDateTime;
import java.util.List;

public record UpworkResponse(int count, String next, List<JobPositionResponse> results, String detail) {

    public record JobPositionResponse(String id, List<String> keywords, LocalDateTime date_posted) {}

}




