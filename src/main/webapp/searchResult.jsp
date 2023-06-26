<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search results for ${searchResult.keyword}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="bg-dark">
    <nav class="navbar navbar-expand-lg bg-dark-subtle" data-bs-theme="dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">List Application</a>
        <button class="navbar-toggler bg-light-subtle" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" href="index.jsp">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/list.html">List viewer</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Configure list
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="create.jsp">Create list</a></li>
                <li><a class="dropdown-item" href="/deleteList">Delete list</a></li>
                <li><a class="dropdown-item" href="/renameList">Rename list</a></li>
              </ul>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Configure list content
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/addItem">Add item</a></li>
                <li><a class="dropdown-item" href="/deleteItem">Delete item</a></li>
                <li><a class="dropdown-item" href="/editItem">Edit item</a></li>
              </ul>
            </li>
          </ul>
          <form class="d-flex" role="search" action="/searchWord" method="get">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" id="keyword" name="keyword" required>
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </div>
    </nav>
    <div class="p-3 mb-2 bg-dark text-white">
        <div class="container-md">
        <h1>Search result for ${searchResult.keyword}</h1>
        <c:if test="${empty searchResult.matchingListNames}">
            <p>No matching list names found.</p>
        </c:if>
        <c:if test="${not empty searchResult.matchingListNames}">
            <h2>Matching List Names:</h2>
            <c:forEach var="listName" items="${searchResult.matchingListNames}">
                <p><a href="/listDetail?name=${listName}" class="btn btn-outline-light">${listName}</a></p>
            </c:forEach>
        </c:if>
        <c:if test="${empty searchResult.matchingItems}">
            <p>No matching items found.</p>
        </c:if>
        <c:if test="${not empty searchResult.matchingItems}">
            <h2>Matching items with ${searchResult.keyword} in the value:</h2>
            <table class="table table-bordered">
                <thead>
                    <tr class="table-dark">
                        <th scope="col">Item</th>
                        <th scope="col">List Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${searchResult.matchingItems}" var="item">
                        <tr class="table-dark">
                            <td>
                                <c:choose>
                                    <c:when test="${item != null and item.itemType == 'url'}">
                                        url : <a href="${item.itemValue}" class="btn btn-outline-light">${item.itemValue}</a>
                                    </c:when>
                                    <c:when test="${item != null and item.itemType == 'linkedList'}">
                                        linked list : <a href="/listDetail?name=${item.itemValue}" class="btn btn-outline-light">${item.itemValue}</a>
                                    </c:when>
                                    <c:when test="${item != null and item.itemType == 'image'}">
                                        <img src="${item.itemValue}" class="img-fluid">
                                    </c:when>
                                    <c:when test="${item != null and item.itemType == 'multiElementItem'}">
                                        Multi element item : <a href="/displayMultiElemItem?itemName=${item.itemValue}" class="btn btn-outline-light">${item.itemValue}</a>
                                    </c:when>
                                    <c:otherwise>
                                        Text : ${item.itemValue}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><a href="/listDetail?name=${item.listName}" class="btn btn-outline-light">${item.listName}</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <a href="/list.html" class="btn btn-primary">Back to lists</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>