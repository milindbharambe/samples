/**
 * 
 */
package com.milind.sample.oauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.milind.sample.oauth.domain.User;


/**
 * @author bharamil
 *
 */
public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {

}
