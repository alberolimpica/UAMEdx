package com.example.r00143659.conecta4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChaChaCha extends AppCompatActivity implements OnClickListener {
    int SIZE = 7;
    GameChaChaCha gameChaChaCha;

    private final int ids[][]={
            {0, 0, R.id.f1, R.id.f2, R.id.f3, 0, 0},
            {0, 0, R.id.f4, R.id.f5, R.id.f6, 0, 0},
            {R.id.f7, R.id.f8, R.id.f9, R.id.f10, R.id.f11, R.id.f12, R.id.f13},
            {R.id.f14, R.id.f15, R.id.f16, R.id.f17, R.id.f18, R.id.f19, R.id.f20},
            {R.id.f21, R.id.f22, R.id.f23, R.id.f24, R.id.f25, R.id.f26, R.id.f27},
            {0, 0, R.id.f28, R.id.f29, R.id.f30, 0, 0},
            {0, 0, R.id.f31, R.id.f32, R.id.f33, 0, 0},
    };


    private void log(String text) {
        Log.d("LifeCycleTest", text);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chachacha);

        registerListeners();
        gameChaChaCha = new GameChaChaCha();
        setFigureFromGrid();

        log("created");
    }

    public void onStart() {
        super.onStart();
        log("started");
    }
    private void registerListeners(){
        RadioButton button;
        for(int i = 0; i < SIZE ; i++){
            for(int j = 0; j < SIZE ; j++){
                if(ids[i][j] != 0){
                    button = (RadioButton) findViewById(ids[i][j]);
                    button.setOnClickListener(this);
                }
            }
        }
    }

    public void onClick(View v){
        int id = ((RadioButton)v).getId();
        for(int i = 0; i < SIZE ; i++){
            for(int j = 0; j < SIZE ; j++){
                if(ids[i][j] == id){
                    gameChaChaCha.play(i,j);
                    break;
                }
            }
        }
        setFigureFromGrid();
        if(gameChaChaCha.isGameFinished()){
            Toast.makeText(this, R.string.gameOverTitle, Toast.LENGTH_LONG).show();
        }
    }
    private void setFigureFromGrid(){
        RadioButton button;

        for(int i = 0; i < SIZE ; i++){
            for(int j = 0; j < SIZE ; j++){
                if(ids[i][j] != 0){
                    int value = gameChaChaCha.getGrid(i,j);
                    button = (RadioButton) findViewById(ids[i][j]);

                    if(value == 1){
                        button.setChecked(true);
                    }else{
                        button.setChecked(false);
                    }
                }
            }
        }
    }

    public void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String grid = savedInstanceState.getString("GRID");
        gameChaChaCha.stringToGrid(grid);
        setFigureFromGrid();
    }

    protected void onResume() {
        super.onResume();

        log("resumed");
    }

    protected void onPause() {
        super.onPause();

        log("paused");
    }

    public void onSaveInstanceState (Bundle outState) {
        outState.putString("GRID", gameChaChaCha.gridToString());
        super.onSaveInstanceState(outState);
    }

    protected void onStop() {
        super.onStop();
        log("stopped");
    }

    protected void onDestroy() {
        super.onDestroy();
        log("destroyed");
    }

    protected void onRestart() {
        super.onRestart();
        log("restarted");
    }
}