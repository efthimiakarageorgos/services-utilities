/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.common;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import java.lang.reflect.Field;

public class Links {
	//Style 1 page links
	@JsonProperty("firstPage")
	private FirstPageLink firstPage;

	@JsonProperty("lastPage")
	private LastPageLink lastPage;

	@JsonProperty("nextPage")
	private NextPageLink nextPage;

	@JsonProperty("previousPage")
	private PreviousPageLink previousPage;

	//Style 2 page links
	@JsonProperty("first")
	private FirstPageLink first;

	@JsonProperty("last")
	private LastPageLink last;

	@JsonProperty("next")
	private NextPageLink next;

	@JsonProperty("previous")
	private PreviousPageLink previous;

	@JsonProperty("profile")
	private ProfileLink profileLink;

	@JsonProperty("self")
	private SelfLink selfLink;

	public Links () {}

	public Links(FirstPageLink firstPage, LastPageLink lastPage, NextPageLink nextPage, PreviousPageLink previousPage, FirstPageLink first, LastPageLink last, NextPageLink next, PreviousPageLink previous, ProfileLink profileLink, SelfLink selfLink) {
		this.firstPage = firstPage;
		this.lastPage = lastPage;
		this.nextPage = nextPage;
		this.previousPage = previousPage;
		this.first = first;
		this.last = last;
		this.next = next;
		this.previous = previous;
		this.profileLink = profileLink;
		this.selfLink = selfLink;
	}

	public FirstPageLink getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(FirstPageLink firstPage) {
		this.firstPage = firstPage;
	}

	public LastPageLink getLastPage() {
		return lastPage;
	}

	public void setLastPage(LastPageLink lastPage) {
		this.lastPage = lastPage;
	}

	public NextPageLink getNextPage() {
		return nextPage;
	}

	public void setNextPage(NextPageLink nextPage) {
		this.nextPage = nextPage;
	}

	public PreviousPageLink getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(PreviousPageLink previousPage) {
		this.previousPage = previousPage;
	}

	public FirstPageLink getFirst() {
		return first;
	}

	public void setFirst(FirstPageLink first) {
		this.first = first;
	}

	public LastPageLink getLast() {
		return last;
	}

	public void setLast(LastPageLink last) {
		this.last = last;
	}

	public NextPageLink getNext() {
		return next;
	}

	public void setNext(NextPageLink next) {
		this.next = next;
	}

	public PreviousPageLink getPrevious() {
		return previous;
	}

	public void setPrevious(PreviousPageLink previous) {
		this.previous = previous;
	}

	public SelfLink getSelfLink() {
		return selfLink;
	}

	public void setSelfLink(SelfLink self) {
		this.selfLink = self;
	}

	public ProfileLink getProfileLink() {
		return profileLink;
	}

	public void setProfileLink(ProfileLink profileLink) {
		this.profileLink = profileLink;
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

	public class HrefLinks {
		@JsonProperty("href")
		String href;

		public HrefLinks() {
		}

		public HrefLinks(String href) {
			this.href = href;
		}

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

			//REVIEW REQUIRED: Need to change isURLCorrectlyFormatted method to look for ?xxx=zzz&yyy=wwww at the end of the href path
			//For now I am expluding them
			//TODO
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

			if (requestHref != null) {
				logger.info("Checking HREF");
				if (!isURLCorrectlyFormatted(requestHrefForValidation))
					return false;
			}

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
	}

	public class SelfLink extends HrefLinks {
	}

	public class FirstPageLink extends HrefLinks {
	}

	public class LastPageLink extends HrefLinks {
	}

	public class PreviousPageLink extends HrefLinks {
	}

	public class NextPageLink extends HrefLinks {
	}

	public class ProfileLink extends HrefLinks {
	}
}
