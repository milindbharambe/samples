package com.milind.sample.oauth.repository.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.milind.sample.oauth.domain.User;
import com.milind.sample.oauth.repository.CustomUserRepository;

@Repository
public class UserRepositoryImpl implements CustomUserRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("username").is(username));
            List<User> users = mongoTemplate.find(query, User.class);
            if (users != null && !users.isEmpty()) {
                user = users.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
