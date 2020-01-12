package com.demo.gateways.repository;

import com.demo.gateways.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    @Query(value = "from Gateway where serialNumber = :serialNumber and (:id is null or id <> :id)")
    List<Gateway> findAllBySerialNumber(@Param("serialNumber") String serialNumber, @Param("id") Long id);
}
