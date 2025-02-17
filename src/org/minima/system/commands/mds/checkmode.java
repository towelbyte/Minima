package org.minima.system.commands.mds;

import org.minima.database.MinimaDB;
import org.minima.database.minidapps.MiniDAPP;
import org.minima.system.Main;
import org.minima.system.commands.Command;
import org.minima.utils.json.JSONObject;

public class checkmode extends Command {

	public checkmode() {
		super("checkmode","Show if this MiniDAPP is READ or WRITE mode");
	}
	
	@Override
	public String getFullHelp() {
		return  "checkmode\n"
				+ "\n"
				+ "Show if a MiniDAPP is READ or WRITE mode\n"
				+ "\n"
				+ "Examples:\n"
				+ "\n"
				+ "checkmode\n";
	}
	
//	@Override
//	public ArrayList<String> getValidParams(){
//		return new ArrayList<>(Arrays.asList(new String[]{"uid"}));
//	}
	
	@Override
	public JSONObject runCommand() throws Exception {
		JSONObject ret = getJSONReply();
		
		//Who called it
		String minidappid = getMiniDAPPID();
		
		JSONObject resp = new JSONObject();
		if(minidappid.equals("0x00")) {
			resp.put("name", "MINIMA");
			resp.put("mode", "WRITE");
			resp.put("public", false);
			resp.put("writemode", true);
		
		}else if(minidappid.equals(Main.getInstance().getMDSManager().getPublicMiniDAPPID())) {
			
			resp.put("name", "PUBLICMDS");
			resp.put("mode", "READ");
			resp.put("public", true);
			resp.put("writemode", false);
		
		}else {
			//Get that MiniDAPP..
			MiniDAPP md = MinimaDB.getDB().getMDSDB().getMiniDAPP(minidappid);
			
			//Return the result
			resp.put("name", md.getName());
			resp.put("mode", md.getPermission().toUpperCase());
			resp.put("writemode", md.getPermission().equalsIgnoreCase("write"));
			resp.put("public", false);
			
		}
		
		resp.put("dblocked",!MinimaDB.getDB().getWallet().isBaseSeedAvailable());
		
		ret.put("response", resp);
		
		return ret;
	}

	@Override
	public Command getFunction() {
		return new checkmode();
	}

}
