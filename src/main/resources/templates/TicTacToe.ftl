<#ftl encoding='UTF-8'>
<html>
<title>${user.getLogin()}</title>
<head>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/modal.css">
    <link rel="stylesheet" href="css/test.css">

    <script type="text/javascript" src="js/tictactoe.js"></script>

</head>
<body>
<a id="userId" style="display:none;">${user.getId()}</a>
<button id="cn" type="button" onclick="connect('${user.getId()}')" style="display: ''">
    CONNECT
</button>
<button id="dcn" type="button" onclick="disconnect()" style="display:none">
    DISCONNECT
</button>
<div class="container">

    <div class="row">
        <div class="col-md-5">
            <h2 style="text-align: center;color: black;"><b id="header">INVITE SOMEONE </b>&nbsp;&nbsp;&nbsp;&nbsp;</h2>

            <div class="box" id="A" style="cursor: pointer"></div>
            <div class="box" id="B" style="cursor: pointer"></div>
            <div class="box" id="C" style="cursor: pointer"></div>
            <div class="box" id="D" style="cursor: pointer"></div>
            <div class="box" id="E" style="cursor: pointer"></div>
            <div class="box" id="F" style="cursor: pointer"></div>
            <div class="box" id="G" style="cursor: pointer"></div>
            <div class="box" id="H" style="cursor: pointer"></div>
            <div class="box" id="I" style="cursor: pointer"></div>

        </div>
        <div class="col-md-6 pull-right">
            <h3>Online Players
                <a type="button" onclick="refresh()" class="glyphicon glyphicon-refresh" style="color: black;cursor: pointer; text-decoration: none; font-size: 15pt"></a>
            </h3>
            <table class="table table-bordered table-hover" style="max-width: 30%">
                <thead>
                <tr>
                    <th>
                        ID
                    </th>
                    <th>
                        PLAYER
                    </th>
                    <th>
                        POINTS
                    </th>
                </tr>
                </thead>
                <tbody id="onlinePlayers">

                </tbody>
            </table>
        </div>
    </div>
</div>

<div onclick="showModal('none')" id="wrap"></div>

<div id="window" style="text-align: center" >
    <h4><b id="inviteFrom">INVITE FROM</b></h4>
    <div class="row">
        <a id="accept" class="btn btn-success" onclick="accept('none')">ACCEPT</a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a id="decline" class="btn btn-danger" onclick="hideModal('none')">DECLINE</a>
    </div>
</div>


</body>
</html>