package ucl.ac.uk.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ListObject {
    private String name;
    private List<ItemObject> items;
    @JsonCreator
    public ListObject(@JsonProperty("name") String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<ItemObject> getItems() {
        return items;
    }
}