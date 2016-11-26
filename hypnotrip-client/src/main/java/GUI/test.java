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
import com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceLocal;
import com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceRemote;
import com.esprit.hypnotrip.services.interfaces.UserServicesRemote;

public class test {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		// RateServiceRemote remote = (RateServiceRemote) context.lookup(
		// "hypnotrip-ear/hypnotrip-ejb/RateService!com.esprit.hypnotrip.services.interfaces.RateServiceRemote");
		// int x =remote.getRateLevel(1);
		// System.out.println(x);
		ToursiticPlaceServiceRemote remote = (ToursiticPlaceServiceRemote) context.lookup(
				"hypnotrip-ear/hypnotrip-ejb/ToursiticPlaceService!com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceRemote");
		List<Touristicplace> ls = remote.getAllTouristicPlaces();
		for (Touristicplace touristicplace : ls) {
			System.out.println(touristicplace);
			System.out.println(remote.numberOfTouristicPLaces());
		}
	}
}
