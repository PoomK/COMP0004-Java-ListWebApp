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

@WebServlet("/list.html")
public class ListServlet extends HttpServlet {
    private static final String LISTS_JSON_PATH = "/WEB-INF/lists.json";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Read lists from json file
        List<ListObject> lists = ListModel.readListsFromJsonFile(getServletContext());

        request.setAttribute("lists", lists);
        request.getRequestDispatcher("/listViewer.jsp").forward(request, response);
    }
}