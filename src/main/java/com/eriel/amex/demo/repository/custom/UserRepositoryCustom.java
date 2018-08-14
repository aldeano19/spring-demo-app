package com.eriel.amex.demo.repository.custom;

import com.eriel.amex.demo.dto.MinimalUserInfoDto;

/**
 * This Interface will define complex queries that the JPA cannot handle.
 */
public interface UserRepositoryCustom {
    /**
     * A custom implementation of this query is required in order to limit the amount of data extracted from the user
     * by using adding a ProjectionOperation to the list of Criteria.
     *
     * @param accountNumber The unique account number to match.
     * @return Some information about the user: first name, last name, address
     */
    MinimalUserInfoDto getMinimalUserInfo(String accountNumber);
}
