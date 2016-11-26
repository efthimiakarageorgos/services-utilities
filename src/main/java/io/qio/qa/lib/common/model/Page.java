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

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Boolean getLast() {
		return last;
	}

	public void setLast(Boolean last) {
		this.last = last;
	}

	public Boolean getFirst() {
		return first;
	}

	public void setFirst(Boolean first) {
		this.first = first;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
}
