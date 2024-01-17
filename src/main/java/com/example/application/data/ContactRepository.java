package com.example.application.data;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface ContactRepository extends JpaRepository<Contact, Integer> {

 @Query("select c from Contact c" +
         "where lower (c.firstName)like lower (contact('%', :searchTerm , '%'))" +
         "or lower(c.lastName)like lower (contact('%',:searchTerm,'%'))")
 List<Contact> search (@Param("searchTerm") String searchTerm);




}
