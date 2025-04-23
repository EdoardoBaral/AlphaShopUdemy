package com.xantrix.webapp.config;

import com.xantrix.webapp.dtos.ArticoloDto;
import com.xantrix.webapp.dtos.BarCodeDto;
import com.xantrix.webapp.entities.Articolo;
import com.xantrix.webapp.entities.BarCode;
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
        modelMapper.addMappings(new PropertyMap<BarCode, BarCodeDto>() {
			
            @Override
            protected void configure() {
				map().setIdTipoArt(source.getIdTipoArt());
            }
        });

        modelMapper.addConverter(articoliConverter);

        return modelMapper;
    }
	
	PropertyMap<Articolo, ArticoloDto> articoliMapping = new PropertyMap<>() {
	 
		protected void configure() {
			map().setData(source.getDataCreazione());
			map().setStatus(source.getIdStatoArt());
		}
	};
	
	Converter<String, String> articoliConverter = context -> context.getSource() == null ? "" : context.getSource().trim();
}