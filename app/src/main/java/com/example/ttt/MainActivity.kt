package com.example.ttt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var buttonboard:Array<Array<Button>>;
    lateinit var turn:TextView;
    var board=Array(3){IntArray(3)}
    var option:Int=0;
    lateinit var reset:Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        turn=findViewById(R.id.turn);
        reset=findViewById(R.id.reset);
        reset.setOnClickListener {
            initializeBoard();
            Toast.makeText(this,"Game Reset Successfull!!",Toast.LENGTH_SHORT).show();
        }

        buttonboard=arrayOf(
                arrayOf(findViewById(R.id.b00),findViewById(R.id.b01),findViewById(R.id.b02)),
                arrayOf(findViewById(R.id.b10),findViewById(R.id.b11),findViewById(R.id.b12)),
                arrayOf(findViewById(R.id.b20),findViewById(R.id.b21),findViewById(R.id.b22))
        )
        initializeBoard();
        for(i in 0..2){
            for(j in 0..2){
                buttonboard[i][j].setOnClickListener {
//                    Toast.makeText(this,"You pressed $i $j button",Toast.LENGTH_SHORT).show()
                    if(option%2==0){
                        board[i][j]=1;//x=1
                        buttonboard[i][j].text="x"
                        option+=1;
                        turn.text="AB O CHALEGA"
                        if(option>2){
                            checkwinner();
                        }
                    }
                    else{
                        board[i][j]=0;//o=0
                        buttonboard[i][j].text="o"
                        option+=1;
                        turn.text="AB X CHALEGA"
                        if(option>2){
                            checkwinner();
                        }
                    }
                }
            }
        }

    }

    private fun checkwinner() {
        if(option==9){
            gamewon(2);
        }
        for(i in 0..2){
            if(checkrow(i)){
                gamewon(board[i][0]);
            }
            if(checkcol(i)){
                gamewon(board[0][i]);
            };
        }
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2]){
            if(board[0][0]!=-1){
                gamewon(board[0][0]);
            }
        }
        if(board[0][2]==board[1][1] && board[1][1]==board[2][0]){
            if(board[0][2]!=-1){
                gamewon(board[0][2]);
            }
        }
    }
    private fun gamewon(j:Int){
        if(j==1){
            turn.text="X jeet gaya party!";
        }
        else if(j==0){
            turn.text="0 jeet gaay party!"
        }
        else{
            turn.text="Game Draw !"
        }
        for(i in 0..2){
            for(j in 0..2){
                buttonboard[i][j].isEnabled=false;

            }
        }
        Toast.makeText(this,"Press Reset for New Game!!",Toast.LENGTH_LONG).show();
    }
    private fun checkcol(col: Int):Boolean {
        if(board[0][col]==board[1][col] && board[1][col]==board[2][col]){
            if(board[0][col]!=-1){
                return true;
            }
        }
        return false;
    }

    private fun checkrow(row: Int):Boolean {
        if(board[row][0]==board[row][1] && board[row][1]==board[row][2]){
            if(board[row][0]!=-1){
                return true;
            }
        }
        return false;

    }

    private fun initializeBoard() {
        option=0;
        turn.text="AB X CHALEGA"
        for(i in 0..2){
            for(j in 0..2){
                board[i][j]=-1;
                buttonboard[i][j].text="";
                buttonboard[i][j].isEnabled=true;
            }
        }
    }

}