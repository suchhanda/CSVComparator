package com.comparator.CSVComparator.service.impl;

import com.comparator.CSVComparator.builder.FileWriterBuilder;
import com.comparator.CSVComparator.constants.GeneralConstants;
import com.comparator.CSVComparator.enums.CSVHeaders;
import com.comparator.CSVComparator.model.RankDto;
import com.comparator.CSVComparator.model.TransactionDto;
import com.comparator.CSVComparator.service.CsvFileWriter;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CsvFileWriterImpl implements CsvFileWriter {
    @Override
    public void writeOutputToCsvFile(Map<TransactionDto, Map<TransactionDto, RankDto>> comparatorMap) {
        File file = new File(GeneralConstants.OUTPUT_FILE_PATH);
        try {
            FileWriter output = new FileWriter(file);
            CSVWriter write = new CSVWriter(output);
            write.writeNext(CSVHeaders.getHeaderList());
            for (Map.Entry<TransactionDto, Map<TransactionDto, RankDto>> entry : comparatorMap.entrySet()) {
                List<String> list = FileWriterBuilder.getRowEquivalentOfMap(entry);
                String[] stringArray = list.toArray(new String[0]);
                write.writeNext(stringArray);
            }
            write.close();
            log.info("Successfully wrote output to path {}", GeneralConstants.OUTPUT_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
