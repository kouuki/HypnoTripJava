package GUI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.persistence.Todo;
import com.esprit.hypnotrip.services.interfaces.RateServiceRemote;
import com.esprit.hypnotrip.services.interfaces.TodoServiceRemote;

public class test {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		 RateServiceRemote remote = (RateServiceRemote) context.lookup(
				"hypnotrip-ear/hypnotrip-ejb/RateService!com.esprit.hypnotrip.services.interfaces.RateServiceRemote");
		int x =remote.getRateLevel(1);
		 System.out.println(x);
	}

}
