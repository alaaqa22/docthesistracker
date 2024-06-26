package dtt.business.backing;

import dtt.dataAccess.repository.interfaces.UserDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

/**
 * Backing bean for verification.
 */
@RequestScoped
@Named
public class VerificationBacking implements Serializable {
    @Inject
    private UserDAO userDAO;

    /**
     * Method to verify the email address.
     */
    public void verifyEmail(){

    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
