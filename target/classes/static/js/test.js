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

var gameOn = true;
var $box;

var xmove = function(){
    if (board[$(this).attr("id")]  !== null) {
        //
    } else {
        $(this).text("X");
        board[$(this).attr("id")]  = "X";
        $box.off("click", xmove);
        $box.on("click", omove);
    }
    if(solutions()){
        alert("Player 1 " + "Wins!");
        gameOn = false;
    }
    checkIfFull()
};
var omove = function(){
    if (board[$(this).attr("id")]  !== null) {
        //
    } else {
        $(this).text("O");
        board[$(this).attr("id")]  = "O";
        $box.off("click", omove)
        $box.on("click", xmove)
    }

    if(solutions()){
        alert("Player 2 " + "Wins!");
        gameOn = false;
    }
    checkIfFull();
};

var solutions = function() {
    return (board.A && (board.A == board.B && board.A == board.C))
        || (board.D && (board.D == board.E && board.D == board.F))
        || (board.G && (board.G == board.H && board.G == board.I))

        || (board.A && (board.A == board.D && board.A == board.G))
        || (board.B && (board.B == board.E && board.B == board.H))
        || (board.C && (board.C == board.F && board.C == board.I))

        || (board.C && (board.C == board.E && board.C == board.G))
        || (board.A && (board.A == board.E && board.A == board.I))
        ;
};
function checkIfFull(){
    for (var spot in board){
        if(board[spot] === null){
            return;
        }
    }
    gameOn = false;
    alert("tie game!")
}
function checkSpot(spot, symbol){
    if(board[spot] === null){
        board[spot] = symbol;
    }
}
$( document ).ready(function() {
    $box = $('.box')





    $box.on("click", xmove)

});
