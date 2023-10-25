package ru.sfu.waffflezz.utils;

import java.util.Scanner;

/**
 * Класс для считывания данных с клавиатуры (из терминала)
 */
public class Input {

  /**
   * Статический метод для считывания целого числа
   *
   * @return Целое число int
   */
  public static int intInput() {
    Scanner scan = new Scanner(System.in);
    while (!scan.hasNextInt()) {
      System.err.println("Неправильное значение!");
      scan.next();
    }
    return scan.nextInt();
  }

  /**
   * Статический метод для считывания числа с плавающей точкой
   *
   * @return Число с плавающей точкой float
   */
  public static float floatInput() {
    Scanner scan = new Scanner(System.in);
    while (!scan.hasNextFloat()) {
      System.err.println("Неправильное значение!");
      scan.next();
    }
    return scan.nextFloat();
  }

  /**
   * Статический метод для считывания строки
   *
   * @return Строка String
   */
  public static String stringInput() {
    Scanner scan = new Scanner(System.in);
    return scan.nextLine();
  }
}
