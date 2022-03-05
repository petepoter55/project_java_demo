package com.example.projectTestDemo.repository;

import com.example.projectTestDemo.entity.MangePeopleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagePeopleDetailRepository extends JpaRepository<MangePeopleDetail, Integer> {
    MangePeopleDetail findByRefNo(String refNo);

    MangePeopleDetail findById(int id);

    @Query("SELECT m FROM MangePeopleDetail m WHERE m.managePeopleTaxId LIKE %:managePeopleTaxId%")
    List<MangePeopleDetail> searchByManagePeopleTaxIdLike(@Param("managePeopleTaxId") String managePeopleTaxId);
}
