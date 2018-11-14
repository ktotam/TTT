var stompClient = null;
var playing = false;
var yourTurn = false;
var $box;
var playerId;
var playingWith;
var board = {
    A: null,
    B: null,
    C: null,
    D: null,
    E: null,
    F: null,
    G: null,
    H: null,
    I: null
};


function connect(userId) {
    playerId = userId;
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    document.getElementById('cn').style.display = 'none';
    document.getElementById('dcn').style.display = '';
    var r = new XMLHttpRequest();
    r.open('POST', '/tttOnline', true);
    r.send();
    var socket = new SockJS('/ws-tictactoe');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        stompClient.subscribe('/topic/tictactoe', function (msg) {
            if (msg.body === "refresh") {
                refresh();
            }
            else {
                if (userId === msg.body.substring(msg.body.indexOf(" ") + 1, msg.body.length) && msg.body.indexOf(" ") !== -1) {
                    showModal('block', msg.body.substring(0, msg.body.indexOf(" ")));
                }
                else {
                    var jsonMsg = JSON.parse(msg.body);
                    if (jsonMsg.answer !== "yes" && jsonMsg.answer !== "no") {
                        if (jsonMsg.playingWith == userId) {
                            board[jsonMsg.cell]  = "O";
                            document.getElementById(jsonMsg.cell).innerText = "O";
                            if(solutions()){
                                alert("YOU LOSE");
                                document.getElementById('header').innerText = 'INVITE SOMEONE';
                                playing = false;
                                yourTurn = false;
                            }
                            else {
                                yourTurn = true;
                                checkIfFull();
                            }
                        }
                    }
                    else {
                        if (jsonMsg.answer === "yes" && jsonMsg.playingWith == userId) {
                            for (var spot in board){
                                board[spot] = null;
                                document.getElementById(spot).innerText = '';
                            }
                            playing = true;
                            $box.on("click", xmove);
                            playingWith = jsonMsg.user;
                            document.getElementById('header').innerText = 'PLAYING WITH ' + document.getElementById(jsonMsg.user).innerText;


                        }
                        if(jsonMsg.answer === "no" && jsonMsg.playingWith == userId) {
                            alert("DECLINE");
                        }

                    }
                }
            }
        });
        refreshAll();
    });
}

function disconnect() {
    if (stompClient !== null) {
        refreshAll();
        var r = new XMLHttpRequest();
        r.open('POST', '/tttOffline', true);
        r.send();
        stompClient.disconnect();
        document.getElementById('dcn').style.display = 'none';
        document.getElementById('cn').style.display = '';
        for (var spot in board){
            board[spot] = null;
            document.getElementById(spot).innerText = '';
        }
    }
}

function refreshAll() {
    stompClient.send("/app/hello", {}, "123");
    document.getElementById("onlinePlayers").innerHTML = "";
}

function refresh() {

    var r = new XMLHttpRequest();
    r.open('POST', '/getOnlinePlayers', true);
    r.onload = () => {
        document.getElementById("onlinePlayers").innerHTML = "";
        var temp = JSON.parse(r.response);
        for (var i = 0; i < temp.users.length; i++) {
            var s ="<tr style='cursor: pointer' onclick='invite(" + temp.users[i].id + ")'><td>" + temp.users[i].id + "</td><td id='"+ temp.users[i].id + "'>" + temp.users[i].login + "</td><td>" + temp.users[i].points + "</td></tr>"
            $("#onlinePlayers").append(s);
        }
    }
    r.send();
}

function playTicTacToe() {
    window.location.replace("/tictactoe");
}

function invite(id) {
    stompClient.send("/app/invite", {}, id);
}

function accept(state, fromId) {
    document.getElementById('window').style.display = state;
    document.getElementById('wrap').style.display = state;
    playing = true;
    yourTurn = true;
    $box.on("click", xmove);
    document.getElementById('header').innerText = 'PLAYING WITH ' + document.getElementById(fromId).innerText;
    playingWith = fromId;
    stompClient.send("/app/answer", {}, JSON.stringify({user: parseInt(playerId), playingWith: playingWith , answer: "yes"}));
    for (var spot in board){
        board[spot] = null;
        document.getElementById(spot).innerText = '';
    }
}

function showModal(state, fromId){
    document.getElementById('inviteFrom').innerText = 'INVITE FROM ' + document.getElementById(fromId).innerText;
    document.getElementById('window').style.display = state;
    document.getElementById('wrap').style.display = state;
    document.getElementById('accept').setAttribute('onclick', "accept('none', " + fromId + ")");
    document.getElementById('decline').setAttribute('onclick', "hideModal('none', " + fromId + ")");

}

function hideModal(state, fromId){
    document.getElementById('window').style.display = state;
    document.getElementById('wrap').style.display = state;
    stompClient.send("/app/answer", {}, JSON.stringify({user: parseInt(playerId), playingWith: fromId , answer: "no"}));

}

$( document ).ready(function() {
    $box = $('.box');
    var userId = document.getElementById('userId').innerText;
    connect(userId);

});
var xmove = function(){
    if (playing && yourTurn) {
        if (board[$(this).attr("id")]  === null) {
            $(this).text("X");

            board[$(this).attr("id")]  = "X";
            stompClient.send("/app/turn", {}, JSON.stringify({cell:$(this).attr("id"), user: parseInt(playerId), playingWith: playingWith }));
            yourTurn = false;
        }
        if(solutions()){
            alert("YOU WIN!");
            stompClient.send("/app/win", {}, playerId);
            document.getElementById('header').innerText = 'INVITE SOMEONE';
            playing = false;
            yourTurn = false;
        }
        else checkIfFull();
    }
};

var solutions = function() {
    return (board.A && (board.A === board.B && board.A === board.C))
        || (board.D && (board.D === board.E && board.D === board.F))
        || (board.G && (board.G === board.H && board.G === board.I))

        || (board.A && (board.A === board.D && board.A === board.G))
        || (board.B && (board.B === board.E && board.B === board.H))
        || (board.C && (board.C === board.F && board.C === board.I))

        || (board.C && (board.C === board.E && board.C === board.G))
        || (board.A && (board.A === board.E && board.A === board.I))
        ;
};
function checkIfFull(){
    for (var spot in board){
        if(board[spot] === null){
            return;
        }
    }
    playing = false;
    yourTurn = false;
    document.getElementById('header').innerText = 'INVITE SOMEONE';
    alert("DRAW!");
}