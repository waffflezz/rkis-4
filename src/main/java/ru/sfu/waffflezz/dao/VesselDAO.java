package ru.sfu.waffflezz.dao;

import java.sql.Types;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sfu.waffflezz.models.Vessel;

/**
 * Класс VesselDAO представляет собой объект доступа к данным для работы с сущностями Vessel.
 * Он обеспечивает доступ к базе данных для выполнения CRUD операций, таких как создание, чтение, обновление и удаление,
 * а также выполнение дополнительных запросов для фильтрации данных
 */
@Component
public class VesselDAO {

  /**
   * Объект для доступа к базе данных
   */
  private final JdbcTemplate jdbcTemplate;

  /**
   * Конструктор класса VesselDAO
   *
   * @param jdbcTemplate Объект JdbcTemplate, который предоставляет доступ к базе данных
   */
  public VesselDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * Получает список всей посуды из базы данных.
   *
   * @return Список посуды.
   */
  public List<Vessel> findAll() {
    List<Vessel> vessels = jdbcTemplate.query("SELECT * FROM Dishes",
        new BeanPropertyRowMapper<>(Vessel.class));
    return vessels;
  }

  /**
   * Находит посуду по её идентификатору.
   *
   * @param id Идентификатор посуды.
   * @return Посуда с указанным идентификатором или null, если посуда не найдена.
   */
  public Vessel find(int id) {
    try {
      return jdbcTemplate.queryForObject("SELECT * FROM Dishes WHERE id = ?",
          new BeanPropertyRowMapper<>(Vessel.class), id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Вставляет новую посуду в базу данных.
   *
   * @param vessel Посуда для вставки.
   */
  public void insert(Vessel vessel) {
    String sql = "INSERT INTO Dishes(name, color, material, width, depth, price, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(
        sql,
        vessel.getName(),
        vessel.getColor(),
        vessel.getMaterial(),
        vessel.getWidth(),
        vessel.getDepth(),
        vessel.getPrice(),
        vessel.getQuantity()
    );
  }

  /**
   * Обновляет информацию о посуде в базе данных.
   *
   * @param vessel Посуда с обновленными данными.
   */
  public void update(Vessel vessel) {
    String sql = "UPDATE Dishes SET "
        + "name = ?, "
        + "color = ?, "
        + "material = ?, "
        + "width = ?, "
        + "depth = ?, "
        + "price = ?, "
        + "quantity = ? "
        + "WHERE id = ?";

    jdbcTemplate.update(
        sql,
        vessel.getName(),
        vessel.getColor(),
        vessel.getMaterial(),
        vessel.getWidth(),
        vessel.getDepth(),
        vessel.getPrice(),
        vessel.getQuantity(),
        vessel.getId()
    );
  }

  /**
   * Удаляет посуду из базы данных по её идентификатору.
   *
   * @param id Идентификатор посуды для удаления.
   * @return Количество удаленных записей (1, если успешно, 0, если посуда с указанным идентификатором не найдена).
   */
  public int delete(int id) {
    return jdbcTemplate.update("DELETE FROM Dishes WHERE id = ?", id);
  }

  /**
   * Фильтрует посуды по максимальной ширине.
   *
   * @param maxWidth Максимальная ширина посуды для фильтрации.
   * @return Список посуды, у которой ширина меньше или равна указанной максимальной ширине.
   */
  public List<Vessel> filterWidth(float maxWidth) {
    return jdbcTemplate.query("SELECT * FROM Dishes WHERE width <= ?",
        new BeanPropertyRowMapper<>(Vessel.class), maxWidth);
  }
}
