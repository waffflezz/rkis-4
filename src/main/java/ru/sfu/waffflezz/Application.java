package ru.sfu.waffflezz;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sfu.waffflezz.config.SpringConfig;
import ru.sfu.waffflezz.utils.Menu;

public class Application {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    Menu menu = context.getBean(Menu.class);
    menu.start();
    context.close();
  }
}
