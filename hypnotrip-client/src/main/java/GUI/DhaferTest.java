package GUI;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.persistence.Posts;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesRemote;

public class DhaferTest {

	public static void main(String[] args) throws NamingException {

		Context context = new InitialContext();

		String jndi = "hypnotrip-ear/hypnotrip-ejb/FollowersServices!com.esprit.hypnotrip.services.interfaces.FollowersServicesRemote";
		FollowersServicesRemote followersServicesRemote = (FollowersServicesRemote) context.lookup(jndi);

		// Test Insert
		String IdUser = "b38f3299-6949-42c7-9a6c-f998c66f4852";
		List<Posts> posts = followersServicesRemote.findListOfTagsOrdredByUsing(IdUser);
		System.out.println(followersServicesRemote.ListAllPagesByTags(posts));
	}

}
