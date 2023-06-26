package ucl.ac.uk.servlets;

import ucl.ac.uk.model.*;
import ucl.ac.uk.model.ListObject;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteItem")
public class DeleteItemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Read lists from json file
        List<ListObject> lists = ListModel.readListsFromJsonFile(getServletContext());
        //Send lists for dropdown box
        request.setAttribute("lists", lists);
        request.getRequestDispatcher("/deleteItem.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listName = request.getParameter("listName");
        int itemPosition = Integer.parseInt(request.getParameter("itemPosition"));
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
        // Delete item at position itemPosition
        if (selectedList != null && itemPosition >= 0 && itemPosition <= selectedList.getItems().size()) {
            ItemObject selectedItem = selectedList.getItems().get(itemPosition-1);
            // Check item type if it is a multi element item, delete from relevant json file
            String itemType = selectedItem.getItemType();
            String itemName = selectedItem.getItemValue();
            if (itemType.equals("multiElementItem")) {
                List<MultiElementItemObject> multiObjectList = ListModel.readMultiElemListFromJson(getServletContext());
                // Find the selected item
                MultiElementItemObject selectedItem2 = null;
                for (MultiElementItemObject list3 : multiObjectList) {
                    if (list3.getName().equals(itemName)) {
                        selectedItem2 = list3;
                        break;
                    }
                }
                if (selectedItem2 != null) {
                    multiObjectList.remove(selectedItem2);
                }
                ListModel.writeMultiElemItemsToJson(multiObjectList, getServletContext());
            }
            selectedList.getItems().remove(itemPosition-1);
        }
        // Update the JSON file
        ListModel.writeListsToJsonFile(lists, getServletContext());
        // Redirect to the list detail page
        response.sendRedirect("/listDetail?name=" + listName);
    }
}
