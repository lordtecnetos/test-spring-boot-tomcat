<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:errors path="*" />
<div>
    <form:label path="ativo">Ativa: </form:label>
    <form:checkbox path="ativo" />
</div>
<div>
    <form:label path="nome">Nome: </form:label>
    <form:input type="text" path="nome" required="required" />
</div>
<div id="autocomplete-banco">
    <form:label path="nomeBanco">Banco: </form:label>
    <form:hidden path="codigoBanco" class="autocomplete-value" />
    <form:input type="text" path="nomeBanco" class="autocomplete-label"
        data-url-autocomplete="/banco/autocomplete?ativos=true"
        onkeyup="window.autocomplete(this)" 
        required="required" />
    <div class="autocomplete-body"></div>
</div>
