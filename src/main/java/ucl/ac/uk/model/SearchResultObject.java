package ucl.ac.uk.model;

import java.util.List;
import java.util.ArrayList;

//Object for storing the search results, this is linked to the Item class
public class SearchResultObject {
    private final String keyword;
    private final List<String> matchingListNames;
    private final List<Item> matchingItems;
    public SearchResultObject(String keyword) {
        this.keyword = keyword;
        this.matchingListNames = new ArrayList<>();
        this.matchingItems = new ArrayList<>();
    }
    public void setMatchingListNames(List<String> matchingListNames) {
        this.matchingListNames.addAll(matchingListNames);
    }
    public void setMatchingItems(List<Item> matchingItems) {
        this.matchingItems.addAll(matchingItems);
    }
    public String getKeyword() {
        return keyword;
    }
    public List<String> getMatchingListNames() {
        return matchingListNames;
    }
    public List<Item> getMatchingItems() {
        return matchingItems;
    }
}