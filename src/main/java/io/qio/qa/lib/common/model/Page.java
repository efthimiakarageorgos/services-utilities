/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.common.model;

public class Page {
	private int size;
	private int totalElements;
	private int totalPages;
	private int number;

	private Boolean last;
	private Boolean first;
	private int numberOfElements;

	public int getSize() {
		return size;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getNumber() {
		return number;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public Boolean getLast() {
		return last;
	}

	public Boolean getFirst() {
		return first;
	}
}
