package com.eriel.amex.demo.repository.custom;

import com.eriel.amex.demo.dto.MinimalUserInfoDto;

/**
 * This Interface will define complex queries that the JPA cannot handle.
 */
public interface UserRepositoryCustom {
    MinimalUserInfoDto getMinimalUserInfo(String accountNumber);
}
