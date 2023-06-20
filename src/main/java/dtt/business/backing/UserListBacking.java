package dtt.business.backing;

import dtt.business.utilities.Pagination;
import dtt.business.utilities.SessionInfo;
import dtt.dataAccess.exceptions.InvalidInputException;
import dtt.dataAccess.repository.postgres.UserDAO;
import dtt.dataAccess.utilities.Transaction;
import dtt.global.tansport.Circulation;
import dtt.global.tansport.Faculty;
import dtt.global.tansport.User;
import dtt.global.tansport.UserState;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.logging.annotations.Pos;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Backing bean for the user list page.
 *
 * @author Alaa Aasem
 */
@ViewScoped
@Named
public class UserListBacking implements Serializable {
    private User filter;
    private Pagination<User> userPagination;
    private List<User> users;

    @Inject
    private UserDAO userDAO;

    @Inject
    private SessionInfo sessionInfo;
    private final Logger logger = LogManager.getLogger ();
    int totalNumberOfPages;

    /**
     * Constructs a new instance of UserListBacking.
     * Initializes the userPagination object.
     */
    public UserListBacking () {
        logger.fatal ("constructur User LIst ");
        userPagination = createPagination ();
    }

    /**
     * Creates a Pagination object for managing user list pagination.
     * Defines how data is loaded and how the total number of pages is calculated.
     *
     * @return A new Pagination object
     */
    private Pagination<User> createPagination () {
        return new Pagination<User> () {
            @Override
            public void loadData () {
                logger.fatal ("Start LoadData() in User List ");
                int currentPage = this.getCurrentPage ();
                int maxItems = getMaxItems ();
                logger.fatal ("currentPage : "+currentPage);
                logger.fatal ("maxItems : "+maxItems);
                if (currentPage <= 0 || maxItems <= 0) {
                    logger.error ("Invalid currentPage or maxItems value.");
                }

                int offset = (currentPage - 1) * maxItems;
                int count = maxItems;
                logger.fatal ("offset : "+offset);
                logger.fatal ("count : "+count);

                try (Transaction transaction = new Transaction()) {
                    Faculty faculty = null;
                    UserState auth = null;
                    users = userDAO.getUsers (filter,faculty,auth,transaction,offset,count);
                    transaction.commit();  // Ensure that the transaction is committed.
                }
            }

            @Override
            public int getTotalNumOfPages () {

                try(Transaction t = new Transaction ()) {
                    int totalNumOfPages = (int) Math.ceil ((double) (userDAO.getTotalUserNumber (filter, null, null, t))
                            / maxItems);
                    logger.fatal ("totalNumOfPages : " + totalNumOfPages);
                    this.totalNumOfPages = totalNumOfPages;
                    return totalNumOfPages;
                }
            }
        };
    }


    // Creates a new User object to be used as a filter and loads the first page of users.
    @PostConstruct
    public void init () {
        filter = new User ();
        loadUsers ();
    }

    public void loadUsers () {
        logger.fatal ("Start loadUsers()");
        userPagination.loadData ();
    }

    public Pagination<User> getUserPagination () {
        return userPagination;
    }


    public void setUserPagination (Pagination<User> userPagination) {
        this.userPagination = userPagination;
    }

    public void setUsers (List<User> users) {
        this.users = users;
    }

    public List<User> getUsers () {
        return users;
    }

    public int getTotalNumberOfPages () {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages (int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public void setFilter (User filter) {
        this.filter = filter;
    }

    public User getFilter () {
        return filter;
    }
}

