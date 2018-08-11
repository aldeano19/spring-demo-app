package com.eriel.amex.demo.repository.custom.impl;

import com.eriel.amex.demo.dto.MapAddress;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;
import com.eriel.amex.demo.model.User;
import com.eriel.amex.demo.repository.custom.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    static final String[] LIST_OF_FIELDS = {"firstName", "lastName", "address"};
    static final String ACCOUNT_NUMBER_KEY = "accountNumber";

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * A custom implementation of this query is required in order to limit the amount of data extracted from the user
     * by using adding a ProjectionOperation to the list of Criteria.
     *
     * @param accountNumber The unique account number to match.
     * @return Some information about the user: first name, last name, address
     */
    @Override
    public MinimalUserInfoDto getMinimalUserInfo(String accountNumber) {
        ProjectionOperation pop = Aggregation.project(LIST_OF_FIELDS);
        AggregationOperation match = Aggregation.match(Criteria.where(ACCOUNT_NUMBER_KEY).is(accountNumber));

        List<AggregationOperation> criteria = new ArrayList<>();
        criteria.add(match);
        criteria.add(pop);

        TypedAggregation<User> aggregation = Aggregation.newAggregation(User.class, criteria);
        AggregationResults<MinimalUserInfoDto> results = mongoTemplate.aggregate(aggregation, MinimalUserInfoDto.class);

        return results.getUniqueMappedResult();
    }

}
