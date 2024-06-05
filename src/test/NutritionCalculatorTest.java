import org.junit.jupiter.api.Test;

import User.User;
import UserNutrition;
import Nutrition.UserNutrition;


public class NutritionCalculatorTest {

  @Test
  public void testNutritionCalculation1() {

    User user = new User("testname1", "testusername1", "testemail1","testpassword1", "attacthelicopter", 16, 152, 48);
    UserNutrition calculator = new UserNutrition();

    calculator.setNutrition(user);;

    assertEquals(1370, calculator.getCal());
    assertEquals(105.82176, calculator.getProtein());
    double expectedFat = (calculator.getCal() * 0.25) / 9;
    assertEquals(expectedFat, calculator.getFat(), 0.1); // Allow a small delta for floating-point calculations
    assertEquals(133.5, calculator.getCarbs(), 0.1); // Assuming remaining calories are carbs
  }
}
