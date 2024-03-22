package com.coussy.reference.findwork.data.fetch.dto;

import java.util.List;

public class Dto {

    int count;
    String next;

//    String previous;
//

    List<SubDto> results;

    public List<SubDto> getResults() {
        return results;
    }

    public void setResults(List<SubDto> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Dto{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", results=" + results +
                '}';
    }
}
