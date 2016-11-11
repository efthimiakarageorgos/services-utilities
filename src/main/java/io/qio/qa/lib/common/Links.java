/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.common;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

import java.lang.reflect.Field;

public class Links {
	@JsonProperty("self")
	private SelfLink selfLink;

	public SelfLink getSelfLink() {
		return selfLink;
	}

	public void setSelfLink(SelfLink self) {
		this.selfLink = self;
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

			if (!(responseObj instanceof HrefLinks) || responseObj == null)
				return false;

			String requestHref = this.getHref();
			String responseHref = ((HrefLinks) responseObj).getHref();

			if (requestHref != null)
				if (!isURLCorrectlyFormatted(requestHref))
					return false;

			if (responseHref != null)
				if (!isURLCorrectlyFormatted(responseHref))
					return false;

			if (requestHref != null)
				if (!requestHref.equals(responseHref)) {
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
}
