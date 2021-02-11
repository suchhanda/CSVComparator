package com.comparator.CSVComparator.enums;

public enum MatchLevel {
    EXACT((byte) 0, "exact"),
    PARTIAL((byte) 1, "partial"),
    ONLY_IN_BUYER((byte) 2, "only in buyer"),
    ONLY_IN_SUPPLIER((byte) 3, "only in supplier");
    private Byte id;
    private String matchLevel;

    MatchLevel(Byte id, String matchLevel) {
        this.id = id;
        this.matchLevel = matchLevel;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }
}
