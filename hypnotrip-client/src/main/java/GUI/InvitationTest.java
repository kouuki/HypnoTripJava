package GUI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.services.exceptions.SenderIsRecieverException;
import com.esprit.hypnotrip.services.interfaces.InvitationServicesRemote;

public class InvitationTest {

	public static void main(String[] args) throws SenderIsRecieverException {

		try {
			Context context = new InitialContext();
			String jndiName = "hypnotrip-ear/hypnotrip-ejb/InvitationServices!com.esprit.hypnotrip.services.interfaces.InvitationServicesRemote";
			InvitationServicesRemote invitationServicesRemote = (InvitationServicesRemote) context.lookup(jndiName);

			// add new invitation test
			 invitationServicesRemote.saveOrUpdateInvitation(7, 0, "b38f3299-6949-42c7-9a6c-f998c66f4852", "2");

			// remove invitation test
			// Invitations invitation =
			// invitationServicesRemote.findInvitationById(1);
			// invitationServicesRemote.deleteInvitation(invitation);

			// get all my invitations test
			// List<Invitations> invitations =
			// invitationServicesRemote.getAllInvitationsByRecieverId("2");
			// for (Invitations invitation : invitations) {
			// System.out.println(invitation.toString());
			// }

			// user is invited test
			boolean response = invitationServicesRemote.isInvitedToLikeApage("2", 7);
			System.out.println(response);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
