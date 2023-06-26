<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List viewer</title>
    <style>
        hr {
            height:5px;
            border-width:0;
            background-color:#ffffff;
        }
    </style>
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
              <a class="nav-link active" aria-current="page" href="/list.html">List viewer</a>
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
        <center>
            <br>
            <h2>Here are all your lists. Click one to view or edit.</h2>
            <br>
            <c:forEach items="${lists}" var="list">
                <p><a href="/listDetail?name=${list.getName()}" class="btn btn-outline-light">${list.getName()}</a></p>
            </c:forEach>
            <br>
            <hr>
            <h2>Menu</h2>
            <h3>Configure List</h3>
            <a href="create.jsp" class="btn btn-outline-primary">Create new list</a>
            <a href="/deleteList" class="btn btn-outline-primary">Delete a list</a>
            <a href="/renameList" class="btn btn-outline-primary">Rename a list</a>
            <h3>Configure List Content</h3>
            <a href="/addItem" class="btn btn-outline-primary">Add item to a list</a>
            <a href="/editItem" class="btn btn-outline-primary">Edit item in a list</a>
            <a href="/deleteItem" class="btn btn-outline-primary">Delete item in a list</a>
            <br>
            <br>
            <a href="index.jsp" class="btn btn-primary">Back to home</a>
        </center>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>