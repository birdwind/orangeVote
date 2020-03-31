package com.orange.orange_vote.enums;

public enum NumberEnum {
    MEMBER("mem-"), ROLE("role-"), MODULE("module-"), FUNCTION("fun-"), TEAM("team-");

    private String value;

    NumberEnum(String value) {
        this.value = value;
    }

    public String valueOf() {
        return this.value;
    }
}
