package com.prorok.minesweeper;

public class App {

  public static void main(String[] args) {
    MineSweeper mineSweeper = new MineSweeperImpl();

    String mineField =
        "**..\n*.*.\n....";

    mineSweeper.setMineField(mineField);

    long startTime1 = System.nanoTime();
    String hintField = mineSweeper.getHintField();
    long endTime1 = System.nanoTime();



    long startTime2 = System.nanoTime();
    String hintField2 = mineSweeper.getHintField2();
    long endTime2 = System.nanoTime();

    System.out.println(hintField);
    System.out.println("Hint Field 1 : " + (endTime1 - startTime1));
    System.out.println(hintField2);
    System.out.println("Hint Field 2 : " + (endTime2 - startTime2));

  }
}
