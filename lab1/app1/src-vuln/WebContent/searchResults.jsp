<jsp:useBean id="bean" class="gui.model.SearchResultBean" scope="session"/>
<%@page import="gui.model.PostBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Your search for:<%=bean.getSearchTerm() %> has <%=bean.getResults().size() %> results:
<%for (PostBean post:bean.getResults()){ %>
<div class=post>
	<h3 class=author>
		<%=post.getAuthor() %>
	</h3>
	<div class=content>
		<%=post.getContent() %>
	</div>
</div>
<%} %>
</body>
</html>