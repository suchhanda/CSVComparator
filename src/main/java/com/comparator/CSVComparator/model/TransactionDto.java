package com.comparator.CSVComparator.model;

import com.comparator.CSVComparator.constants.GeneralConstants;
import com.comparator.CSVComparator.enums.MatchLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private String gstIN;
    private String date;
    private Long billNo;
    private Integer gstRate;
    private Float txnVal;
    private Float iGst;
    private Float cGst;
    private Float sGst;
    private Float total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDto)) return false;
        TransactionDto that = (TransactionDto) o;
        return getGstIN().equals(that.getGstIN()) && Objects.equals(getDate(), that.getDate()) && getBillNo().equals(that.getBillNo()) && getGstRate().equals(that.getGstRate()) && getTxnVal().equals(that.getTxnVal()) && Objects.equals(iGst, that.iGst) && Objects.equals(cGst, that.cGst) && Objects.equals(sGst, that.sGst) && getTotal().equals(that.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGstIN(), getDate(), getBillNo(), getGstRate(), getTxnVal(), iGst, cGst, sGst, getTotal());
    }

    public RankDto getRank(Object o, Float threshold) throws ParseException {
        RankEvalDto rankEvalDto = new RankEvalDto();
        TransactionDto that = (TransactionDto) o;
        stringComparator(getGstIN(), that.getGstIN(), rankEvalDto);
        Date dateThis = DateUtils.parseDate(getDate(), GeneralConstants.DATE_FORMAT_1, GeneralConstants.DATE_FORMAT_2);
        Date dateThat = DateUtils.parseDate(that.getDate(), GeneralConstants.DATE_FORMAT_1, GeneralConstants.DATE_FORMAT_2);
        longComparator(dateThis.getTime(), dateThat.getTime(), threshold, rankEvalDto);
        longComparator(getBillNo(), that.getBillNo(), threshold, rankEvalDto);
        integerComparator(getGstRate(), that.getGstRate(), threshold, rankEvalDto);
        floatComparator(getTxnVal(), that.getTxnVal(), threshold, rankEvalDto);
        floatComparator(getIGst(), that.getIGst(), threshold, rankEvalDto);
        floatComparator(getCGst(), that.getCGst(), threshold, rankEvalDto);
        floatComparator(getSGst(), that.getSGst(), threshold, rankEvalDto);
        floatComparator(getTotal(), that.getTotal(), threshold, rankEvalDto);
        RankDto rankDto = RankDto.builder().rank(rankEvalDto.getRank()).build();
        if (rankEvalDto.getOnlyInSupplierCount() == 9) {
            rankDto.setMatchLevel(MatchLevel.ONLY_IN_SUPPLIER);
        } else {
            rankDto.setMatchLevel(MatchLevel.PARTIAL);
        }
        return rankDto;
    }

    public void stringComparator(String a, String b, RankEvalDto rankEvalDto) {
        float rank = 0f;
        MatchLevel matchLevel = MatchLevel.ONLY_IN_SUPPLIER;
        if (a.equals(b)) {
            matchLevel = MatchLevel.EXACT;
        } else {
            if (a.contains(b) || b.contains(a)) {
                rank += a.length() > b.length() ?
                        (float) a.length() - b.length() :
                        (float) b.length() - a.length() ;
                matchLevel = MatchLevel.PARTIAL;
            } else {
                rank += a.length() > b.length() ?
                        (float) a.length():
                        (float) b.length();
                matchLevel = MatchLevel.ONLY_IN_SUPPLIER;
            }
        }
        rankEvalDto.setRank(rankEvalDto.getRank() + rank);
        if (matchLevel.equals(MatchLevel.ONLY_IN_SUPPLIER)) {
            rankEvalDto.setOnlyInSupplierCount(rankEvalDto.getOnlyInSupplierCount() + 1);
        }
    }

    public void longComparator(Long a, Long b, float threshold, RankEvalDto rankEvalDto) {
        float rank = 0f;
        MatchLevel matchLevel = MatchLevel.ONLY_IN_SUPPLIER;
        if (a.equals(b)) {
            matchLevel = MatchLevel.EXACT;
        } else {
            if ((a > b && (float) a-b <= threshold)) {
                rank += (float) a-b;
            } else if ((b > a && (float) b-a <= threshold)) {
                rank += (float) b-a;
            } else {
                matchLevel = MatchLevel.ONLY_IN_SUPPLIER;
                rank += a > b ? (float) a : (float) b;
            }
        }
        rankEvalDto.setRank(rankEvalDto.getRank() + rank);
        if (matchLevel.equals(MatchLevel.ONLY_IN_SUPPLIER)) {
            rankEvalDto.setOnlyInSupplierCount(rankEvalDto.getOnlyInSupplierCount() + 1);
        }
    }

    public void floatComparator(Float a, Float b, float threshold, RankEvalDto rankEvalDto) {
        float rank = 0f;
        MatchLevel matchLevel = MatchLevel.ONLY_IN_SUPPLIER;
        if (a.equals(b)) {
            matchLevel = MatchLevel.EXACT;
        } else {
            if ((a > b && a-b <= threshold)) {
                rank += a-b;
            } else if ((b > a && b-a <= threshold)) {
                rank += b-a;
            } else {
                matchLevel = MatchLevel.ONLY_IN_SUPPLIER;
                rank += a > b ? a : b;
            }
        }
        rankEvalDto.setRank(rankEvalDto.getRank() + rank);
        if (matchLevel.equals(MatchLevel.ONLY_IN_SUPPLIER)) {
            rankEvalDto.setOnlyInSupplierCount(rankEvalDto.getOnlyInSupplierCount() + 1);
        }
    }

    public void integerComparator(Integer a, Integer b, float threshold, RankEvalDto rankEvalDto) {
        float rank = 0f;
        MatchLevel matchLevel = MatchLevel.ONLY_IN_SUPPLIER;
        if (a.equals(b)) {
            matchLevel = MatchLevel.EXACT;
        } else {
            if ((a > b && (float) a-b <= threshold)) {
                rank += (float) a-b;
            } else if ((b > a && (float) b-a <= threshold)) {
                rank += (float) b-a;
            } else {
                matchLevel = MatchLevel.ONLY_IN_SUPPLIER;
                rank += a > b ? (float) a : (float) b;
            }
        }
        rankEvalDto.setRank(rankEvalDto.getRank() + rank);
        if (matchLevel.equals(MatchLevel.ONLY_IN_SUPPLIER)) {
            rankEvalDto.setOnlyInSupplierCount(rankEvalDto.getOnlyInSupplierCount() + 1);
        }
    }
}
