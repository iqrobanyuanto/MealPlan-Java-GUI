NutritionCalculationTest.java

import org.junit.jupiter.api.Test;

import User.User;
import Nutrition.UserNutrition;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NutritionCalculatorTest {

  @Test
  public void testNutritionCalculation1() {

    User user = new User("testname1", "testusername1", "testemail1","testpassword1", "Male", 16, 152, 48);
    UserNutrition calculator = new UserNutrition();

    calculator.setNutrition(user);;

    assertEquals(1702, calculator.getCal());
    assertEquals(232.0, calculator.getProtein());
    assertEquals(66.0, calculator.getFat(), 0.1); // Allow a small delta for floating-point calculations
    assertEquals(43.0, calculator.getCarbs(), 0.1); // Assuming remaining calories are carbs
  }
}