package com.comparator.CSVComparator.enums;

import java.util.ArrayList;
import java.util.List;

public enum CSVHeaders {
    GSTIN((byte) 0, "GSTIN"),
    DATE((byte) 1, "Date"),
    BILL_NO((byte) 2, "Bill no"),
    GST_RATE((byte) 3, "GST rate(%)"),
    TXN_VALUE((byte) 4, "Taxable value"),
    I_GST((byte) 5, "IGST"),
    C_GST((byte) 5, "CGST"),
    S_GST((byte) 5, "SGST"),
    TOTAL((byte) 8, "Total");

    private byte id;
    private String name;

    CSVHeaders(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CSVHeaders getHeadersByName(String name) {
        for (CSVHeaders csvHeaders : CSVHeaders.values()) {
            if (csvHeaders.getName().equals(name)) {
                return csvHeaders;
            }
        }
        return null;
    }

    public static String[] getNameList() {
        String[] nameList = new String[CSVHeaders.values().length];
        int i = 0;
        for (CSVHeaders csvHeaders : CSVHeaders.values()) {
            nameList[i++] = csvHeaders.getName();
        }
        return nameList;
    }

    public static String[] getHeaderList() {
        String[] headers = new String[(CSVHeaders.values().length * 2) + 1];
        int i = 0;
        for (CSVHeaders csvHeaders : CSVHeaders.values()) {
            headers[i++] = csvHeaders.getName();
        }
        headers[i++] = "Category";
        for (CSVHeaders csvHeaders : CSVHeaders.values()) {
            headers[i++] = csvHeaders.getName();
        }
        return headers;
    }

    }
