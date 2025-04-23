package com.xantrix.webapp.dtos;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class PagingData {
	
	private int pageNum;
	private boolean selected;
}
