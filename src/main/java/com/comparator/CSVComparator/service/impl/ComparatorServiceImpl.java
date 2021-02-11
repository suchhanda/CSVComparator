package com.comparator.CSVComparator.service.impl;

import com.comparator.CSVComparator.builder.TransactionDtoBuilder;
import com.comparator.CSVComparator.enums.CSVHeaders;
import com.comparator.CSVComparator.enums.MatchLevel;
import com.comparator.CSVComparator.model.RankDto;
import com.comparator.CSVComparator.model.TransactionDto;
import com.comparator.CSVComparator.service.ComparatorService;
import com.comparator.CSVComparator.service.CsvFileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.*;

@Service
public class ComparatorServiceImpl implements ComparatorService {
    @Autowired
    private CsvFileWriter csvFileWriter;

    @Override
    public void compareCSVData(Set<TransactionDto> buyerCsvData, List<Map<CSVHeaders, String>> supplierCsvData,
                               float threshold, Map<TransactionDto, Map<TransactionDto, RankDto>> comparatorMap) throws ParseException {
        for (Map<CSVHeaders, String> list : supplierCsvData) {
            TransactionDto supplierDto = TransactionDtoBuilder.getTransactionDto(list.entrySet());
            if (buyerCsvData.contains(supplierDto)) {
                comparatorMap.put(supplierDto, new HashMap<TransactionDto, RankDto>() {{
                    put(supplierDto, RankDto.builder().rank(0f).matchLevel(MatchLevel.EXACT).build());
                }});
            } else {
                for (TransactionDto buyerData : buyerCsvData) {
                    RankDto currRank = supplierDto.getRank(buyerData, threshold);
                    if (!currRank.getMatchLevel().equals(MatchLevel.ONLY_IN_SUPPLIER)) {
                        Map<TransactionDto, RankDto> transactionDtoRankDtoMap = comparatorMap.get(buyerData);
                        if (CollectionUtils.isEmpty(transactionDtoRankDtoMap)) {
                            comparatorMap.put(buyerData, new HashMap<TransactionDto, RankDto>() {{
                                put(supplierDto, currRank);
                            }});
                        } else {
                            transactionDtoRankDtoMap.forEach((key, value) -> {
                                if (currRank.getRank() < value.getRank()) {
                                    comparatorMap.put(buyerData, new HashMap<TransactionDto, RankDto>() {{
                                        put(supplierDto, currRank);
                                    }});
                                }
                            });
                        }
                    } else {
                        comparatorMap.put(supplierDto, new HashMap<TransactionDto, RankDto>() {{
                            put(supplierDto, currRank);
                        }});
                    }
                }
            }
        }
        csvFileWriter.writeOutputToCsvFile(comparatorMap);
    }

    @Override
    public Map<TransactionDto, Map<TransactionDto, RankDto>> createComparatorMapForBuyer(Set<TransactionDto> buyerCsvData) {
        Map<TransactionDto, Map<TransactionDto, RankDto>> comparatorMap = new HashMap<>();
        buyerCsvData.forEach(data -> {
            comparatorMap.put(data, new HashMap<>());
        });
        return comparatorMap;
    }
}
