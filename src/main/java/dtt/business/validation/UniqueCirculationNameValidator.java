package dtt.business.validation;

import dtt.dataAccess.repository.interfaces.CirculationDAO;
import dtt.dataAccess.utilities.Transaction;
import dtt.global.tansport.Circulation;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

/**
 * A validator that verifies the uniqueness of a given Circulation Name. It
 * checks whether the name is already present or used by another entity,
 * ensuring its uniqueness.
 *
 * @author Hadi Abou Hassoun
 */
@FacesValidator(value = "UniqueCirculationNameValidator", managed = true)
public class UniqueCirculationNameValidator implements Validator<String> {

    /** circulationDAO object for database access. */
    private CirculationDAO circulationDAO;

    /**
     *
     * @param context   FacesContext for the request we are processing
     * @param component UIComponent we are checking for correctness
     * @param value     the value to validate
     * @throws ValidatorException
     */
    @Override
    public void validate(final FacesContext context,
            final UIComponent component, final String value)
            throws ValidatorException {
        String circTitle = value;

        String originalTitle = (String) component.getAttributes().get("originalValue");
        if (circTitle.equals(originalTitle)) {
            return;
        }

        if (!isValueUnique(context, circTitle)) {
            FacesMessage msg = new FacesMessage(
                    "A circulation with the same title "
                            + "already exists in the Database.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
    }

    /**
     * check if the CirculationName is unique.
     *
     * @param context FacesContext for the request we are processing.
     * @param circulationName : The Name of the Circulation.
     * @return {@code true} if the Circulation name is unique, {@code false}
     *         otherwise.
     */
    private boolean isValueUnique(final FacesContext context,
            final String circulationName) {
        Circulation circ = new Circulation();
        circ.setTitle(circulationName);
        circulationDAO = getCirculationDAO(context);
        try (Transaction transaction = new Transaction()) {
            return !circulationDAO.findCirculationByTitle(circ, transaction);
        }
    }

    private CirculationDAO getCirculationDAO(final FacesContext context) {
        return context.getApplication().evaluateExpressionGet(context,
                "#{circulationDAO}", CirculationDAO.class);
    }

}
