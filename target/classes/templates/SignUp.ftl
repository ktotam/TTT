<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <style type="text/css">
        body {
            background-color: #E9F2F7;
        }
    </style>
</head>
<body>

<div class="container" style="margin-top: 5%;">
    <div class="row">
        <div class="col-sm-4"> </div>
        <div class="col-md-4">

            <h1 class="text-center text-success"> Register page</h1>
            <br/>
                <div class="tab-content">
                    <div id="home" class="tab-pane fade in active">

                        <form>

                            <div class="form-group">
                                <label for="UserName">Name</label>
                                <input type="text" class="form-control" name="login" maxlength="255">
                            </div>

                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" name="password" maxlength="255">
                            </div>

                            <button formaction="registration" formmethod="post" type="submit" class="btn btn-lg btn-success">Sign Up</button>

                        </form>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
