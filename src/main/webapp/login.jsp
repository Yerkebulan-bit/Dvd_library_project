
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        h1{
            text-align: center;
        }
        input {
            font-size: 16px;
            width: 350px;
            height: 50px;
            padding: 10px 20px;
        }
        #subInput:hover{
            background-color: #ffffff;
            color: blue;
            border: 1px solid blue;
        }
        label{
            display: block;
            margin: 0 0 10px 0;
        }
        strong{
            font-size: 16px;
        }
        #subInput{
            background-color: blue;
            transition: all 0.3s ease 0s;
            color: #fff;
            border: none;
        }
        .formcontainer{
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>
    <form action="loginAction.action">
        <h1>Login Form</h1>
        <div class="formcontainer">
            <hr/>
            <div class="container">
                <label for="Username"><strong>Username</strong></label>
                <div><input type = "text" placeholder="Enter Username" name = "Username" id="Username"/><br/><br/></div>
                <label for="Password"><strong>Password</strong></label>
                <div><input type = "password" name = "password" id="Password" placeholder="Enter Username"/><br/><br/></div>
            </div>
            <input type="submit" value="Login" id="subInput">
        </div>
    </form>
</body>
</html>
