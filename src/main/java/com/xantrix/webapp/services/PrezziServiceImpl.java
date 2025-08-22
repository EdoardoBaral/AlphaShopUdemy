package com.xantrix.webapp.services;

import com.xantrix.webapp.entities.DettListini;
import com.xantrix.webapp.repository.PrezziRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
 
@Service
@RequiredArgsConstructor
public class PrezziServiceImpl implements PrezziService {
	
	private final PrezziRepository prezziRepository;
	
	@Override
	public double selPrezzoArt(String CodArt, String IdList) {
		DettListini dettListini = prezziRepository.selByCodArtAndList(CodArt, IdList);
		
		if (dettListini != null) {
			return dettListini.getPrezzo();
		} else {
			return 0;
		}
	}
}
