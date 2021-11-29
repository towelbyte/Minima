package org.minima.system.commands.all;

import org.minima.system.commands.Command;
import org.minima.utils.json.JSONObject;

public class tutorial extends Command {

	public tutorial() {
		super("tutorial","Show a tutorial for Minima scrtipting");
	}
	
	@Override
	public JSONObject runCommand() throws Exception{
		JSONObject ret = getJSONReply();

		String tutorial = "\n"
				+ "Minima transactions are a series of inputs, a series of outputs and a variable list known as the state, which you can access from the script with STATE. The state can be accessed by all the input scripts, and is stored in the MMR database, so can be accessed by PREVSTATE in the next transaction the outputs are added to, as inputs. \n"
				+ "\n"
				+ "The sum of the outputs must be less than or equal to the sum of the inputs, for every tokenid used. The difference in raw minima is the Burn. \n"
				+ "\n"
				+ "A Minima input script returns TRUE or FALSE. The default is return FALSE, so all inputs must explicitly RETURN TRUE for the transaction to be valid.\n"
				+ "\n"
				+ "A transaction can be signed, in full, by one or more public keys.\n"
				+ "\n"
				+ "Minima allows input scripts to have full knowledge of the transaction. How many, token type, amount and the address of all inputs and outputs are available. An input knows it's own script ( @SCRIPT ) and can ensure an output of a similar address exists in the outputs. \n"
				+ "\n"
				+ "A script can run for 1024 instructions. An instruction is 1 operation or function.\n"
				+ "\n"
				+ "The addition of the state variables in the MMR Proof DB, allow for complex scripts with knowledge of their past to be created. A simple state mechanic for transactional history rather than a global state for ALL transactions.\n"
				+ "\n"
				+ "Minima tracks all coins that are to an address you possess and all coins that have a public key or address you possess in the STATE or PREVSTATE.\n"
				+ "\n"
				+ "Minima transactions are scriptable Logic Gates, with analogue inputs and outputs, a simple yet powerful control language, and a previous history state mechanic.\n"
				+ "\n"
				+ "I think of them as Script Gates.\n"
				+ "\n"
				+ "Grammar\n"
				+ "-------\n"
				+ "\n"
				+ "ADDRESS     ::= KECCAK ( BLOCK )\n"
				+ "BLOCK       ::= STATEMENT_1 STATEMENT_2 ... STATEMENT_n\n"
				+ "STATEMENT   ::= LET VARIABLE = EXPRESSION |\n"
				+ "                LET ( EXPRESSION_1 EXPRESSION_2 ... EXPRESSION_n ) = EXPRESSION |\n"
				+ "                IF EXPRESSION THEN BLOCK [ELSEIF EXPRESSION THEN BLOCK]* [ELSE BLOCK] ENDIF | \n"
				+ "                WHILE EXPRESSION DO BLOCK ENDWHILE |\n"
				+ "                EXEC EXPRESSION |\n"
				+ "                MAST EXPRESSION |\n"
				+ "                ASSERT EXPRESSION |\n"
				+ "                RETURN EXPRESSION\n"
				+ "EXPRESSION  ::= RELATION\n"
				+ "RELATION    ::= LOGIC AND LOGIC  | LOGIC OR LOGIC  |  \n"
				+ "                LOGIC XOR LOGIC  | LOGIC NAND LOGIC | \n"
				+ "                LOGIC NOR LOGIC  | LOGIC NXOR LOGIC | LOGIC\n"
				+ "LOGIC       ::= OPERATION EQ OPERATION  | OPERATION NEQ OPERATION  | \n"
				+ "                OPERATION GT OPERATION  | OPERATION GTE OPERATION  | \n"
				+ "                OPERATION LT OPERATION  | OPERATION LTE OPERATION  | OPERATION\n"
				+ "OPERATION   ::= ADDSUB & ADDSUB | ADDSUB | ADDSUB | ADDSUB ^ ADDSUB | ADDSUB\n"
				+ "ADDSUB      ::= MULDIV + MULDIV | MULDIV - MULDIV | MULDIV % MULDIV |\n"
				+ "                MULDIV << MULDIV | MULDIV >> MULDIV | MULDIV\n"
				+ "MULDIV      ::= PRIME * PRIME | PRIME / PRIME | PRIME\n"
				+ "PRIME       ::= NOT PRIME |  NEG PRIME | NOT BASEUNIT | NEG BASEUNIT | BASEUNIT\n"
				+ "BASEUNIT    ::= VARIABLE | VALUE | -NUMBER | GLOBAL | FUNCTION | ( EXPRESSION )\n"
				+ "VARIABLE    ::= [a-z]+\n"
				+ "VALUE       ::= NUMBER | HEX | STRING | BOOLEAN\n"
				+ "NUMBER      ::= ^[0-9]+(\\\\.[0-9]+)?\n"
				+ "HEX         ::= 0x[0-9a-fA-F]+\n"
				+ "STRING      ::= [UTF8_String]\n"
				+ "BOOLEAN     ::= TRUE | FALSE\n"
				+ "FALSE       ::= 0\n"
				+ "TRUE        ::= NOT FALSE\n"
				+ "GLOBAL      ::= @BLKNUM | @BLKTIME | @INPUT | @INBLKNUM | @BLKDIFF\n"
				+ "      	        @AMOUNT | @ADDRESS | @TOKENID | @COINID |\n"
				+ "                @SCRIPT | @TOTIN | @TOTOUT\n"
				+ "FUNCTION    ::= FUNC ( EXPRESSION_1 EXPRESSION_2 .. EXPRESSION_n ) \n"
				+ "FUNC        ::= CONCAT | LEN | REV | SUBSET | GET |\n"
				+ "                CLEAN | UTF8 | REPLACE | SUBSTR |\n"
				+ "                BOOL | HEX | NUMBER | STRING |\n"
				+ "                ABS | CEIL | FLOOR | MIN | MAX | INC | DEC | SIGDIG | POW |\n"
				+ "                BITSET | BITGET | BITCOUNT | PROOF | KECCAK | SHA2 |\n"
				+ "                SIGNEDBY | MULTISIG | CHECKSIG |\n"
				+ "                GETOUTADDR | GETOUTAMT | GETOUTTOK | VERIFYOUT |\n"
				+ "                GETINADDR | GETINAMT | GETINTOK | GETINID | VERIFYIN |\n"
				+ "                STATE | PREVSTATE | SAMESTATE\n"
				+ "\n"
				+ "Globals\n"
				+ "-------\n"
				+ "\n"
				+ "@BLOCK       : Block number this transaction is in\n"
//				+ "@BLKTIME     : Block time in seconds from Jan 01 1970\n"
//				+ "@PREVBLKHASH : Hash of the previous Block\n"
				+ "@INBLOCK     : Block number when this output was created\n"
				+ "@BLOCKDIFF   : Difference between @BLOCK and INBLOCK\n"
				+ "@INPUT       : Input number in the transaction\n"
				+ "@COINID      : CoinID of this input\n"
				+ "@AMOUNT      : Amount of this input\n"
				+ "@ADDRESS     : Address of this input\n"
				+ "@TOKENID     : TokenID of this input\n"
				+ "@SCRIPT      : Script for this input\n"
//				+ "@TOKENSCRIPT : Script for this input\n"
				+ "@TOTIN       : Total number of inputs for this transaction\n"
				+ "@TOTOUT      : Total number of outputs for this transaction\n"
//				+ "@FLOATING    : Is this a floating input\n"
				+ "\n"
				+ "Functions\n"
				+ "---------\n"
				+ "\n"
				+ "CONACT ( HEX_1 HEX_2 ... HEX_n )\n"
				+ "Concatenate the HEX values. \n"
				+ "\n"
				+ "LEN ( HEX|SCRIPT )\n"
				+ "Length of the data\n"
				+ "\n"
				+ "REV ( HEX )\n"
				+ "Reverse the data\n"
				+ "\n"
				+ "SUBSET ( HEX NUMBER NUMBER )\n"
				+ "Return the HEX subset of the data - start - length\n"
				+ "\n"
				+ "GET ( NUMBER NUMBER .. NUMBER )\n"
				+ "Return the array value set with LET ( EXPRESSION EXPRESSION .. EXPRESSION )  \n"
				+ "\n"
				+ "REPLACE ( STRING STRING STRING )\n"
				+ "Replace in 1st string all occurrence of 2nd string with 3rd\n"
				+ "\n"
				+ "SUBSTR ( STRING NUMBER NUMBER )\n"
				+ "Get the substring\n"
				+ "\n"
				+ "CLEAN ( STRING )\n"
				+ "Return a CLEAN version of the script\n"
				+ "\n"
				+ "UTF8 ( HEX )\n"
				+ "Convert the HEX value of a script value to a string\n"
				+ "\n"
				+ "BOOL ( VALUE )\n"
				+ "Convert to TRUE or FALSE value\n"
				+ "\n"
				+ "HEX ( SCRIPT )\n"
				+ "Convert SCRIPT to HEX\n"
				+ "\n"
				+ "NUMBER ( HEX )\n"
				+ "Convert HEX to NUMBER\n"
				+ "\n"
				+ "STRING ( HEX ) \n"
				+ "Convert a HEX value to SCRIPT\n"
				+ "\n"
				+ "ABS ( NUMBER )\n"
				+ "Return the absolute value of a number\n"
				+ "\n"
				+ "CEIL ( NUMBER )\n"
				+ "Return the number rounded up\n"
				+ "\n"
				+ "FLOOR ( NUMBER ) \n"
				+ "Return the number rounded down\n"
				+ "\n"
				+ "MIN ( NUMBER NUMBER )\n"
				+ "Return the minimum value of the 2 numbers\n"
				+ "\n"
				+ "MAX ( NUMBER NUMBER )\n"
				+ "Return the maximum value of the 2 numbers\n"
				+ "\n"
				+ "INC ( NUMBER )\n"
				+ "Increment a number\n"
				+ "\n"
				+ "DEC ( NUMBER )\n"
				+ "Decrement a number\n"
				+ "\n"
				+ "POW ( NUMBER NUMBER )\n"
				+ "Returns the power of N of a number. N must be a whole number.\n"
				+ "\n"
				+ "SIGDIG ( NUMBER NUMBER )\n"
				+ "Set the significant digits of the number\n"
				+ "\n"
				+ "BITSET ( HEX NUMBER BOOLEAN )\n"
				+ "Set the value of the BIT at that Position to 0 or 1\n"
				+ "\n"
				+ "BITGET ( HEX NUMBER ) \n"
				+ "Get the BOOLEAN value of the bit at the position.\n"
				+ "\n"
				+ "BITCOUNT ( HEX ) \n"
				+ "Count the number of bits set in a HEX value\n"
				+ "\n"
				+ "PROOF ( HEX HEX ) \n"
				+ "Recursively KECCAK hash the first HEX value with the merkle proof provided in the second. Returns the final result that can be checked in script. Use the 'chainsha' function in Minima to construct Hash Trees proofs for MAST and Signature Public Keys.   \n"
				+ "\n"
				+ "KECCAK ( HEX ) \n"
				+ "Returns the KECCAK value of the HEX value.\n"
				+ "\n"
				+ "SHA2 ( HEX ) \n"
				+ "Returns the SHA2 value of the HEX value.\n"
				+ "\n"
				+ "SIGNEDBY ( HEX )\n"
				+ "Returns true if the transaction is signed by this public key\n"
				+ "\n"
				+ "MULTISIG ( NUMBER HEX1 HEX2 .. HEXn )\n"
				+ "Returns true if the transaction is signed by N of the public keys\n"
				+ "\n"
				+ "CHECKSIG ( HEX HEX HEX)\n"
				+ "Check public key, data and signature \n"
				+ "\n"
				+ "GETOUTADDR ( NUMBER ) \n"
				+ "Return the HEX address of the specified output\n"
				+ "\n"
				+ "GETOUTAMT ( NUMBER ) \n"
				+ "Return the amount of the specified output \n"
				+ "\n"
				+ "GETOUTTOK ( NUMBER ) \n"
				+ "Return the token id of the specified output\n"
				+ "\n"
				+ "VERIFYOUT ( NUMBER HEX NUMBER HEX )\n"
				+ "Verify the specified output has the specified address, amount and tokenid\n"
				+ "\n"
				+ "GETINADDR ( NUMBER ) \n"
				+ "Return the HEX address of the specified input\n"
				+ "\n"
				+ "GETINAMT ( NUMBER ) \n"
				+ "Return the amount of the specified input\n"
				+ "\n"
				+ "GETINTOK ( NUMBER ) \n"
				+ "Return the token id of the specified input\n"
				+ "\n"
				+ "VERIFYIN ( NUMBER HEX NUMBER HEX)\n"
				+ "Verify the specified input has the specified address, amount and tokenid\n"
				+ "\n"
				+ "STATE ( NUMBER )\n"
				+ "Return the state value for the given number\n"
				+ "\n"
				+ "PREVSTATE ( NUMBER )\n"
				+ "Return the state value stored in the MMR data in the initial transaction this input was created. Allows for a state to be maintained from 1 spend to the next\n"
				+ "\n"
				+ "SAMESTATE ( NUMBER NUMBER )\n"
				+ "Return TRUE if the previous state and current state are the same for the start and end positions\n"
				+ "\n"
				+ "Examples\n"
				+ "--------\n"
				+ "\n"
				+ "LET thing = 23\n"
				+ "LET ( 12 2 ) = 45.345\n"
				+ "LET ( 0 0 1 ) = 0xFF\n"
				+ "LET ( 3 ( thing + 1 ) ) = [ RETURN TRUE ]\n"
				+ "\n"
				+ "--\n"
				+ "\n"
				+ "RETURN SIGNEDBY ( 0x12345.. )\n"
				+ "\n"
				+ "--\n"
				+ "\n"
				+ "IF SIGNEDBY ( 0x123456.. ) AND SIGNEDBY ( 0x987654.. ) THEN\n"
				+ "   RETURN TRUE\n"
				+ "ELSE IF @BLKNUM GT 198765 AND SIGNEDBY ( 0x12345.. ) THEN\n"
				+ "   RETURN TRUE\n"
				+ "ENDIF\n"
				+ "\n"
				+ "--\n"
				+ "\n"
				+ "LET x = STATE ( 23 )\n"
				+ "LET shax = KECCAK ( x )\n"
				+ "IF shax EQ 0x6785456.. AND SIGNEDBY ( 0x12345.. ) THEN \n"
				+ "  RETURN TRUE \n"
				+ "ENDIF\n"
				+ "\n"
				+ "--\n"
				+ "\n"
				+ "EXEC [ RETURN TRUE ]\n"
				+ "\n"
				+ "--\n"
				+ "\n"
				+ "MAST 0xA6657D2133E29B0A343871CAE44224BBA6BB87A972A5247A38A45D3D2065F7E4\n"
				+ "\n"
				+ "--\n"
				+ "\n"
				+ "ASSERT STATE ( 0 ) EQ INC ( PREVSTATE ( 0 ) )\n"
				+ "\n";
		
		//Add balance..
		ret.put("response", tutorial);
		
		return ret;
	}

	@Override
	public Command getFunction() {
		return new tutorial();
	}

}
