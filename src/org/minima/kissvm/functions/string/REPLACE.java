package org.minima.kissvm.functions.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.minima.kissvm.Contract;
import org.minima.kissvm.exceptions.ExecutionException;
import org.minima.kissvm.functions.MinimaFunction;
import org.minima.kissvm.values.StringValue;
import org.minima.kissvm.values.Value;

public class REPLACE extends MinimaFunction {

	public REPLACE() {
		super("REPLACE");
	}

	@Override
	public Value runFunction(Contract zContract) throws ExecutionException {
		checkExactParamNumber(requiredParams());

		//Get the the first string
		StringValue strmain   	= zContract.getStringParam(0, this);
		StringValue strsearch 	= zContract.getStringParam(1, this);
		StringValue strrepl 	= zContract.getStringParam(2, this);

		String main 	= strmain.toString();
		String search 	= strsearch.toString();
		String repl 	= strrepl.toString();

		//Now replace.. using safe method that checks length
		String newstr = safeReplaceAll(main, search, repl);

		return new StringValue(newstr);	
	}

	@Override
	public int requiredParams() {
		return 3;
	}
	
	@Override
	public MinimaFunction getNewFunction() {
		return new REPLACE();
	}
	
	/**
	 * Safely replace strings with length check
	 */
	public static String safeReplaceAll(String zStart, String zSearch, String zReplace) throws ExecutionException {
		
		//The final result
		StringBuffer sb = new StringBuffer();
		
		//Create a pattern and a matcher
		Pattern p 		= Pattern.compile(zSearch);
		Matcher m 		= p.matcher(zStart);
		
		//Search for occurrences
		while (m.find()) {
		    
			//Append the replacement String
			m.appendReplacement(sb, zReplace);
		    
		    //Check length
		    if(sb.length()>StringValue.MAX_STRING_LEN) {
		    	throw new ExecutionException("Replace String too long! "+sb.length());
		    }
		}
		
		//Add the rest
		m.appendTail(sb);
		
		//Final Check
		if(sb.length()>StringValue.MAX_STRING_LEN) {
	    	throw new ExecutionException("Replace String too long! "+sb.length());
	    }
		
		return sb.toString();
	}
	
	public static void main(String[] zArgs) throws ExecutionException {
		
		String start 	= "aa";
		String search 	= "a";
		
		System.out.println("0:"+start);
		
		for(int i=1;i<5;i++) {
			start = safeReplaceAll(start, search, start);
			System.out.println(i+":"+start.length());
		}	
	}
}
