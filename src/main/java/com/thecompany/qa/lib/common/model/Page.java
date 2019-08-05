/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.thecompany.qa.lib.common.model;

public class Page {
	private Long size;
	private Long totalElements;
	private Long totalPages;
	private Long number;
	private Long numberOfElements;

	private Boolean last;
	private Boolean first;
	//private Links _links;

	public Page() {
	}

	public Page(Long size, Long totalElements, Long totalPages, Long number, Long numberOfElements, Boolean last, Boolean first) {
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.number = number;
		this.numberOfElements = numberOfElements;
		this.last = last;
		this.first = first;
		//this._links = _links;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Long numberOfElements) {
		this.numberOfElements = numberOfElements;
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
}