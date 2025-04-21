package com.xantrix.webapp.config;

import com.xantrix.webapp.dtos.ArticoliDto;
import com.xantrix.webapp.dtos.BarcodeDto;
import com.xantrix.webapp.entities.Articoli;
import com.xantrix.webapp.entities.Barcode;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.addMappings(articoliMapping);
        modelMapper.addMappings(new PropertyMap<Barcode, BarcodeDto>() {
			
            @Override
            protected void configure() {
				map().setIdTipoArt(source.getIdTipoArt());
            }
        });

        modelMapper.addConverter(articoliConverter);

        return modelMapper;
    }
	
	PropertyMap<Articoli, ArticoliDto> articoliMapping = new PropertyMap<>() {
	 
		protected void configure() {
			map().setData(source.getDataCreaz());
			map().setStatus(source.getIdStatoArt());
		}
	};
	
	Converter<String, String> articoliConverter = context -> context.getSource() == null ? "" : context.getSource().trim();
}