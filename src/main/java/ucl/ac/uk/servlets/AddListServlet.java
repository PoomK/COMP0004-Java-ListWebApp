package ucl.ac.uk.servlets;

import ucl.ac.uk.model.ListModel;
import ucl.ac.uk.model.ListObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/createList")
public class AddListServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Receive list name from text box
        String listName = req.getParameter("listName");
        // Create new list object
        ListObject newList = new ListObject(listName);
        // Add new list to json file
        List<ListObject> lists = ListModel.readListsFromJsonFile(getServletContext());
        lists.add(newList);
        ListModel.writeListsToJsonFile(lists, getServletContext());
        resp.sendRedirect("create.jsp");
    }
}
