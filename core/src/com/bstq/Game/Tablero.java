/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bstq.Game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Tablero {

    int[][] tabla;
    int a=0,b=0;
    int size;
    int points;
    List content;
    int time;
    TimerTask crono;
    Timer timer;

    public Tablero(int size) {
        this.size=size;
        tabla = new int[size][size];
        content = new ArrayList();
        points=0;
        time=100;
    }

    public int getCell(int row,int column){
        return tabla[row][column];
    }

    public void moveUpColumn(int col) {
        int count = 0;
        int temp;
        for (int i = 0; i < size; i++) {
            if (tabla[i][col] != 0) {
                temp = tabla[i][col];
                tabla[i][col] = 0;
                tabla[count][col] = temp;
                count++;
            }
        }
    }

    public void moveDownColumn(int col) {
        int count = tabla[col].length - 1;
        int temp;
        for (int i = size- 1; i >= 0; i--) {
            if (tabla[i][col] != 0) {
                temp = tabla[i][col];
                tabla[i][col] = 0;
                tabla[count][col] = temp;
                count--;
            }
        }
    }

    public void moveLeftRow(int row) {
        int count = 0;
        int temp;
        for (int i = 0; i < size; i++) {
            if (tabla[row][i] != 0) {
                temp = tabla[row][i];
                tabla[row][i] = 0;
                tabla[row][count] = temp;
                count++;
            }
        }
    }

    public void moveRigthRow(int row) {
        int count = tabla.length - 1;
        int temp;
        for (int i = size-1; i >= 0; i--) {
            if (tabla[row][i] != 0) {
                temp = tabla[row][i];
                tabla[row][i] = 0;
                tabla[row][count] = temp;
                count--;

            }
        }
    }

    public boolean check() {
        boolean b = false;
        if (checkHorizontal() || checkVertical()) {
            b = true;
        }
        return b;
    }

    public void generateAllContent() {
        LoadContent();
        Random rndm = new Random();
        rndm.setSeed(System.nanoTime());
        Collections.shuffle(content, rndm);
        int index=0;
        for (int i = 0; i < size; i++) {
            for (int p = 0; p < size; p++) {
                tabla[i][p] = Integer.parseInt(content.get(index).toString());
                index++;
            }
        }
    }

    private void LoadContent() {
        for(int i=0;i<36;i++){
            if(a<12){
                content.add(1);
                a++;
            }else if(b<12){
                content.add(2);
                b++;
            }else{
                content.add(0);
            }
        }

    }

    public void generateNewContent() {
        int contador=0;
        for (int i = 0; i < size; i++) {
            for (int p = 0; p < size; p++) {
                if (tabla[i][p] == 88) {
                    int n = (int) (Math.random() * 2)+1;
                    tabla[i][p] = n;
                    contador++;
                }
            }
        }
        if(contador!=0) {
            points += (contador / 6) * 10;
        }
    }

    public void printTablero() {
        System.out.println("----------------------");
        for (int i = 0; i < size; i++) {
            for (int p = 0; p < size; p++) {
                System.out.print("[" + tabla[i][p] + "] ");
            }
            System.out.println("");
        }
        System.out.println("----------------------");
    }

    private boolean checkHorizontal() {
        boolean b = false;
        int cont = 0;
        for (int i = 0; i < size; i++) {
            for (int p = 1; p < size; p++) {
                if ((tabla[i][p - 1] == tabla[i][p])&&(tabla[i][p]!=0)) {
                    cont++;
                }
            }
            if (cont == 5) {
                for (int p = 0; p < size; p++) {
                    tabla[i][p] = 88;

                }
                b = true;
            }
            cont = 0;
        }
        return b;
    }

    private boolean checkVertical() {
        boolean b = false;
        int cont = 0;
        for (int p = 0; p < size; p++) {
            for (int i = 1; i < size; i++) {
                if (tabla[i - 1][p] == tabla[i][p]&&(tabla[i][p]!=0)) {
                    cont++;
                }
            }
            if (cont == 5) {
                for (int i = 0; i < size; i++) {
                    tabla[i][p] = 88;

                }
                b = true;
            }
            cont = 0;
        }
        return b;
    }
    public int getPoints(){

        return points;
    }
    public void startTimer(){
        crono = new TimerTask() {
            @Override
            public void run() {
                time--;
                if(time==0) this.cancel();
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(crono, 0, 1000);
    }
    public int getTime(){
        return time;
    }
}
