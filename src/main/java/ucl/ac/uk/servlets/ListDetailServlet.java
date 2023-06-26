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

@WebServlet("/listDetail")
public class ListDetailServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listName = request.getParameter("name");
        //Read lists from json file
        List<ListObject> lists = ListModel.readListsFromJsonFile(getServletContext());
        // Find the selected list
        ListObject selectedList = null;
        for (ListObject list : lists) {
            if (list.getName().equals(listName)) {
                selectedList = list;
                break;
            }
        }
        //Forwards selected list to list.jsp to display
        request.setAttribute("selectedList", selectedList);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
