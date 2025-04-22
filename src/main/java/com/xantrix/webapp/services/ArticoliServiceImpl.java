package com.xantrix.webapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.xantrix.webapp.dtos.ArticoloDto;
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
	public List<ArticoloDto> SelAll()
	{
		return  ConvertToDto(articoliRepository.findAll());
	}

	@Override
	public ArticoloDto SelByCodArt(String codart)
	{
		return ConvertToDto(articoliRepository.findByCodArt(codart));
	}

	@Override
	public List<ArticoloDto> SelByDescrizione(String filter, int page, int numrec)
	{
		filter = "%".concat(filter.toUpperCase().concat("%"));
		
		Pageable pageAndRecords = PageRequest.of(page, numrec);
		
		return ConvertToDto(articoliRepository.findByDescrizioneLike(filter, pageAndRecords));
	}

	@Override
	public ArticoloDto SelByBarcode(String barcode)
	{
		return ConvertToDto(articoliRepository.selByEan(barcode));
	}
	
	private ArticoloDto ConvertToDto(Articoli articoli)
	{
		ArticoloDto articoloDto = null;
		
		if (articoli != null)
		{
			articoloDto =  modelMapper.map(articoli, ArticoloDto.class);
		}
		
		return articoloDto;
	}
	
	private List<ArticoloDto> ConvertToDto(List<Articoli> articoli)
	{		
		List<ArticoloDto> articoloDto = articoli
		        .stream()
		        .map(source -> modelMapper.map(source, ArticoloDto.class))
		        .collect(Collectors.toList());
		
		return articoloDto;
	}
	 

}
