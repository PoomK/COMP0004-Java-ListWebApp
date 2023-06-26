package ucl.ac.uk.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MultiElementItemObject {
    private String name;
    private String listName;
    private List<ItemObject> items;
    @JsonCreator
    public MultiElementItemObject(@JsonProperty("name") String name, @JsonProperty("listName") String listName) {
        this.name = name;
        this.listName = listName;
        this.items = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getListName() {return listName;}
    public void setListName(String listName) {this.listName = listName;}
    public List<ItemObject> getItems() {
        return items;
    }
}
