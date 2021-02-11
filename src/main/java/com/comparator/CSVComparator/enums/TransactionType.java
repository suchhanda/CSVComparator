package com.comparator.CSVComparator.enums;

public enum TransactionType {
    SUPPLIER((byte) 0, "Supplier"),
    BUYER((byte) 0, "Buyer");
    private byte id;
    private String type;

    TransactionType(Byte id, String type) {
        this.id = id;
        this.type = type;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
