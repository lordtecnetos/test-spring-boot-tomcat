<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tld/components.tld" prefix="co"%>

<title>Adicionar</title>

<co:messages />

<form:form action="/banco/salvar" method="post" modelAttribute="form">
    <c:import url="_campos.jsp" />
    <div>
        <button type="button" onclick="location.href = '/banco/listar'">Cancelar</button>
        <button type="submit">Salvar</button>
    </div>
</form:form>

<script src="/resources/js/main.js"></script>
