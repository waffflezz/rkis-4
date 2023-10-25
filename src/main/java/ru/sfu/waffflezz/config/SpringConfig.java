package ru.sfu.waffflezz.config;

import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Конфигурационный класс Spring для настройки базы данных и компонентов приложения
 */
@Configuration
@ComponentScan("ru.sfu.waffflezz")
@PropertySource("classpath:db.properties")
public class SpringConfig {

  /**
   * Представляет доступ к настройкам приложения
   */
  private final Environment env;

  /**
   * Конструктор класса SpringConfig
   *
   * @param env Объект Environment, предоставляющий доступ к настройкам приложения
   */
  public  SpringConfig(Environment env) {
    this.env = env;
  }

  /**
   * Определяет бин DataSource для доступа к базе данных
   *
   * @return Объект DataSource с настроенными параметрами подключения
   */
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("dataSource.driver")));
    dataSource.setUrl(env.getProperty("dataSource.url"));
    dataSource.setUsername(env.getProperty("dataSource.user"));
    dataSource.setPassword(env.getProperty("dataSource.password"));

    return dataSource;
  }

  /**
   * Определяет бин JdbcTemplate для удобной работы с базой данных через JDBC
   *
   * @return Объект JdbcTemplate, сконфигурированный для использования DataSource
   */
  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }
}
