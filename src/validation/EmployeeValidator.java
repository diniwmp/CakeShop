
package validation;

import java.util.regex.Pattern;

/**
 *
 * @author Dini
 */
public class EmployeeValidator {

    public void validateEmployee(String email, String firstName, String lastName, String nic,
            String mobile, String password, String gender,
            String type, String status) throws Exception {

        if (email.isEmpty()) {
            throw new Exception("Please enter your email");
        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+"
                + "(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new Exception("Invalid email");
        }

        if (firstName.isEmpty()) {
            throw new Exception("Please enter your first name");
        }

        if (lastName.isEmpty()) {
            throw new Exception("Please enter your last name");
        }
        if (nic.isEmpty()) {
            throw new Exception("Please enter your nic");
        }

        if (mobile.isEmpty()) {
            throw new Exception("Please enter your mobile");
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            throw new Exception("Please enter valid mobile");
        }

        if (password.isEmpty()) {
            throw new Exception("Please enter your password");
        } else if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            throw new Exception("Please enter minimum eight characters, at least one letter and one number");
        }
        
        
        if (gender.equals("Select gender")) {
            throw new Exception("Please select a gender");
        }

        if (type.equals("Select type")) {
            throw new Exception("Please select a type");
        }

       
        if (status.equals("Select status")) {
            throw new Exception("Please select a status");
        }
     
   
    }
}
