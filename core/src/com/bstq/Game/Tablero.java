package com.bstq.Game;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Jose on 14/03/2017.
 */

public class Tablero{

    int[][] tabla;


    public Tablero(int size){
        tabla = new int[size][size];
    }

    public void moveUpColumn(int col){
        int count=0;
        int temp;
        for(int i=0;i<tabla.length;i++){
            if(tabla[i][col]!=0){
                tabla[count][col]=tabla[i][col];
                tabla[i][col]=0;
            }
        }
    }
    public void moveDownColumn(int col){

    }
    public void moveLeftRow(int row){
    }
    public void moveRigthRow(int row){
    }
    public void check(){
    }
    public void generateAllContent(){
    }
    public void generateRowContent(int row){
    }
    public void generateColumnContent(int col){

    }
}
