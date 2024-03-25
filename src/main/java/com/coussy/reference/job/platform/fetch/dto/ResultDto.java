package com.coussy.reference.job.platform.fetch.dto;


import java.time.LocalDateTime;
import java.util.List;

public record ResultDto(String id, String next, List<String> keywords, LocalDateTime date_posted) {}




