/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.common.model;

import io.qio.qa.lib.common.Links;
import org.codehaus.jackson.annotate.JsonProperty;
import org.apache.log4j.Logger;

public class CollectionListResponseStyleB {
    @JsonProperty("page")
    private Page page;

    @JsonProperty("_embedded")
    private String _embedded;

    @JsonProperty("_links")
    private Links _links;

    public CollectionListResponseStyleB() {
    }

    //@SuppressWarnings("serial")

    public String get_embedded() {
        return _embedded;
    }

    public void set_embedded(String _embedded) {
        this._embedded = _embedded;
    }

    public Links get_links() {
            return _links;
        }

    public void set_links(Links _links) {
            this._links = _links;
        }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
