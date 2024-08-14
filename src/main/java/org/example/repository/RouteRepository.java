package org.example.repository;

import org.example.model.RoutesAndTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<RoutesAndTransport.Route, Long> {
    List<RoutesAndTransport.Route> findAllBy();
    List<RoutesAndTransport.Route> findByNameIn(List<String> names);
    List<RoutesAndTransport.Route> findByLocation(String location);
}
