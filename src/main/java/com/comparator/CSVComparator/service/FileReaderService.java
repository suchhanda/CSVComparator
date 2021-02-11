package com.comparator.CSVComparator.service;

import com.comparator.CSVComparator.enums.CSVHeaders;

import java.util.List;
import java.util.Map;

public interface FileReaderService {
    List<Map<CSVHeaders, String>> readCSVFile(String path);
}
