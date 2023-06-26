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

@WebServlet("/renameList")
public class RenameListServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Read lists from json file
        List<ListObject> lists = ListModel.readListsFromJsonFile(getServletContext());

        request.setAttribute("lists", lists);
        request.getRequestDispatcher("/renameList.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listName = request.getParameter("listName");
        String newListName = request.getParameter("newListName");
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
        // Rename list listName as newListName
        if (selectedList != null) {
            selectedList.setName(newListName);
        }
        // Edit any items that has values stored related to the changed list name
        ListModel.changeMultiElemListName(listName, newListName, getServletContext());
        // Change any items that are linked list and list name needs to be updated
        ListModel.changeLinkedListItemValue(lists, listName, newListName, getServletContext());
        // Update the JSON file
        ListModel.writeListsToJsonFile(lists, getServletContext());
        // Redirect to the list detail page
        response.sendRedirect("/renameList");
    }
}