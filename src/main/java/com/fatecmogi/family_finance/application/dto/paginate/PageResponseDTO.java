package com.fatecmogi.family_finance.application.dto.paginate;

import com.fatecmogi.family_finance.application.dto.IDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<I extends IDTO> {
    List<I> content;
    private long totalElements;
    private int totalPages;
    private int actualPage;
    private boolean first;
    private boolean last;
}