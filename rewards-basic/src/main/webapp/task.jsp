<%@ page import="org.kie.api.task.model.TaskSummary" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Task management</title>
</head>
<body>
<% String user = request.getParameter("user"); %>
<p><%= user %>'s Task</p>
<table border="1">
<tr>
<th>Task Name</th>
<th>Task Id</th>
<th>ProcessInstance Id</th>
<th>Action</th>
</tr>
<% for (TaskSummary task : (List<TaskSummary>)request.getAttribute("taskList")) { %>
<tr>
<td><%= task.getName() %></td>
<td><%= task.getId() %></td>
<td><%= task.getProcessInstanceId() %></td>
<td><a href="task?user=<%= user %>&amp;taskId=<%= task.getId() %>&amp;cmd=approve">Approve</a></td>
</tr>
<% } %>
</table>
</body>
</html>