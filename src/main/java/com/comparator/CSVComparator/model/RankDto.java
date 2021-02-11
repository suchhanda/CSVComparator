package com.comparator.CSVComparator.model;

import com.comparator.CSVComparator.enums.MatchLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankDto {
    private Float rank = 0f;
    private MatchLevel matchLevel = MatchLevel.EXACT;
}
