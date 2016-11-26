package GUI;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.UserServicesRemote;

public class WSTest {

	public static void main(String[] args) {

		try {
			Context context = new InitialContext();
			String jndiName = "hypnotrip-ear/hypnotrip-ejb/UserServices!com.esprit.hypnotrip.services.interfaces.UserServicesRemote";
			UserServicesRemote userServicesRemote = (UserServicesRemote) context.lookup(jndiName);

			List<User> friends = userServicesRemote.getAllFriendsByUserId("b38f3299-6949-42c7-9a6c-f998c66f4852");
			for (User user : friends) {
				System.out.println(user.toString());
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
