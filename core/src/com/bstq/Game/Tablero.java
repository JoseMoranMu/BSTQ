/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bstq.Game;

public class Tablero {

    int[][] tabla;
    int size;

    public Tablero(int size) {
        this.size=size;
        tabla = new int[size][size];
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
        for (int i = 0; i < size; i++) {
            for (int p = 0; p < size; p++) {
                int n = (int) (Math.random() * 3);
                tabla[i][p] = n;
            }
        }
    }

    public void generateNewContent() {
        for (int i = 0; i < size; i++) {
            for (int p = 0; p < size; p++) {
                if (tabla[i][p] == 88) {
                    int n = (int) (Math.random() * 3);
                    tabla[i][p] = n;
                }
            }
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
}
