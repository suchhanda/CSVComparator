package com.comparator.CSVComparator;

import com.comparator.CSVComparator.enums.CSVHeaders;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<Map<String, Integer>> list = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get("/Users/suchhanda/Downloads/Buyer.csv"))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            List<String> header = csvParser.getHeaderNames();
            List<CSVHeaders> csvHeadersList = new ArrayList<>();
            header.forEach(name -> {
                CSVHeaders csvHeaders = CSVHeaders.getHeadersByName(name);
                if (Objects.nonNull(csvHeaders)) {
                    csvHeadersList.add(csvHeaders);
                }
            });
            List<Map<CSVHeaders, String>> listCsvData = new ArrayList<>();
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
    }
}
