<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Results</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Search Results for ${location} - ${type}</h1>
    <c:choose>
        <c:when test="${not empty results}">
            <ul>
                <c:forEach var="result" items="${results}">
                    <li>${result}</li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No results found.</p>
        </c:otherwise>
    </c:choose>
    <a href="/">Back to Search</a>
</body>
</html>