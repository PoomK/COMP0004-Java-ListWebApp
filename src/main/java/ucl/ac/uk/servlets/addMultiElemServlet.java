package ucl.ac.uk.servlets;

import ucl.ac.uk.model.ItemObject;
import ucl.ac.uk.model.ListModel;

import ucl.ac.uk.model.MultiElementItemObject;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addMultiElem")
public class addMultiElemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("itemName");
        //Forward itemName to display in the title
        request.setAttribute("itemName", itemName);
        request.getRequestDispatcher("/addMultiElem.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("listName");
        String itemType = request.getParameter("itemType");
        String itemValue = request.getParameter("itemValue");
        // Read lists from json file
        List<MultiElementItemObject> lists = ListModel.readMultiElemListFromJson(getServletContext());
        // Find the ListObject corresponding to the listName parameter
        MultiElementItemObject selectedItem = null;
        for (MultiElementItemObject list : lists) {
            if (list.getName().equals(itemName)) {
                selectedItem = list;
                break;
            }
        }
        // Add the new item to the selected ListItem
        if (selectedItem!= null) {
            ItemObject newItem = new ItemObject(itemType, itemValue);
            selectedItem.getItems().add(newItem);
        }
        // Update the JSON file
        ListModel.writeMultiElemItemsToJson(lists, getServletContext());
        // Redirect to the list detail page
        response.sendRedirect("/displayMultiElemItem?itemName=" + itemName);
    }
}
