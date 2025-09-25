package com.example.youeatieat.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Criteria {
    private int page;
    private int pageCount;
    private int startPage;
    private int endPage;
    private int rowCount;
    private int realEnd;
    private int offset;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean hasMore;
    private int count;
    private int total;

    public Criteria(int page, int total) {
        rowCount = 10;
        pageCount = 10;
        count = rowCount + 1;
        this.page = Math.max(1, page);
        this.total = Math.max(0, total);
        endPage = (int)(Math.ceil(page / (double)pageCount) * pageCount);
        startPage = endPage - pageCount + 1;
        realEnd = (int)(Math.ceil(total / (double)rowCount));
        endPage = Math.min(realEnd, endPage);
        endPage = Math.max(1, endPage);
        offset = (this.page - 1) * rowCount;
        hasNextPage = this.page < realEnd;;
        hasPreviousPage = this.page > 1;
    }
}
