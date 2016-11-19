package GUI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.persistence.Todo;
import com.esprit.hypnotrip.services.interfaces.TodoServiceRemote;

public class test {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		 TodoServiceRemote todoService = (TodoServiceRemote) context.lookup(
				"hypnotrip-ear/hypnotrip-ejb/TodoService!com.esprit.hypnotrip.services.TodoServiceRemote");
		Todo todo = new Todo(); 
		todo.setText("Pidev2");
		 todoService.create(todo);
	}

}
