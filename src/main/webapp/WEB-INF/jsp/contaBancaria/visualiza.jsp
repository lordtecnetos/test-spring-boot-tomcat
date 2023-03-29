<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>Editar</title>

<form:form action="/conta-bancaria/alterar/${form.codigo}" method="put" modelAttribute="form">
    <c:import url="_campos.jsp" />
    <div>
        <button type="button" onclick="document.querySelector('#excluir').submit()">Excluir</button>
        <button type="button" onclick="location.href = '/conta-bancaria/listar'">Cancelar</button>
        <button type="submit">Salvar</button>
    </div>
</form:form>

<form:form id="excluir" action="/conta-bancaria/excluir/${form.codigo}" method="delete" />

<script src="/resources/js/main.js"></script>
