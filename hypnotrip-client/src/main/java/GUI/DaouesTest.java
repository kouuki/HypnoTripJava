package GUI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.services.interfaces.FollowersServicesRemote;

public class DaouesTest {

	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub

		Context context = new InitialContext();

		String jndi = "hypnotrip-ear/hypnotrip-ejb/FollowersServices!com.esprit.hypnotrip.services.interfaces.FollowersServicesRemote";
		FollowersServicesRemote followersServicesRemote = (FollowersServicesRemote) context.lookup(jndi);

		// Test Insert
		String IdUser = "b38f3299-6949-42c7-9a6c-f998c66f4852";
		Integer IdPage = 1;
		//followersServicesRemote.SaveOrUpdate(IdUser, IdPage, true);
		followersServicesRemote.SaveOrUpdate(IdUser, IdPage, false);
		//followersServicesRemote.SaveOrUpdate(IdUser, IdPage, true);

		// Test List
		System.out.println(followersServicesRemote.findAllFollowByUserId(IdUser));

	}

}
