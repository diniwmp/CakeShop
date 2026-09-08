
package validation;

/**
 *
 * @author Dini
 */
public class SupplierValidator {

    public void validateSupplier(String mobile, String fname, String lname, String email, String company) throws Exception {

        if (mobile.isEmpty()) {
            throw new Exception("Please enter mobile number");
        } else if (!mobile.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$")) {
            throw new Exception("Please enter valid mobile number");
        }

        
        if (fname.isEmpty()) {
            throw new Exception("Please enter first name");
        }
        
        if (lname.isEmpty()) {
            throw new Exception("Please enter last name");
        }
        

        if (email.isEmpty()) {
            throw new Exception("Please enter your email");
        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+"
                + "(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new Exception("Invalid email");
        }
        
        if (company.equals("Select Company")){
            throw new Exception("Please select a company");
        }

    }
}
