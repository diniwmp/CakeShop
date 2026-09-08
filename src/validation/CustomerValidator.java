
package validation;

/**
 *
 * @author Dini
 */
public class CustomerValidator {

    public void validateCustomer(String mobile, String firstName, String lastName, String email) throws Exception {

        if (mobile.isEmpty()) {
            throw new Exception("Please enter your mobile");
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            throw new Exception("Please enter valid mobile");
        }

        if (firstName.isEmpty()) {
            throw new Exception("Please enter your first name");
        }

        if (lastName.isEmpty()) {
            throw new Exception("Please enter your last name");

        }
        if (email.isEmpty()) {
            throw new Exception("Please enter your email");

        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+"
                + "(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new Exception("Invalid email");

        }
    }
}
