
package validation;

/**
 *
 * @author Dini
 */
public class ProductValidator {

    public void validateProduct(String id, String brand, String category, String name) throws Exception {

        if (id.isEmpty()) {
            throw new Exception("Please enter product id");
        }

        if (brand.equals("Select")) {
            throw new Exception("Please select a brand");
        }
        if (category.equals("Select")) {
            throw new Exception("Please select a category");
        }

        if (name.isEmpty()) {
            throw new Exception("Please enter product name");
        }

    }

}
