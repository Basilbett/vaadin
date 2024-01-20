package com.example.application.data;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface ContactRepository extends JpaRepository<Contact, Integer> {

 @Query("select c from Contact c " +
         "where LOWER(c.firstName) like LOWER(concat('%', :searchTerm , '%')) or " +
         "LOWER(c.lastName) like LOWER(concat('%',:searchTerm,'%'))")
 List<Contact> search (@Param("searchTerm") String searchTerm);




}
