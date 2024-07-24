<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Naver News Search Results for: ${query}</h1>
    <form action="${pageContext.request.contextPath}/search/news" method="get">
        <input type="text" name="query" placeholder="Search news..." required>
        <button type="submit">Search</button>
    </form>
    <div>
        <c:if test="${not empty newsItems}">
            <ul>
                <c:forEach var="item" items="${newsItem}">
                    <li>
                        <a href="${item.link}">${item.title}</a>
                        <p>${item.description}</p>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
    
</body>
</html>