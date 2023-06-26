package ucl.ac.uk.servlets;

import ucl.ac.uk.model.*;
import ucl.ac.uk.model.ListObject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editItem")
public class EditItemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Read lists from json file
        List<ListObject> lists = ListModel.readListsFromJsonFile(getServletContext());

        request.setAttribute("lists", lists);
        request.getRequestDispatcher("/editItem.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listName = request.getParameter("listName");
        int itemPosition = Integer.parseInt(request.getParameter("itemPosition"));
        String itemType = request.getParameter("itemType");
        String itemValue = request.getParameter("itemValue");
        // Read lists from json file
        List<ListObject> lists = ListModel.readListsFromJsonFile(getServletContext());
        // Find the ListObject corresponding to the listName parameter
        ListObject selectedList = null;
        for (ListObject list : lists) {
            if (list.getName().equals(listName)) {
                selectedList = list;
                break;
            }
        }
        // Edit item at position itemPosition
        if (selectedList != null && itemPosition >= 0 && itemPosition <= selectedList.getItems().size()) {
            ItemObject item = selectedList.getItems().get(itemPosition-1);
            List<MultiElementItemObject> multiObjectList = ListModel.readMultiElemListFromJson(getServletContext());
            // Change name in MultiElementItem.json if user is only changing name, not item type
            if (item.getItemType().equals("multiElementItem") && itemType.equals("multiElementItem")) {
                // Find the selected item
                MultiElementItemObject selectedItem = null;
                for (MultiElementItemObject list2 : multiObjectList) {
                    if (list2.getName().equals(item.getItemValue())) {
                        selectedItem = list2;
                        break;
                    }
                }
                selectedItem.setName(itemValue);
            } else if (item.getItemType().equals("multiElementItem") && !itemType.equals("multiElementItem")) {
                // Find the selected item
                MultiElementItemObject selectedItem = null;
                for (MultiElementItemObject list3 : multiObjectList) {
                    if (list3.getName().equals(item.getItemValue())) {
                        selectedItem = list3;
                        break;
                    }
                }
                if (selectedItem != null) {
                    multiObjectList.remove(selectedItem);
                }
            } else if (!item.getItemType().equals("multiElementItem") && itemType.equals("multiElementItem")) {
                // New item type is multiElementItem so have to add to json file
                MultiElementItemObject newMultiObject = new MultiElementItemObject(itemValue, listName);
                multiObjectList.add(newMultiObject);
            }
            ListModel.writeMultiElemItemsToJson(multiObjectList, getServletContext());
            item.setItemType(itemType);
            item.setItemValue(itemValue);
        }
        // Update the JSON file
        ListModel.writeListsToJsonFile(lists, getServletContext());
        // Redirect to the list detail page
        response.sendRedirect("/listDetail?name=" + listName);
    }
}
