package com.xantrix.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.xantrix.webapp.entities.Articoli;

import jakarta.transaction.Transactional;

public interface ArticoliRepository extends JpaRepository<Articoli, String> 
{
	//Query Method
	Articoli findByCodArt(String codArt);
	
	//Query Method
	List<Articoli> findByDescrizioneLike(String descrizione, Pageable pageable);
	
	//Query Method
	List<Articoli> findByCodStatOrderByDescrizione(String codStat);
	
	//JPQL
	@Query(value="SELECT a FROM Articoli a JOIN a.barcode b WHERE b.barcode IN (:ean)")
	Articoli selByEan(@Param("ean") String ean);
	
	//SQL
	@Query(value = "SELECT COUNT(*) FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
	int countRecords(@Param("desArt") String descrizione);	
	
}
