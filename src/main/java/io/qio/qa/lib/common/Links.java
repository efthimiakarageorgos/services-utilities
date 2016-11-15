/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.common;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

import java.lang.reflect.Field;

public class Links {
	@JsonProperty("first")
	private FirstPageLink firstPageLink;

	@JsonProperty("last")
	private LastPageLink lastPageLink;

	@JsonProperty("next")
	private NextPageLink nextPageLink;

	@JsonProperty("profile")
	private ProfileLink profileLink;

	@JsonProperty("self")
	private SelfLink selfLink;

	@JsonProperty("xxx")
	private XXXLink xxxLink;

	public SelfLink getSelfLink() {
		return selfLink;
	}

	public void setSelfLink(SelfLink self) {
		this.selfLink = self;
	}

	public FirstPageLink getFirstPageLink() {
		return firstPageLink;
	}

	public void setFirstPageLink(FirstPageLink firstPageLink) {
		this.firstPageLink = firstPageLink;
	}

	public LastPageLink getLastPageLink() {
		return lastPageLink;
	}

	public void setLastPageLink(LastPageLink lastPageLink) {
		this.lastPageLink = lastPageLink;
	}

	public NextPageLink getNextPageLink() {
		return nextPageLink;
	}

	public void setNextPageLink(NextPageLink nextPageLink) {
		this.nextPageLink = nextPageLink;
	}

	public ProfileLink getProfileLink() {
		return profileLink;
	}

	public void setProfileLink(ProfileLink profileLink) {
		this.profileLink = profileLink;
	}

	public XXXLink getXxxLink() {
		return xxxLink;
	}

	public void setXxxLink(XXXLink xxxLink) {
		this.xxxLink = xxxLink;
	}

	@Override
	public boolean equals(Object responseObj) {
		Logger logger = Logger.getRootLogger();
		Boolean equalityCheckFlag = true;
		try {
			if (!(responseObj instanceof Links) || responseObj == null)
				return false;

			Field[] fields = Links.class.getDeclaredFields();
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

	/**
	 * TODO: Here we just need to check that the format of url is correct, i.e.
	 * it should be like https://<>/<>/ etc
	 */

	public class HrefLinks {
		@JsonProperty("href")
		String href;

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

		/**
		 * In addition to just checking the equality of the urls, this method
		 * will also check if they are valid or not.
		 */
		@Override
		public boolean equals(Object responseObj) {
			Logger logger = Logger.getRootLogger();
			Boolean equalityCheckFlag = true;
			int idx = 0;

			if (!(responseObj instanceof HrefLinks) || responseObj == null)
				return false;

			String requestHref = this.getHref();
			idx = requestHref.indexOf("?");

			String requestHrefForValidation = requestHref;
			if (idx > 0) {
				requestHrefForValidation = requestHref.substring(0, idx - 1);
			}

			String responseHref = ((HrefLinks) responseObj).getHref();
			idx = responseHref.indexOf("?");

			String responseHrefForValidation = responseHref;
			if (idx > 0) {
				responseHrefForValidation = responseHref.substring(0, idx - 1);
			}

			if (requestHref != null)
				if (!isURLCorrectlyFormatted(requestHrefForValidation))
					return false;

			if (responseHref != null)
				if (!isURLCorrectlyFormatted(responseHrefForValidation))
					return false;

			if (requestHref != null)
				if (!requestHrefForValidation.equals(responseHrefForValidation)) {
					equalityCheckFlag = false;
					logger.error("Class Name: " + this.getClass().getName()
							+ " --> Match failed on property: href, Request Value: " + requestHref
							+ ", Response Value: " + responseHref);
				}
			return equalityCheckFlag;
		}

		/**
		 * This method checks the format of the input URL to be similar to the
		 * following: http://assets-new-qa.qiotec.internal/assettypes/
		 * 5712fea6b27caa4cfb0a7253/attributes/5712fea6b27caa4cfb0a7257 In
		 * addition, it also checks of there are no double slashes (i.e. //) in
		 * the url. The corresponding regex can be extended to include further
		 * valdations.
		 */
		public boolean isURLCorrectlyFormatted(String inputURL) {
			Logger logger = Logger.getRootLogger();
			Boolean urlFormatCheckerFlag = true;
			String urlFormatCheckerRegex = "https?:\\/\\/[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,10}(\\/[-0-9a-z]{2,256})*";

			if (!inputURL.matches(urlFormatCheckerRegex)) {
				urlFormatCheckerFlag = false;
				logger.error("Incorrectly formatted URL: " + inputURL);
			}
			return urlFormatCheckerFlag;
		}

		/**
		 * TODO: Here we just need to check that the format of url is correct,
		 * i.e. it should be like https://<>/<>/ etc
		 */
	}

	public class SelfLink extends HrefLinks {
	}

	public class FirstPageLink extends HrefLinks {
	}

	public class LastPageLink extends HrefLinks {
	}

	public class NextPageLink extends HrefLinks {
	}

	public class ProfileLink extends HrefLinks {
	}

	public class XXXLink extends HrefLinks {
	}
}
