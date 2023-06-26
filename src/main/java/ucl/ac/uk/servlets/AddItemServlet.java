package ucl.ac.uk.servlets;

import javax.servlet.http.HttpServlet;

import ucl.ac.uk.model.ItemObject;
import ucl.ac.uk.model.ListModel;
import ucl.ac.uk.model.ListObject;
import ucl.ac.uk.model.MultiElementItemObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addItem")
public class AddItemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Read lists from json file
        List<ListObject> lists = ListModel.readListsFromJsonFile(getServletContext());
        //Forward lists to jsp file for dropdown box
        request.setAttribute("lists", lists);
        request.getRequestDispatcher("/addItem.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Receive parameters
        String listName = request.getParameter("listName");
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
        // Add the new item to the selected ListObject
        if (selectedList != null) {
            ItemObject newItem = new ItemObject(itemType, itemValue);
            selectedList.getItems().add(newItem);
        }
        // If item type is equal to multiElementItem, create new object in multiElementItem.json
        if (Objects.equals(itemType, "multiElementItem")) {
            MultiElementItemObject newMultiObject = new MultiElementItemObject(itemValue, listName);
            List<MultiElementItemObject> multiObjectList = ListModel.readMultiElemListFromJson(getServletContext());
            multiObjectList.add(newMultiObject);
            ListModel.writeMultiElemItemsToJson(multiObjectList,getServletContext());
        }
        // Update the JSON file
        ListModel.writeListsToJsonFile(lists, getServletContext());
        // Redirect to the list detail page
        response.sendRedirect("/listDetail?name=" + listName);
    }
}