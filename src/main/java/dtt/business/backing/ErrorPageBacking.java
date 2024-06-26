package dtt.business.backing;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;

/**
 * Backing bean for the error page.
 * @author Alaa Qasem
 */

@RequestScoped
@Named
public class ErrorPageBacking implements Serializable {
    private String errorMessage;


    /**
     * Return the error message.
     *
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set the error message.
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
