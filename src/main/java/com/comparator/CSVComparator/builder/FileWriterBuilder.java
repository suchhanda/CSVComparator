package com.comparator.CSVComparator.builder;

import com.comparator.CSVComparator.enums.CSVHeaders;
import com.comparator.CSVComparator.model.RankDto;
import com.comparator.CSVComparator.model.TransactionDto;

import java.util.List;
import java.util.Map;

public class FileWriterBuilder {
    public static List<String> getRowEquivalentOfMap(Map.Entry<TransactionDto, Map<TransactionDto, RankDto>> entry) {
        List<String> rowData = TransactionDtoBuilder.convertTransactionDtoToList(entry.getKey());
        entry.getValue().forEach((key, value) -> {
            rowData.add(value.getMatchLevel().name());
            rowData.addAll(TransactionDtoBuilder.convertTransactionDtoToList(key));
        });
        return rowData;
    }
}
