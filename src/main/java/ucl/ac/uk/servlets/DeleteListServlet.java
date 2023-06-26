package ucl.ac.uk.servlets;

import ucl.ac.uk.model.ListModel;
import ucl.ac.uk.model.ListObject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteList")
public class DeleteListServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Read lists from json file
        List<ListObject> lists = ListModel.readListsFromJsonFile(getServletContext());
        request.setAttribute("lists", lists);
        request.getRequestDispatcher("/deleteList.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listName = request.getParameter("listName");
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
        // Delete list listName
        if (selectedList != null) {
            lists.remove(selectedList);
        }
        // Update the JSON file
        ListModel.writeListsToJsonFile(lists, getServletContext());
        // Redirect to the list detail page
        response.sendRedirect("/deleteList");
    }
}