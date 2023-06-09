package com.example.banking.entities.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Pagination {
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("total_pages")
    private Long totalPages;
    @JsonProperty("total_counts")
    private Long totalCounts;

    public Pagination() {
        this(1, 15, 0L, 0L);
    }

    public Long getTotalPages() {
        return (long) Math.ceil((double) this.totalCounts / size);
    }

    public Integer getOffSet(){
        return  this.page*this.size;
    }

    public Integer getIndexPageable () {
        return this.page -1;
    }

}
