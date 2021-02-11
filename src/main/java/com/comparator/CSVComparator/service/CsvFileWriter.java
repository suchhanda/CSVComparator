package com.comparator.CSVComparator.service;

import com.comparator.CSVComparator.model.RankDto;
import com.comparator.CSVComparator.model.TransactionDto;

import java.util.Map;

public interface CsvFileWriter {
    void writeOutputToCsvFile(Map<TransactionDto, Map<TransactionDto, RankDto>> comparatorMap);
}
