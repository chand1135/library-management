package com.atik.librarymanagement.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class Copy {

	private String barcode;
	private String status;

}
