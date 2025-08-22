package com.xantrix.webapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PagingData {
	
	private int PageNum;
	private boolean IsSelected;
}
