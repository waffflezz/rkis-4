package ru.sfu.waffflezz.utils;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sfu.waffflezz.dao.VesselDAO;
import ru.sfu.waffflezz.models.Vessel;

/**
 * Класс с базовым меню в консоле для взаимодействия с базой данных в удобном для пользователя
 * виде
 */
@Component
public class Menu {

  /**
   * DAO для работы с объектами Vessel в базе данных
   */
  private final VesselDAO vesselDAO;

  /**
   * Конструктор меню
   *
   * @param vesselDAO DAO для работы с объектами Vessel в базе данных
   */
  @Autowired
  public Menu(VesselDAO vesselDAO) {
    this.vesselDAO = vesselDAO;
  }

  /**
   * Метод запуска консольного меню
   */
  public void start() {
    while (true) {
      printMainMenu();
      int choice = Input.intInput();
      switch (choice) {
        case 1:
          addVessel();
          break;
        case 2:
          printAll();
          break;
        case 3:
          editVessel();
          break;
        case 4:
          deleteVessel();
          break;
        case 5:
          widthFilter();
          break;
        case 6:
          System.out.println("Выход из приложения");
          System.exit(0);
        default:
          System.out.println("Неправильный ввод!");
      }
    }
  }

  /**
   * Метод для добавления новой посуды в базу данных
   */
  private void addVessel() {
    Vessel vessel = new Vessel();
    try {
      System.out.println("Введите название:");
      vessel.setName(Input.stringInput());
      System.out.println("Введите цвет:");
      vessel.setColor(Input.stringInput());
      System.out.println("Введите материал:");
      vessel.setMaterial(Input.stringInput());
      System.out.println("Введите ширину:");
      vessel.setWidth(Input.floatInput());
      System.out.println("Введите глубину:");
      vessel.setDepth(Input.floatInput());
      System.out.println("Введите цену:");
      vessel.setPrice(Input.floatInput());
      System.out.println("Введите количество товара:");
      vessel.setQuantity(Input.intInput());
      vesselDAO.insert(vessel);
      System.out.println("Товар добавлен в базу данных!");
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Метод для вывода всей посуды в базе данных
   */
  private void printAll() {
    List<Vessel> Dishes = vesselDAO.findAll();
    System.out.println("Список всей посуды:");
    for (Vessel vessel : Dishes) {
      System.out.println(vessel);
    }
  }

  /**
   * Метод для редактирования посуды в базе данных
   */
  private void editVessel() {
    System.out.println("Введите ID посуды:");
    Vessel vessel = vesselDAO.find(Input.intInput());
    if (vessel == null) {
      System.err.println("Посуда с указанным ID не была найдена!");
      return;
    }
    int choose;
    do {
      System.out.println("""
          Какое поле будем редактировать?
          1. Название
          2. Цвет
          3. Материал
          4. Ширина
          5. Глубина
          6. Цена
          7. Количество
          8. Выход
          """);

      choose = Input.intInput();
      switch (choose) {
        case 1:
          System.out.println("Введите имя:");
          vessel.setName(Input.stringInput());
          break;
        case 2:
          System.out.println("Введите цвет:");
          vessel.setColor(Input.stringInput());
          break;
        case 3:
          System.out.println("Введите материал:");
          vessel.setMaterial(Input.stringInput());
          break;
        case 4:
          System.out.println("Введите ширину:");
          try {
            vessel.setWidth(Input.floatInput());
          } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
          }
          break;
        case 5:
          System.out.println("Введите глубину:");
          try {
            vessel.setDepth(Input.floatInput());
          } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
          }
          break;
        case 6:
          System.out.println("Введите цену:");
          try {
            vessel.setPrice(Input.floatInput());
          } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
          }
          break;
        case 7:
          System.out.println("Введите количество товара:");
          try {
            vessel.setQuantity(Input.intInput());
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 8:
          vesselDAO.update(vessel);
          break;
        default:
          System.err.println("Нет такого пункта меню!");
      }
    } while (choose != 8);
  }

  /**
   * Метод для удаления посуды из базы данных
   */
  private void deleteVessel() {
    System.out.println("Введите ID посуды:");
    int result = vesselDAO.delete(Input.intInput());
    if (result == 0) {
      System.err.println("Посуды с введённым ID не нашлось!");
    }
  }

  /**
   * Метод для фильтрации по ширине для посуды в базе данных
   */
  private void widthFilter() {
    System.out.println("Введите максимальную ширину:");
    float width = Input.floatInput();
    if (width < 0) {
      System.err.println("Ширина не может быть отрицательной!");
      return;
    }

    List<Vessel> vessels = vesselDAO.filterWidth(width);
    for (Vessel vessel : vessels) {
      System.out.println(vessel);
    }
  }

  /**
   * Выводит пункты меню в консоле
   */
  private void printMainMenu() {
    System.out.println("""
        ----Меню----
        1. Добавить посуду
        2. Вывести всю посуду
        3. Редактировать посуду
        4. Удалить посуду
        5. Найти посуду с шириной ниже определенной
        6. Выход
        ------------
        """);
  }
}
