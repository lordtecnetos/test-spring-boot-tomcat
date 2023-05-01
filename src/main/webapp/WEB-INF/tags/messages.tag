<%@ tag pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="messages">
    <div data-type="success">
        <c:forEach var="msg" items="${successList}">
            <div data-message>
                <c:out value="${msg.value}" />
            </div>
        </c:forEach>
    </div>

    <div data-type="error">
        <c:forEach var="msg" items="${errorList}">
            <div data-message>
                <c:out value="${msg.value}" />
            </div>
        </c:forEach>
    </div>

    <div data-type="warning">
        <c:forEach var="msg" items="${warningList}">
            <div data-message>
                <c:out value="${msg.value}" />
            </div>
        </c:forEach>
    </div>

    <div data-type="info">
        <c:forEach var="msg" items="${infoList}">
            <div data-message>
                <c:out value="${msg.value}" />
            </div>
        </c:forEach>
    </div>
</div>
