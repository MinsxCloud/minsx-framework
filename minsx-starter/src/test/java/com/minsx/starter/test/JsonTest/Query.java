package com.minsx.starter.test.JsonTest;

public class Query {

    private String condition;

    private Query query;

    public Query(){}

    public Query(String condition, Query query) {
        this.condition = condition;
        this.query = query;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
