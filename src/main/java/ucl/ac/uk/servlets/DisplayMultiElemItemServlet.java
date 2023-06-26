package ucl.ac.uk.servlets;

import ucl.ac.uk.model.ListModel;
import ucl.ac.uk.model.MultiElementItemObject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayMultiElemItem")
public class DisplayMultiElemItemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("itemName");
        //Read lists from json file
        List<MultiElementItemObject> lists = ListModel.readMultiElemListFromJson(getServletContext());
        // Find the selected item
        MultiElementItemObject selectedItem = null;
        for (MultiElementItemObject list : lists) {
            if (list.getName().equals(itemName)) {
                selectedItem = list;
                break;
            }
        }
        // Send the selected item to display
        request.setAttribute("selectedItem", selectedItem);
        request.getRequestDispatcher("/multiElementItem.jsp").forward(request, response);
    }
}
