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
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("BMW", 12);
        Car car2 = new Car("Mercedes", 13);

        User user11 = new User("User11", "Lastname11", "user11@mail.ru");
        user11.setCar(car1);

        User user22 = new User("User22", "Lastname22", "user22@mail.ru");
        user22.setCar(car2);

        userService.add(user11);
        userService.add(user22);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();

        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car model = " + (user.getCar() == null ? null : user.getCar().getModel()));
            System.out.println("Car series = " + (user.getCar() == null ? null : user.getCar().getSeries()));
            System.out.println();
        }

        System.out.println(userService.getUserByCarModelAndSeries("BMW", 12).getFirstName());

        context.close();
    }
}
