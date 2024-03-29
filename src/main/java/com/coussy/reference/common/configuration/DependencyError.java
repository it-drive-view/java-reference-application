package com.coussy.reference.common.configuration;

public class DependencyError extends RuntimeException {

    private String dependencyIdentifier;
    private String errorCode;

    public DependencyError(String message) {
        super(message);
    }

    public DependencyError(String dependencyIdentifier, String formatted, String errorCode) {
        super(formatted);
        this.dependencyIdentifier = dependencyIdentifier;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }



}
