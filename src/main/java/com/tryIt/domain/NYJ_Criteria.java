package com.tryIt.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
public class NYJ_Criteria {

    private int pageNum;
    private int amount;

    private String type;
    private String keyword;

    public NYJ_Criteria(){
        this(1,10);
    }

    public NYJ_Criteria(int pageNum, int amount){
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String[] getTypeArr(){
        return type == null? new String[] {}: type.split("");
    }
}
