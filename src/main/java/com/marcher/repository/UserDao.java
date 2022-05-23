package com.marcher.repository;

import com.marcher.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("userDao")
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findAllByOrderByLastName();

    @Query("from User where firstName = :firstName and lastName = :lastName")
    Optional<User> findUserByFirstNameAndLastName(@Param("firstName")String firstName, @Param("lastName")String lastName);
}
