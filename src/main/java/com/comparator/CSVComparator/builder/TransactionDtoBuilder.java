package com.comparator.CSVComparator.builder;

import com.comparator.CSVComparator.constants.GeneralConstants;
import com.comparator.CSVComparator.enums.CSVHeaders;
import com.comparator.CSVComparator.model.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class TransactionDtoBuilder {
    private static DateFormat df1 = new SimpleDateFormat(GeneralConstants.DATE_FORMAT_1);
    private static DateFormat df2 = new SimpleDateFormat(GeneralConstants.DATE_FORMAT_2);

    public static Set<TransactionDto> buildTransactionDataFromBuyerCsvData(List<Map<CSVHeaders, String>> csvData) throws ParseException{
        Set<TransactionDto> transactionDtoSet = new HashSet<>();
        for (Map<CSVHeaders, String> dataList : csvData) {
            transactionDtoSet.add(getTransactionDto(dataList.entrySet()));
        }
        return transactionDtoSet;
    }

    public static TransactionDto getTransactionDto(Set<Map.Entry<CSVHeaders, String>> entrySet) throws ParseException {
        TransactionDto transactionDto = new TransactionDto();
        for (Map.Entry<CSVHeaders, String> entry : entrySet) {
            switch (entry.getKey()) {
                case GSTIN:
                    transactionDto.setGstIN(entry.getValue());
                    break;
                case DATE:
                    transactionDto.setDate(entry.getValue());
                    break;
                case BILL_NO:
                    transactionDto.setBillNo(StringUtils.isEmpty(entry.getValue()) ? 0L :Long.parseLong(entry.getValue()));
                    break;
                case GST_RATE:
                    transactionDto.setGstRate(StringUtils.isEmpty(entry.getValue()) ? 0 :Integer.parseInt(entry.getValue()));
                    break;
                case TXN_VALUE:
                    transactionDto.setTxnVal(StringUtils.isEmpty(entry.getValue()) ? 0F : Float.parseFloat(entry.getValue()));
                    break;
                case I_GST:
                    transactionDto.setIGst(StringUtils.isEmpty(entry.getValue()) ? 0F : Float.parseFloat(entry.getValue()));
                    break;
                case C_GST:
                    transactionDto.setCGst(StringUtils.isEmpty(entry.getValue()) ? 0F : Float.parseFloat(entry.getValue()));
                    break;
                case S_GST:
                    transactionDto.setSGst(StringUtils.isEmpty(entry.getValue()) ? 0F : Float.parseFloat(entry.getValue()));
                    break;
                case TOTAL:
                    transactionDto.setTotal(StringUtils.isEmpty(entry.getValue()) ? 0F : Float.parseFloat(entry.getValue()));
                    break;
            }
        }
        return transactionDto;
    }

    public static List<String> convertTransactionDtoToList(TransactionDto transactionDto) {
        List<String> stringList = new ArrayList<>();
        for (CSVHeaders value : CSVHeaders.values()) {
            switch (value){
                case GSTIN:
                    stringList.add(transactionDto.getGstIN());
                    break;
                case DATE:
                    stringList.add(transactionDto.getDate());
                    break;
                case BILL_NO:
                    stringList.add(String.valueOf(transactionDto.getBillNo()));
                    break;
                case GST_RATE:
                    stringList.add(String.valueOf(transactionDto.getGstRate()));
                    break;
                case TXN_VALUE:
                    stringList.add(String.valueOf(transactionDto.getTxnVal()));
                    break;
                case I_GST:
                    stringList.add(String.valueOf(transactionDto.getIGst()));
                    break;
                case C_GST:
                    stringList.add(String.valueOf(transactionDto.getCGst()));
                    break;
                case S_GST:
                    stringList.add(String.valueOf(transactionDto.getSGst()));
                    break;
                case TOTAL:
                    stringList.add(String.valueOf(transactionDto.getTotal()));
                    break;
            }
        }
        return stringList;
    }
}
