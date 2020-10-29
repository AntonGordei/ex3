<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Pokemon form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h3 id="form_header" class="text-warning" align="center">Pokemon Form</h3>
    <div>&nbsp;</div>

    <!-- User input form to add a new user or update the existing user-->
    <c:url var="saveUrl" value="/save"/>
    <form:form id="pokemonForm" modelAttribute="pokemonAttr" method="POST" action="${saveUrl}">
        <form:hidden path="id" value="${pokemonAttr.id}"/>
        <label for="pokemonName">Enter Name: </label>
        <form:input id="pokemonName" cssClass="form-control" path="name" />
        <div>&nbsp;</div>
        <label for="pokemonHp">Enter HP: </label>
        <form:input id="pokemonHp" cssClass="form-control" path="hp" />
        <div>&nbsp;</div>
        <label for="pokemonAttack">Enter Attack: </label>
        <form:input id="pokemonAttack" cssClass="form-control" path="attack" />
        <div>&nbsp;</div>
        <label for="pokemonIsLegendary">Is Legendary: </label>
        <form:checkbox id="pokemonIsLegendary" cssClass="form-control" path="isLegendary" />
        <div>&nbsp;</div>

        <button id="saveBtn" type="submit" class="btn btn-primary">Save</button>
    </form:form>
</div>
</body>
</html>