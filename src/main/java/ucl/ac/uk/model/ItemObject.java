package ucl.ac.uk.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemObject {
    private String itemType;
    private String itemValue;
    @JsonCreator
    public ItemObject(@JsonProperty("type") String itemType,
                      @JsonProperty("value") String itemValue) {
        this.itemType = itemType;
        this.itemValue = itemValue;
    }
    public String getItemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public String getItemValue() {
        return itemValue;
    }
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
