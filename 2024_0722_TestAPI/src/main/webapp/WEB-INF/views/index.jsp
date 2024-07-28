<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Page</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Search for Pet-friendly Places</h1>
    <form action="result" method="get">
        <label for="location">Location:</label>
        <select id="location" name="location">
            <c:forEach var="location" items="${locations}">
                <option value="${location}">${location}</option>
            </c:forEach>
        </select>
        <br>

        <label for="type">Type:</label>
        <select id="type" name="type">
            <c:forEach var="type" items="${types}">
                <option value="${type}">${type}</option>
            </c:forEach>
        </select>
        <br>

        <input type="submit" value="Search">
    </form>
</body>
</html>