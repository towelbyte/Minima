package org.minima.system.mds.hub;

public class MDSHubLogon {

	public static final String HUB_START ="<html>\n"
			+ "\n"
			+ "<head>\n"
			+ "	<title>MDS Logon</title>\n"
			+ "</head>\n"
			+ "\n"
			+ "<body>\n"
			+ "\n"
			+ "<br><br>\n"
			+ "\n"
			+ "<center>\n"
			+ "\n"
			+ "<h2>MDS Login</h2>\n"
			+ "\n"
			+ "<form action=\"login.html\" method=\"get\">\n"
			+ "\n"
			+ "	<input type=\"text\" name=\"password\"/>\n"
			+ "	\n"
			+ "	<input type=\"submit\" value=\"login\"/>\n"
			+ "	\n"
			+ "</form>\n"
			+ "\n"
			+ "<br>\n"
			+ "Use 'minima' for now.."
			+ "</center>\n"
			+ "\n"
			+ "\n"
			+ "</body>\n"
			+ "\n"
			+ "</html>";
	
	public static String createHubPage() {
		
		//Start the HTML
		String page = HUB_START;
				
		return page;
		
	}
}
