package ucl.ac.uk.servlets;

import ucl.ac.uk.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchWord")
public class searchServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        //READ FILES
        List<ListObject> lists = ListModel.readListsFromJsonFile(getServletContext());
        List<MultiElementItemObject> multiElemLists = ListModel.readMultiElemListFromJson(getServletContext());
        //Obtain search results
        SearchResultObject searchResult = ListModel.performSearch(keyword, lists, multiElemLists);
        //Forward search results to display
        request.setAttribute("searchResult", searchResult);
        request.getRequestDispatcher("/searchResult.jsp").forward(request, response);
    }
}
