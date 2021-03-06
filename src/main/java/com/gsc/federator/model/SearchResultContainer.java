package com.gsc.federator.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: msaccotelli
 * Date: 10/10/2014
 */
public class SearchResultContainer implements ModelObject {
    private static final Logger logger = LoggerFactory.getLogger(SearchResultContainer.class);

    private final List<SearchResult> searchResults;

    private final String query;

    public SearchResultContainer(final String query) {
        this.searchResults = new ArrayList<>();
        this.query = query;
    }

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    /**
     * Adds a searchResult to the list of SearchResults, avoiding duplicate titles
     *
     * @param searchResult
     */
    public void addSearchResult(final SearchResult searchResult) {
        if (searchResult.getTitle() == null || searchResult.getTitle().length() == 0) {
            return;
        }

        // TODO msaccotelli : handle duplicates *after* removing mailing list tags

        for (final SearchResult searchResultExisting : searchResults) {
            if (searchResultExisting.getTitle().equalsIgnoreCase(searchResult.getTitle())) {
                return;
            }
        }

        logger.info("Got Result {}", searchResult);

        searchResults.add(searchResult);
    }
}
