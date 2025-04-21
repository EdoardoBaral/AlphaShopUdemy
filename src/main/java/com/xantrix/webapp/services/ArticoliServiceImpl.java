package com.xantrix.webapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.xantrix.webapp.dtos.ArticoliDto;
import com.xantrix.webapp.entities.Articoli;
import com.xantrix.webapp.repository.ArticoliRepository;

@Service
public class ArticoliServiceImpl implements ArticoliService
{
	
	//@Autowired
	private ArticoliRepository articoliRepository;
	
	private ModelMapper modelMapper;
	
	public ArticoliServiceImpl(
			ArticoliRepository articoliRepository,
			ModelMapper modelMapper)
	{
		this.modelMapper = modelMapper;
		this.articoliRepository = articoliRepository;
	}
	
	@Override
	public List<ArticoliDto> SelAll() 
	{
		return  ConvertToDto(articoliRepository.findAll());
	}

	@Override
	public ArticoliDto SelByCodArt(String codart) 
	{
		return ConvertToDto(articoliRepository.findByCodArt(codart));
	}

	@Override
	public List<ArticoliDto> SelByDescrizione(String filter, int page, int numrec) 
	{
		filter = "%".concat(filter.toUpperCase().concat("%"));
		
		Pageable pageAndRecords = PageRequest.of(page, numrec);
		
		return ConvertToDto(articoliRepository.findByDescrizioneLike(filter, pageAndRecords));
	}

	@Override
	public ArticoliDto SelByBarcode(String barcode) 
	{
		return ConvertToDto(articoliRepository.selByEan(barcode));
	}
	
	private ArticoliDto ConvertToDto(Articoli articoli)
	{
		ArticoliDto articoliDto = null;
		
		if (articoli != null)
		{
			articoliDto =  modelMapper.map(articoli, ArticoliDto.class);
		}
		
		return articoliDto;
	}
	
	private List<ArticoliDto> ConvertToDto(List<Articoli> articoli)
	{		
		List<ArticoliDto> articoliDto = articoli
		        .stream()
		        .map(source -> modelMapper.map(source, ArticoliDto.class))
		        .collect(Collectors.toList());
		
		return articoliDto;
	}
	 

}
