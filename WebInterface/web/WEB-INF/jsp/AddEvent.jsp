<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Event Manager - Add new Event</title>
      <link rel="stylesheet" href="<c:url value=" /default.css"/>" type="text/css"></head>
      <body>
        <h1>Add new Event</h1>

        <c:if test="${not empty error}">
          <p class="error">
            <c:out escapeXml="false" value="${error}"/>
          </p>
        </c:if>

        <form action="<c:url value=" /AddEvent"/>" method="post">
          <table>
            <tr>
              <th>Name:</th>
              <td><input type="text" name="name" value="${eventForm.name}"/></td>
            </tr>
            <tr>
              <th>Place:</th>
              <td><input type="text" name="place" value="${eventForm.place}"/></td>
            </tr>
            <tr>
              <th>Label:</th>
              <td><input type="text" name="label" value="${eventForm.label}"/></td>
            </tr>
            <tr>
              <th>TimeFrom:</th>
              <td><input type="text" name="timeFrom" value="${eventForm.timeFrom}"/></td>
            </tr>
            <tr>
              <th>TimeTo:</th>
              <td><input type="text" name="timeTo" value="${eventForm.timeTo}"/></td>
            </tr>
          </table>
          <p>
            <input type="Submit" value="Add Event" name="submit"/>
            <input type="Submit" value="Cancel" name="cancel"/>
          </p>
        </form>
      </body>
    </html>
