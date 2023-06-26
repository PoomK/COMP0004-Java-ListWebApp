package ucl.ac.uk.servlets;

import ucl.ac.uk.model.ListModel;
import ucl.ac.uk.model.MultiElementItemObject;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteMultiElem")
public class deleteMultiElemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("itemName");
        // Send to jsp file to display itemName
        request.setAttribute("itemName", itemName);
        request.getRequestDispatcher("/deleteMultiElem.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("listName");
        int itemPosition = Integer.parseInt(request.getParameter("itemPosition"));
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
        // Delete item at position itemPosition
        if (selectedItem != null && itemPosition >= 0 && itemPosition <= selectedItem.getItems().size()) {
            selectedItem.getItems().remove(itemPosition-1);
        }
        // Update the JSON file
        ListModel.writeMultiElemItemsToJson(lists, getServletContext());
        // Redirect to the list detail page
        response.sendRedirect("/displayMultiElemItem?itemName=" + itemName);
    }
}
