/**
 * © HBC Shared Services QA 2018. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF HBC.
 */
package com.hbc.qa.lib.common.model;

import com.hbc.qa.lib.common.Links;
import org.json.simple.JSONObject;
import org.codehaus.jackson.annotate.JsonProperty;

public class CollectionListResponseStyleB {
    @JsonProperty("page")
    private Page page;

    @JsonProperty("_embedded")
    private JSONObject _embedded;

    @JsonProperty("_links")
    private Links _links;

    public CollectionListResponseStyleB() {
    }

    //@SuppressWarnings("serial")

    public JSONObject get_embedded() {
        return _embedded;
    }

    public void set_embedded(JSONObject _embedded) {
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
