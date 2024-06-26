package dtt.business.validation;

import dtt.global.utilities.ConfigReader;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.util.regex.Pattern;

/**
* A JSF validator for password strength.
 * @author Hadi Abou Hassoun
 */
@FacesValidator("PasswordValidator")
public class PasswordValidator implements Validator {

    private static Pattern pattern;

    /**
     *
     * @param context FacesContext for the request we are processing
     * @param component UIComponent we are checking for correctness
     * @param value the value to validate
     * @throws ValidatorException If the password is not strong enough.
     */
    @Override
    public void validate (FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;
        if(!isValidPassword (password)){
            throw new ValidatorException(new FacesMessage("Invalid password. Please enter a password that meets the required criteria"));
        }

    }

    /**
     * Checks if a password meets the required criteria.
     *
     * @param password The password to be checked.
     * @return {@code true} if the password is valid, {@code false} otherwise.
     */
    private boolean isValidPassword(String password) {
        if(!ConfigReader.arePropertiesLoaded()) {
            ConfigReader.loadProperties();
        }
        String passwordPattern = ConfigReader.getProperty (ConfigReader.PASSWORD_PATTERN);
        pattern = Pattern.compile (passwordPattern);

        return pattern.matcher(password).matches();
    }

}
