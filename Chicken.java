import java.util.InputMismatchException;
import java.util.Scanner;

public class Chicken {

	static void print(ChickenAbs kbc) {
		
		kbc.printName();
		
	}
	
	static void Menu(ChickenAbs kbc) {
		
		kbc.Menu();
		
	}
	
	private static boolean isNumber(String choice) {
		boolean isNum = true;
		
		for(char c : choice.toCharArray()) {
			if(c>48 && c<=57) {
				continue;
			}
			else {
				isNum = false;
				return false;
			}
			
		}
		return true;
		// TODO Auto-generated method stub
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ChickenAbs BBQ; ChickenAbs Gubne; 
		Gubne = new Gubne("Gubne");
		BBQ = new BBQ("BBQ");
		
		System.out.println("1. BBQ 2. ���� �����Ϸ��� -1�� �Է��ϼ���");
		
		Scanner sc = new Scanner(System.in);
		
		String Choice = "";
		
        Choice = sc.nextLine();
		
        while(!isNumber(Choice)) {
        	
        	System.out.print("���ڰ� �ƴմϴ� �ٽ� �Է��ϼ���: ");
        	
        	Choice = sc.nextLine();
        	
        }
		
		
		while(Choice != "-1") {		
			
			switch(Choice) {
		
				case "1" : print(BBQ);
				           Menu(BBQ);
						break;
				case "2" : print(Gubne);
							Menu(Gubne);
						break;	
		
			}
			
			Choice = sc.nextLine();
			
			if(Choice.equals("-1")) {
				break;
			}
			
			 while(!isNumber(Choice)) {
		        	
		        	System.out.print("���ڰ� �ƴմϴ� �ٽ� �Է��ϼ���: ");
		        	
		        	Choice = sc.nextLine();
		        	
		        }
			
		
			
		}	
		
		
		System.out.println("�̿����ּż� �����մϴ�");
		
	}

	
	
	
}


