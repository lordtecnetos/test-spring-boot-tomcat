<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>Adicionar</title>

<form:form action="/conta-bancaria/salvar" method="post" modelAttribute="form">
    <c:import url="_campos.jsp" />
    <div>
        <button type="button" onclick="location.href = '/conta-bancaria/listar'">Cancelar</button>
        <button type="submit">Salvar</button>
    </div>
</form:form>
