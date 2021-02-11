package com.comparator.CSVComparator.service;

import com.comparator.CSVComparator.enums.CSVHeaders;
import com.comparator.CSVComparator.model.RankDto;
import com.comparator.CSVComparator.model.TransactionDto;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ComparatorService {
    void compareCSVData(Set<TransactionDto> buyerCsvData, List<Map<CSVHeaders, String>> supplierCsvData, float threshold, Map<TransactionDto, Map<TransactionDto, RankDto>> comparatorMap) throws ParseException;
    Map<TransactionDto, Map<TransactionDto, RankDto>> createComparatorMapForBuyer(Set<TransactionDto> buyerCsvData);
}
