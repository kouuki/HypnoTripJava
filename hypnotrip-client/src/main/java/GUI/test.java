package GUI;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.persistence.Todo;
import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.RateServiceRemote;
import com.esprit.hypnotrip.services.interfaces.TodoServiceRemote;
import com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceRemote;
import com.esprit.hypnotrip.services.interfaces.UserServicesRemote;

public class test {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
//		 RateServiceRemote remote = (RateServiceRemote) context.lookup(
//				"hypnotrip-ear/hypnotrip-ejb/RateService!com.esprit.hypnotrip.services.interfaces.RateServiceRemote");
//		int x =remote.getRateLevel(1);
//		 System.out.println(x);
		UserServicesRemote remote = (UserServicesRemote) context.lookup(
				"hypnotrip-ear/hypnotrip-ejb/UserServices!com.esprit.hypnotrip.services.interfaces.UserServicesRemote");
	List<User> ls=remote.listBlockedUser();
	List<User> ls2=remote.listNotBlockedUser();
	System.out.println(ls);
	System.out.println(ls2);
	}
}
