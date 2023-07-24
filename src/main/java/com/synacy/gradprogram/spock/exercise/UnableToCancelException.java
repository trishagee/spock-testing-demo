package com.synacy.gradprogram.spock.exercise;

public class UnableToCancelException  extends RuntimeException {
    public UnableToCancelException(String errorMessage) {
        super(errorMessage);
    }
}
