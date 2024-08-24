package com.makersharks.suppliers.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.makersharks.suppliers.entity.Supplier;
import com.makersharks.suppliers.entity.enums.ManufacturingProcess;
import com.makersharks.suppliers.entity.enums.NatureOfBusiness;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT s FROM Supplier s WHERE s.location = :location AND s.natureOfBusiness = :natureOfBusiness AND :manufacturingProcess MEMBER OF s.manufacturingProcesses")
    org.springframework.data.domain.Page<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcesses(
        @Param("location") String location,
        @Param("natureOfBusiness") NatureOfBusiness natureOfBusiness,
        @Param("manufacturingProcess") ManufacturingProcess manufacturingProcess,
        Pageable pageable
    );
}

