package com.xantrix.webapp.repository;

import com.xantrix.webapp.entities.Articolo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticoliRepository extends JpaRepository<Articolo, String> {
	
	Articolo findByCodArt(String codArt);
	
	List<Articolo> findByDescrizioneLike(String descrizione, Pageable pageable);
	
	List<Articolo> findByCodStatOrderByDescrizione(String codStat);
	
	@Query(value="select a.* from ARTICOLI a join BARCODE b on (a.CODART = b.CODART) where b.BARCODE = :europeanArticleNumber)", nativeQuery = true)
	Articolo selectByEuropeanArticleNumber(@Param("europeanArticleNumber") String europeanArticleNumber);
	
	@Query(value = "SELECT COUNT(*) FROM ARTICOLI WHERE DESCRIZIONE LIKE :descArt", nativeQuery = true)
	int countRecords(@Param("descArt") String descrizione);
}