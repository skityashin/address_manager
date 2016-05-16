<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table id="ttt" border="1px" style="color: black">
    <tr>
        <td  align="center" >content: ${content}</td>
    </tr>
    <tr>
        <td  align="center" >country:${country}</td>
    </tr>
    <tr>
        <c:forEach var="phones" items="${phones}">
            <table border="1px" style="color: black" >
                <tr>
                    <td  align="center" >phones: ${phones}</td>
                </tr>
                <br>
            </table>
        </c:forEach>
    </tr>

    <br>
</table>


