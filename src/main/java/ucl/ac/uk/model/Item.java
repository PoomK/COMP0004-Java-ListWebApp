package ucl.ac.uk.model;

//This is the class Item for the SearchResultObject
public class Item {
    private final String itemType;
    private final String itemValue;
    private final String listName;

    public Item(String itemType, String itemValue, String listName) {
        this.itemType = itemType;
        this.itemValue = itemValue;
        this.listName = listName;
    }
    public String getItemType() {
        return itemType;
    }
    public String getItemValue() {
        return itemValue;
    }
    public String getListName() {
        return listName;
    }
}