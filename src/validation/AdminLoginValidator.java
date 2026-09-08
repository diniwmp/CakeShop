
package validation;

/**
 *
 * @author Dini
 */
public class AdminLoginValidator {

    public void validateAdminLogin(String email, String password) throws Exception {

        if (email.isEmpty()) {
            throw new Exception("Please enter your email");
        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new Exception("Invalid email");
        }

        if (password.isEmpty()) {
            throw new Exception("Please enter your password");

        }
    }

}
