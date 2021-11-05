package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Ivan", "Petrov", "ivan@mail.ru", new Car("BMW", 7)));
      userService.add(new User("Mariya", "Ivanova", "maria@mail.ru", new Car("Mazda", 5)));
      userService.add(new User("Fedor", "Smirnov", "fedor@mail.ru", new Car("Mercedes", 999)));
      userService.add(new User("Stepan", "Ivanov", "stepan@mail.ru", new Car("Audi", 3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + " series: " + user.getCar().getSeries());
         System.out.println();
      }

      User user1 = userService.getUserByCarModelAndSeries("BMW", 7);
      System.out.println(user1);

      context.close();
   }
}
