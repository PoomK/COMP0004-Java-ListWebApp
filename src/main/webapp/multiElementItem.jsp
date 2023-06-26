<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${selectedItem.name}</title>
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
    <div class="container-md">
        <div class="p-3 mb-2 bg-dark text-white">
            <a href="/listDetail?name=${selectedItem.listName}" class="btn btn-primary">Back to ${selectedItem.listName} list</a>
            <center>
            <h1>${selectedItem.name}</h1>
            <ol class="list-group list-group-numbered">
            <c:forEach items="${selectedItem.items}" var="item">
                <c:choose>
                    <c:when test="${item.itemType == 'url'}">
                        <li class="list-group-item bg-dark text-white">url : <a href="${item.itemValue}" class="btn btn-outline-light">${item.itemValue}</a></li>
                    </c:when>
                    <c:when test="${item.itemType == 'linkedList'}">
                        <li class="list-group-item bg-dark text-white">linked list : <a href="/listDetail?name=${item.itemValue}" class="btn btn-outline-light">${item.itemValue}</a></li>
                    </c:when>
                    <c:when test="${item.itemType == 'image'}">
                        <li class="list-group-item bg-dark text-white"><img src="${item.itemValue}" class="img-fluid"></li>
                    </c:when>
                    <c:otherwise>
                        <li class="list-group-item bg-dark text-white">${item.itemValue}</li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </ol>
            <br>
            <h3>Configure ${selectedItem.name}</h3>
            <a href="/addMultiElem?itemName=${selectedItem.name}" class="btn btn-outline-primary">Add item to ${selectedItem.name}</a>
            <a href="/editMultiElem?itemName=${selectedItem.name}" class="btn btn-outline-primary">Edit item in ${selectedItem.name}</a>
            <a href="/deleteMultiElem?itemName=${selectedItem.name}" class="btn btn-outline-primary">Delete item in ${selectedItem.name}</a>
            </center>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>