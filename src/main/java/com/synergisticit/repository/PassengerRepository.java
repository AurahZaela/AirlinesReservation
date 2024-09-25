package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	@Query(value = "SELECT passengerId FROM passenger ORDER BY passengerId DESC LIMIT 1", nativeQuery = true)
    Long getNextSeriesId();
}
