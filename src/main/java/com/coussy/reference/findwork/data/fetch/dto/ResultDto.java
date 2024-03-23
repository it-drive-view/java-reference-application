package com.coussy.reference.findwork.data.fetch.dto;


import java.time.LocalDateTime;
import java.util.List;

public record ResultDto(String id, List<String> keywords, LocalDateTime date_posted) {}




