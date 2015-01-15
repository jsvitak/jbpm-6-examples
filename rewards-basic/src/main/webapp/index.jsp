<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Rewards Basic example</title>
</head>
<body>
<p>Rewards Basic example</p>
<p><%= request.getAttribute("message") == null ? "" : request.getAttribute("message") %></p>
<ul>
<li><a href="startProcess.jsp">Start Reward Process</a></li>
<li><a href="task?user=jiri&amp;cmd=list">Jiri's Task</a></li>
<li><a href="task?user=mary&amp;cmd=list">Mary's Task</a></li>
</ul>
</body>
</html>