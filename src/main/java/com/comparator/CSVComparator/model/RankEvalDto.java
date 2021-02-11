package com.comparator.CSVComparator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankEvalDto {
    float rank = 0f;
    int onlyInSupplierCount = 0;
}
