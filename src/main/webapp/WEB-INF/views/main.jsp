<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Pokemon Catalog</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
</head>
<body>

<div class="container">
    <h1>Pokemon's Catalog</h1>

    <div id="add_new_user">
        <c:url var="addUrl" value="/add"/><a id="add" href="${addUrl}" class="btn btn-success">Add pokemon</a>
    </div>
    <div>&nbsp;</div>

    <table class="table">
        <tr>
            <th><c:url var="sortUrl" value="/sort?field=name"/> <a href="${sortUrl}">Name</a></th>
            <th><c:url var="sortUrl" value="/sort?field=hp"/> <a href="${sortUrl}">HP</a></th>
            <th><c:url var="sortUrl" value="/sort?field=attack"/> <a href="${sortUrl}">Attack</a></th>
            <th><c:url var="sortUrl" value="/sort?field=isLegendary"/> <a href="${sortUrl}">IsLegendary</a></th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${pokemons}" var="pokemon">
            <tr>
                <td>
                    <c:out value="${pokemon.getName()}"/>
                </td>
                <td>
                    <c:out value="${pokemon.getHp()}"/>
                </td>
                <td>
                    <c:out value="${pokemon.getAttack()}"/>
                </td>
                <td>
                    <c:out value="${pokemon.getIsLegendary()}"/>
                </td>
                <td>
                    <c:url var="editUrl" value="/edit?id=${pokemon.getId()}" /><a id="update" href="${editUrl}" class="btn btn-warning">Update</a>
                </td>
                <td>
                    <c:url var="deleteUrl" value="/delete?id=${pokemon.getId()}" /><a id="delete" href="${deleteUrl}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>