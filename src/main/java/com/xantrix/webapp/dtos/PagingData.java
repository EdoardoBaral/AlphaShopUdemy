package com.xantrix.webapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagingData {
	
	private int pageNum;
	private boolean selected;
}
