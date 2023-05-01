<%@ tag pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="msg" items="${successList}">
    <div>
        <c:out value="${msg.value}" />
    </div>
</c:forEach>

<c:forEach var="msg" items="${errorList}">
    <div>
        <c:out value="${msg.value}" />
    </div>
</c:forEach>
