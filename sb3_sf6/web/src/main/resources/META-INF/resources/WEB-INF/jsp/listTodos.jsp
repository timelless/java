<%@ include file="common/header.jspf" %>
    <%@ include file="common/nav.jspf" %>
        <div class="container">
            <h1>${name}'s TODOs:</h1>
            <hr/>
            <table class="table">
                <thead>
                    <tr>
                        <th>description</th>
                        <th>target date</th>
                        <th>done</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${todos}" var="todo">
                        <tr>
                            <td>${todo.description}</td>
                            <td>${todo.targetDate}</td>
                            <td>${todo.done}</td>
                            <td>
                                <a href="update-todo?id=${todo.id}" class="btn btn-warning">Update</a>
                            </td>
                            <td>
                                <a href="delete-todo?id=${todo.id}" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="add-todo" class="btn btn-success">Add</a>
        </div>
<%@ include file="common/footer.jspf" %>