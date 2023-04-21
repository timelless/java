<html>
    <head>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <title>Logging page</title>
    </head>
    <body>
        <div class="container">
            <h1>Login</h1>
            <hr/>
            <pre>${errorMessage}</pre>
            <form method="post">
                <label>Name</label>
                <input type="text" name="name" />

                <label>Password</label>
                <input type="password" name="password" />

                <input type="submit" value="Login" class="btn btn-danger" />
            </form>
        </div>

        <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    </body>
</html>