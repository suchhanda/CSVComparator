package com.comparator.CSVComparator;

import com.comparator.CSVComparator.builder.TransactionDtoBuilder;
import com.comparator.CSVComparator.constants.GeneralConstants;
import com.comparator.CSVComparator.enums.CSVHeaders;
import com.comparator.CSVComparator.model.RankDto;
import com.comparator.CSVComparator.model.TransactionDto;
import com.comparator.CSVComparator.service.ComparatorService;
import com.comparator.CSVComparator.service.FileReaderService;
import com.comparator.CSVComparator.service.impl.ComparatorServiceImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

@SpringBootApplication
public class CsvComparatorApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext applicationContext = SpringApplication.run(CsvComparatorApplication.class, args);
		FileReaderService fileReaderService = applicationContext.getBean(FileReaderService.class);
		ComparatorService comparatorService = applicationContext.getBean(ComparatorService.class);
		List<Map<CSVHeaders, String>> buyerData = fileReaderService.readCSVFile(GeneralConstants.BUYERS_FILE_PATH);
		List<Map<CSVHeaders, String>> supplierData = fileReaderService.readCSVFile(GeneralConstants.SUPPLIERS_FILE_PATH);
		Set<TransactionDto> buyerTransDto = TransactionDtoBuilder.buildTransactionDataFromBuyerCsvData(buyerData);
		Map<TransactionDto, Map<TransactionDto, RankDto>> comparatorMap = comparatorService.createComparatorMapForBuyer(buyerTransDto);
		comparatorService.compareCSVData(buyerTransDto, supplierData, Float.parseFloat(args[0]), comparatorMap);
	}

	
}
