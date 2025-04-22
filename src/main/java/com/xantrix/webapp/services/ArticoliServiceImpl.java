package com.xantrix.webapp.services;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.xantrix.webapp.dtos.ArticoloDto;
import com.xantrix.webapp.entities.Articoli;
import com.xantrix.webapp.repository.ArticoliRepository;

@Service
@RequiredArgsConstructor
public class ArticoliServiceImpl implements ArticoliService {
	
	private final ArticoliRepository articoliRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public List<ArticoloDto> selectAll() {
		return convertToDto(articoliRepository.findAll());
	}

	@Override
	public ArticoloDto selectByCodArt(String codart) {
		return convertToDto(articoliRepository.findByCodArt(codart));
	}

	@Override
	public List<ArticoloDto> selectByDescrizione(String filter, int page, int numrec) {
		filter = "%".concat(filter.toUpperCase().concat("%"));
		Pageable pageAndRecords = PageRequest.of(page, numrec);
		
		return convertToDto(articoliRepository.findByDescrizioneLike(filter, pageAndRecords));
	}

	@Override
	public ArticoloDto selectByBarcode(String barcode) {
		return convertToDto(articoliRepository.selectByEuropeanArticleNumber(barcode));
	}
	
	private ArticoloDto convertToDto(Articoli articoli) {
		ArticoloDto articoloDto = null;
		if (articoli != null) {
			articoloDto =  modelMapper.map(articoli, ArticoloDto.class);
		}
		
		return articoloDto;
	}
	
	private List<ArticoloDto> convertToDto(List<Articoli> articoli) {
		List<ArticoloDto> articoloDto = articoli.stream()
		        								.map(source -> modelMapper.map(source, ArticoloDto.class))
		        								.collect(Collectors.toList());
		return articoloDto;
	}
}
