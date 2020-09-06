package telran.ashkelon2020.person.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 712602907332683310L;

	public UserNotFoundException(Integer login) {
		super("User " + login + " not found");
	}
}
