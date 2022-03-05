package com.example.projectTestDemo.repository;

import com.example.projectTestDemo.entity.ManagePeopleVat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagePeopleVatRepository extends JpaRepository<ManagePeopleVat, Integer> {

    ManagePeopleVat findByManagePeopleTaxId(String managePeopleTaxId);
}
