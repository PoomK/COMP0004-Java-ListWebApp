<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List application</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="bg-dark">
    <nav class="navbar navbar-expand-lg bg-dark-subtle" data-bs-theme="dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">List Application</a>
        <button class="navbar-toggler bg-light-subtle   " type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
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
    <main>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="container-md">
        <div class="p-3 mb-2 bg-dark text-white">
            <center>
                <h1>Welcome to the list application</h1>
                <p class="fs-7">This is a java webapp for the java coursework.</p>
                <p class="fs-7">The navigation bar at the top contains links to configure lists and their contents.</p>
                <p class="fs-7">Type a keyword in the search box at the top to return lists and items that contain the keyword.</p>
                <p class="fs-7">Click on the button below to go to list viewer.</p>
                <form action="/list.html">
                    <button type="submit" class="btn btn-primary btn-lg">Click to view lists</button>
                </form>
            </center>
        </div>
    </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>