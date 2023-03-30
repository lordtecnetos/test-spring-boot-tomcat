<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Lista</title>

<a href="/banco/novo">Adicionar</a>

<table>
    <thead>
        <tr>
            <th>Nome</th>
            <th>Situação</th>
            <th>Opções</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="dto" items="${dtos}">
            <tr>
                <td>
                    <c:out value="${dto.nome}" />
                </td>
                <td>
                    <c:out value="${dto.ativo ? 'Ativo' : 'Inativo'}" />
                </td>
                <td>
                    <a href="/banco/visualizar/${dto.codigo}">Editar</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty dtos}">
            <tr>
                <td colspan="99">Nenhum resultado encontrado</td>
            </tr>
        </c:if>
    </tbody>
</table>

<a href="/dashboard">Dashboard</a>
