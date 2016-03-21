<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Event Manager - Event list</title>
      <link rel="stylesheet" href="<c:url value=" /default.css"/>" type="text/css"></head>
      <body>
        <h1>Event list</h1>

        <table>
          <tr>
            <th>Name</th>
            <th>Place</th>
            <th>Label</th>
            <th>TimeFrom</th>
            <th>TimeTo</th>
          </tr>
          <c:forEach items="${events}" var="event" varStatus="loopStatus">
            <tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
              <td><c:out value="${event.name}"/></td>
              <td><c:out value="${event.place}"/></td>
              <td><c:out value="${event.label}"/></td>
              <td><c:out value="${event.timeFrom}"/></td>
              <td><c:out value="${event.timeTo}"/></td>
            </tr>
          </c:forEach>
        </table>

        <p>
          <a href="<c:url value=" /AddEvent"/>">Add Event</a>
        </p>

      </body>
    </html>
