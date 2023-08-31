package com.example.demo.repo;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

	@Query(value = "SELECT o.* FROM table_offer o INNER JOIN table_suboffer s ON o.offer_id = s.offer_id WHERE o.activation_date <= :currentDate AND o.expiration_date >= :currentDate", nativeQuery = true)
	List<Offer> findValidOffersNative(@Param("currentDate") LocalDate currentDate);


}
