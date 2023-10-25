package ru.sfu.waffflezz.models;

/**
 * Класс Vessel представляет собой посуду (тарелку, чашку)
 */
public class Vessel {

  /**
   * Идентификатор посуды
   */
  private int id;

  /**
   * Название посуды
   */
  private String name;

  /**
   * Цвет посуды
   */
  private String color;

  /**
   * Материал посуды
   */
  private String material;

  /**
   * Ширина посуды
   */
  private float width;

  /**
   * Глубина посуды
   */
  private float depth;

  /**
   * Цена посуды
   */
  private float price;

  /**
   * Количество посуды
   */
  private int quantity;

  /**
   * Конструктор без параметров
   */
  public Vessel() {
    this.name = null;
    this.color = null;
    this.material = null;
    this.width = 0;
    this.depth = 0;
    this.price = 0;
    this.quantity = 0;
  }

  /**
   * Конструктор с параметрами
   *
   * @param id Идентификатор посуды
   * @param name Название посуды
   * @param color Цвет посуды
   * @param material Материал посуды
   * @param width Ширина посуды
   * @param depth Глубина посуды
   * @param price Цена посуды
   * @param quantity Количество посуды
   */
  public Vessel(int id, String name, String color, String material, float width, float depth,
      float price, int quantity) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.material = material;

    if (width >= 0) {
      this.width = width;
    } else {
      throw new IllegalArgumentException("Ширина не может быть отрицательной!");
    }

    if (depth >= 0) {
      this.depth = depth;
    } else {
      throw new IllegalArgumentException("Глубина не может быть отрицательной!");
    }

    if (price > 0) {
      this.price = price;
    } else {
      throw new IllegalArgumentException("Цена не может быть отрицательной!");
    }

    if (quantity > 0) {
      this.quantity = quantity;
    } else {
      throw new IllegalArgumentException("Количество товара не может быть отрицательным!");
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public float getWidth() {
    return width;
  }

  public void setWidth(float width) {
    if (width >= 0) {
      this.width = width;
    } else {
      throw new IllegalArgumentException("Ширина не может быть отрицательной!");
    }
  }

  public float getDepth() {
    return depth;
  }

  public void setDepth(float depth) {
    if (depth >= 0) {
      this.depth = depth;
    } else {
      throw new IllegalArgumentException("Глубина не может быть отрицательной!");
    }
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    if (price > 0) {
      this.price = price;
    } else {
      throw new IllegalArgumentException("Цена не может быть отрицательной!");
    }
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    if (quantity > 0) {
    this.quantity = quantity;
    } else {
      throw new IllegalArgumentException("Количество товара не может быть отрицательным!");
    }
  }

  @Override
  public String toString() {
    return "---Посуда с id: " + id + "---\n" +
        "Название: " + name + '\n' +
        "Цвет: " + color + '\n' +
        "Материал: " + material + '\n' +
        "Ширина: " + width + " См.\n" +
        "Глубина: " + depth + " См.\n" +
        "Цена: " + price + " Руб.\n" +
        "Количество: " + quantity + "\n";
  }
}
