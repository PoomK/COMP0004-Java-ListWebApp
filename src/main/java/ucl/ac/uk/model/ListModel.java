package ucl.ac.uk.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListModel {
    //File paths to the json files
    private static final String LISTS_JSON_PATH = "/WEB-INF/lists.json";
    private static final String MULTI_ELEMENT_JSON_PATH = "/WEB-INF/multiElementItem.json";
    //Function to read from lists.json file
    public static List<ListObject> readListsFromJsonFile(ServletContext context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(context.getRealPath(LISTS_JSON_PATH));
        if (file.exists() && file.length() > 0) {
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, ListObject.class));
        } else {
            return new ArrayList<>();
        }
    }
    //Function to write to lists.json file
    public static void writeListsToJsonFile(List<ListObject> lists, ServletContext context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(context.getRealPath(LISTS_JSON_PATH));
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, lists);
    }
    //Function to read from multiElementItem.json file
    public static List<MultiElementItemObject> readMultiElemListFromJson(ServletContext context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(context.getRealPath(MULTI_ELEMENT_JSON_PATH));
        if (file.exists() && file.length() > 0) {
            List<MultiElementItemObject> multiObjects = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, MultiElementItemObject.class));
            return multiObjects;
        } else {
            return new ArrayList<>();
        }
    }
    //Function to write to multiElementItem.json file
    public static void writeMultiElemItemsToJson(List<MultiElementItemObject> lists, ServletContext context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(context.getRealPath(MULTI_ELEMENT_JSON_PATH));
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, lists);
    }
    //Function to change the list name of any multi element item with the old list name to the new list name
    public static void changeMultiElemListName(String oldListName, String newListName, ServletContext context) throws IOException {
        List<MultiElementItemObject> items = readMultiElemListFromJson(context);
        for (MultiElementItemObject item : items) {
            if (item.getListName().equals(oldListName)) {
                item.setListName(newListName);
            }
        }
        ListModel.writeMultiElemItemsToJson(items, context);
    }
    //Function to change linked list names when changing a list name
    public static List<ListObject> changeLinkedListItemValue(List<ListObject> listObjects, String oldListName, String newListName, ServletContext context) throws IOException {
        for (ListObject listObject : listObjects) {
            List<ItemObject> items = listObject.getItems();
            for (ItemObject item : items) {
                if (item.getItemType().equals("linkedList") && item.getItemValue().equals(oldListName)) {
                    item.setItemValue(newListName);
                }
            }
        }
        return listObjects;
    }
    //Function to search for matching list names and items that contain the keyword
    public static SearchResultObject performSearch(String keyword, List<ListObject> lists, List<MultiElementItemObject> multiElemList) {
        SearchResultObject searchResult = new SearchResultObject(keyword);
        // Search for matching list names
        List<String> matchingListNames = new ArrayList<>();
        for (ListObject list : lists) {
            if (list.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingListNames.add(list.getName());
            }
        }
        // Search for matching items in lists
        List<Item> matchingItems = new ArrayList<>();
        for (ListObject list : lists) {
            for (ItemObject item : list.getItems()) {
                if (item.getItemValue().toLowerCase().contains(keyword.toLowerCase())) {
                    // Add itemType, itemValue, listName
                    matchingItems.add(new Item(item.getItemType(), item.getItemValue(), list.getName()));
                }
            }
        }
        //Search for matching items in multiElementItem.json
        for (MultiElementItemObject multiItem : multiElemList) {
            for (ItemObject item : multiItem.getItems()) {
                if (item.getItemValue().toLowerCase().contains(keyword.toLowerCase())) {
                    matchingItems.add(new Item(item.getItemType(), item.getItemValue(), multiItem.getListName()));
                }
            }
        }
        //Add results to searchResult
        searchResult.setMatchingListNames(matchingListNames);
        searchResult.setMatchingItems(matchingItems);
        return searchResult;
    }
}
