package dtt.dataAccess.repository.Postgres;

import java.util.List;

import dtt.dataAccess.exceptions.DataNotCompleteException;
import dtt.dataAccess.exceptions.DataNotFoundException;
import dtt.dataAccess.exceptions.InvalidInputException;
import dtt.dataAccess.exceptions.KeyExistsException;
import dtt.dataAccess.utilities.Transaction;
import dtt.global.tansport.User;

/**
 * 
 * @author Stefan Witka
 *
 */
public class UserDAO implements dtt.dataAccess.repository.interfaces.UserDAO {

	/**
	 * Constructor for UserDAO
	 */
	public UserDAO() {
		
	}

	@Override
	public void add(User user, Transaction transaction)
			throws DataNotCompleteException, KeyExistsException, InvalidInputException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(User user, Transaction transaction) throws DataNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Transaction transaction)
			throws DataNotFoundException, InvalidInputException, KeyExistsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getUserById(User user, Transaction transaction) throws DataNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean findUserByEmail(User user, Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getUsers(User user, Transaction transaction, int offset, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalUserNumber(User user, Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

}
