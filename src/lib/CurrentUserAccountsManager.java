import java.io.*;
import java.util.*;
package lib;

class CurrentUserAccountsManager {
	String newUserAccount;
	String mainUserAccount;
	
	CurrentUserAccountsManager(String newAcc, String mainAcc){
		newUserAccount = newAcc;
		mainUserAccount = mainAcc;
	}
	
	int addUser(String user, String pass, String accType, float credits){
		
		try{
			FileInputStream in = new FileInputStream(mainUserAccount);
			FileWriter fw = new FileWriter(new File(mainUserAccount), true);
			
			// iterate through file
			while(in.hasNextLine()){
				String line = in.nextLine();
				String []accData = new String[3];
				
				// get data
				accData[0] = line.substring(0, 15);
				accData[1] = line.substring(16, 18);
				accData[2] = line.substring(19, 28);
				
				// if user found, return user found error
				if(accData[0].equals(user)){
					System.out.println("ERROR: User already exists");
					return(-1);
				}
			}
			// write user info into account file
			fw.write(user + " " + accType + " " + credits +"\n");
		} catch (Exception e){
			System.out.println(e.getStackTrace());
			return(-1);
		}
		return(0);
	}
	
	int deleteUser(String user){
		try{
			FileInputStream in = new FileInputStream(mainUserAccount);
			FileWriter fw = new FileWriter(new File(mainUserAccount), false);
			
			// iterate through file
			while(in.hasNextLine()){
				String line = in.nextLine();
				String []accData = new String[3];
				
				// get data
				accData[0] = line.substring(0, 15);
				accData[1] = line.substring(16, 18);
				accData[2] = line.substring(19, 28);
				
				// if not user, write the line back into file
				if(!accData[0].equals(user)){
					fw.write(accData[0] + " " + accData[1] + " " + accData[2] +"\n");
				}
			}
		} catch (Exception e){
			System.out.println(e.getStackTrace());
			return(-1);
		}
		return(0);
	}
	
	int addCredit(float amount){
		try{
			FileInputStream in = new FileInputStream(mainUserAccount);
			FileWriter fw = new FileWriter(new File(mainUserAccount), false);
			bool accountFound = false;
			
			// iterate through file
			while(in.hasNextLine()){
				String line = in.nextLine();
				String []accData = new String[3];
				
				// get data
				accData[0] = line.substring(0, 15);
				accData[1] = line.substring(16, 18);
				accData[2] = line.substring(19, 28);
				
				// if user is found, add the credits to his account
				if(accData[0].equals(user)){
					accData[2] = "" + (Float.valueOf(accData[2]) + amount);
					fw.write(accData[0] + " " + accData[1] + " " + accData[2] +"\n");
				}
			}
		} catch (Exception e){
			System.out.println(e.getStackTrace());
			return(-1);
		}
		return(0);
	}
	
	int refund(String seller, String buyer, float amount){
		try{
			FileInputStream in = new FileInputStream(mainUserAccount);
			FileWriter fw = new FileWriter(new File(mainUserAccount), false);
			bool accountFound = false;
			
			// iterate through file
			while(in.hasNextLine()){
				String line = in.nextLine();
				String []accData = new String[3];
				
				// get data
				accData[0] = line.substring(0, 15);
				accData[1] = line.substring(16, 18);
				accData[2] = line.substring(19, 28);
				
				// if buyer found, add amount into his account
				if(accData[0].equals(buyer)){
					accData[2] = "" + (Float.valueOf(accData[2]) + amount);
					fw.write(accData[0] + " " + accData[1] + " " + accData[2] +"\n");
				}
				
				// if seller found, subtract amount from his account
				if(accData[0].equals(seller)){
					accData[2] = "" + (Float.valueOf(accData[2]) - amount);
					fw.write(accData[0] + " " + accData[1] + " " + accData[2] +"\n");
				}
			}
		} catch (Exception e){
			System.out.println(e.getStackTrace());
			return(-1);
		}
		return(0);
	}
}
