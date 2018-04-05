/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package com.hbc.qa.lib.idm.model.organizations;

import com.hbc.qa.lib.common.Links;
import com.hbc.qa.lib.idm.model.common.Address;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;
import java.lang.reflect.Field;

public class OrganizationIDM {
	private String name;
	private Address address;
	private String email;
	private String phoneNumber;
	private String faxNumber;
	private String logo;

	// returned in the response of a POST request
//	@JsonProperty("organizationId")
//	private String organizationId;

	@JsonProperty("_links")
	private Links _links;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

//	public String getOrganizationId() {
//		return organizationId;
//	}
//
//	public void setOrganizationId(String organizationId) {
//		this.organizationId = organizationId;
//	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}

	public OrganizationIDM() {
	}

	@SuppressWarnings("serial")
	public OrganizationIDM(String timeStamp) {
		Address address = new Address(timeStamp);
		this.name = "ORG " + timeStamp;
		this.address = address;
		this.logo = "LOGO " + timeStamp;
		this.email = "email@logo.com";
	}

	public OrganizationIDM(String name, Address address, String phoneNumber, String faxNumber, String email, String logo) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.logo = logo;
	}

	// TODO:
	/*
	 * If two objects do not match, then its simply going to print out their
	 * string representations in the logger message. I need to figure out a
	 * better way for this.
	 */
	@Override
	public boolean equals(Object responseObj) {
		Logger logger = Logger.getRootLogger();
		Boolean equalityCheckFlag = true;
		try {
			if (!(responseObj instanceof OrganizationIDM) || responseObj == null)
				return false;

			Field[] fields = OrganizationIDM.class.getDeclaredFields();
			for (Field field : fields) {
				Object requestVal = field.get(this);
				Object responseVal = field.get(responseObj);
				if (requestVal != null)
					if (!requestVal.equals(responseVal)) {
						equalityCheckFlag = false;
						logger.error("Class Name: " + this.getClass().getName() + " --> Match failed on property: "
								+ field.getName() + ", Request Value: " + requestVal + ", Response Value: "
								+ responseVal);
						break;
					}
			}
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		}
		return equalityCheckFlag;
	}
}