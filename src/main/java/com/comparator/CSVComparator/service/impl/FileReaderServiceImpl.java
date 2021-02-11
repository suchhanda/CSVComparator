package com.comparator.CSVComparator.service.impl;

import com.comparator.CSVComparator.enums.CSVHeaders;
import com.comparator.CSVComparator.service.FileReaderService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public List<Map<CSVHeaders, String>> readCSVFile(String path) {
        List<Map<CSVHeaders, String>> listCsvData = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            List<String> header = csvParser.getHeaderNames();
            List<CSVHeaders> csvHeadersList = new ArrayList<>();
            header.forEach(name -> {
                CSVHeaders csvHeaders = CSVHeaders.getHeadersByName(name);
                if (Objects.nonNull(csvHeaders)) {
                    csvHeadersList.add(csvHeaders);
                }
            });
            List<CSVRecord> csvParserRecords = csvParser.getRecords();
            for (CSVRecord records : csvParserRecords) {
                int index = 0;
                Map<CSVHeaders, String> mapOfRow = new HashMap<>();
                while (index < csvHeadersList.size()) {
                    mapOfRow.put(csvHeadersList.get(index), records.get(index));
                    index++;
                }
                listCsvData.add(mapOfRow);
            }
            listCsvData.forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
        return listCsvData;
    }
}
