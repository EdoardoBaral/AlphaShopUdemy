package com.xantrix.webapp.services;

import com.xantrix.webapp.dtos.ArticoliDto;
import com.xantrix.webapp.entities.Articoli;
import com.xantrix.webapp.repository.ArticoliRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticoliServiceImpl implements ArticoliService {
	
	private final ArticoliRepository articoliRepository;
	private final PrezziService prezziService;
	private final ModelMapper modelMapper;
	
	@Override
	public List<ArticoliDto> selAll() {
		return this.convertToDto(articoliRepository.findAll());
	}

	@Override
	public ArticoliDto selByCodArt(String codart) {
		return this.convertToDto(articoliRepository.findByCodArt(codart));
	}

	@Override
	public List<ArticoliDto> selByDescrizione(String filter, int page, int numrec) {
		filter = "%".concat(filter.toUpperCase().concat("%"));
		Pageable pageAndRecords = PageRequest.of(page, numrec);
		
		return this.convertToDto(articoliRepository.findByDescrizioneLike(filter, pageAndRecords));
	}

	@Override
	public ArticoliDto selByBarcode(String barcode) {
		return this.convertToDto(articoliRepository.selByEan(barcode));
	}
	
	@Override
	public int numRecords(String filter) {
		filter = "%".concat(filter.toUpperCase().concat("%"));
		return articoliRepository.countRecords(filter);
	}
	
	private ArticoliDto convertToDto(Articoli articoli) {
		ArticoliDto articoliDto = null;
		
		if (articoli != null) {
			articoliDto =  modelMapper.map(articoli, ArticoliDto.class);
			articoliDto.setPrezzo(prezziService.selPrezzoArt(articoliDto.getCodart(), "1"));
		}
		
		return articoliDto;
	}
	
	private List<ArticoliDto> convertToDto(List<Articoli> articoli) {
		List<ArticoliDto> articoliDto = articoli.stream()
		        								.map(source -> modelMapper.map(source, ArticoliDto.class))
		        								.toList();
		articoliDto.forEach(e -> e.setPrezzo(prezziService.selPrezzoArt(e.getCodart(),"1")));
		
		return articoliDto;
	}
}
