package com.xantrix.webapp.config;

import com.xantrix.webapp.dtos.ArticoloDto;
import com.xantrix.webapp.dtos.BarCodeDto;
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
        modelMapper.addMappings(new PropertyMap<Barcode, BarCodeDto>() {
			
            @Override
            protected void configure() {
				map().setIdTipoArt(source.getIdTipoArt());
            }
        });

        modelMapper.addConverter(articoliConverter);

        return modelMapper;
    }
	
	PropertyMap<Articoli, ArticoloDto> articoliMapping = new PropertyMap<>() {
	 
		protected void configure() {
			map().setData(source.getDataCreaz());
			map().setStatus(source.getIdStatoArt());
		}
	};
	
	Converter<String, String> articoliConverter = context -> context.getSource() == null ? "" : context.getSource().trim();
}