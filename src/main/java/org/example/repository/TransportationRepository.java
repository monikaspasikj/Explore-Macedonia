package org.example.repository;

import org.example.model.RoutesAndTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportationRepository extends JpaRepository<RoutesAndTransport.Transportation, Long> {
    List<RoutesAndTransport.Transportation> findAllBy();
    List<RoutesAndTransport.Transportation> findByTransportationMode(String transportationMode);
}

