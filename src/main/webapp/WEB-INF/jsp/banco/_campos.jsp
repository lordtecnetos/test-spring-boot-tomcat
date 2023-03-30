<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:errors path="*" />
<div>
    <form:label path="ativo">Ativo: </form:label>
    <form:checkbox path="ativo" />
</div>
<div>
    <form:label path="nome">Nome: </form:label>
    <form:input type="text" path="nome" required="required" />
</div>
