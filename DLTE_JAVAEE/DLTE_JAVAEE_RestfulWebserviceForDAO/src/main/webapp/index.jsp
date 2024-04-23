<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login into MyBank</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <form method="post" action="authorize" class="col-lg-3 col-md-6 col-12 align-self-center p-5 rounded-5 shadow">
            <div class="form group">
                <label>Username</label>
                <input type="text" name="username" class="form-control" placeholder="Username"/>
            </div>
            <div class="form group">
                <label>Password</label>
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
            <div class="m-5 p-1">
                <div class="row justify-content-around g-2">
                    <div class="col-6">
                        <button id="login" class="btn btn-outline-primary">Login</button>
                    </div>
                    <div class="col-6">
                        <button id="cancel" class="btn btn-outline-dark">Cancel</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>