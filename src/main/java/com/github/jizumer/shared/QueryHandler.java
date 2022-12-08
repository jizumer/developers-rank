package com.github.jizumer.shared;


public interface QueryHandler<QueryType extends Query> {
    Class<QueryType> subscribedTo();

    void respond(QueryType event);
}
