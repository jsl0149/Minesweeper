import java.util.Scanner;

class Gubne extends ChickenAbs {

	public Gubne(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void printName() {
		System.out.printf("%30s","������� ����ġŲ�Դϴ�\n");
		// TODO Auto-generated method stub
		
	}

	@Override
	void Menu() {
		// TODO Auto-generated method stub
		System.out.printf("%30s", "�޴��� �������ּ���\n");
		System.out.printf("%30s","-----------------------------------------\n");
		System.out.printf("%30s","1.��������   2.���߹ٻ��    3.����õ�� \n");
		System.out.printf("%30s","-----------------------------------------\n");
		
		
        Scanner sc = new Scanner(System.in);
		
	 	int Choice = sc.nextInt();
		
		
		switch(Choice) {
		
		case 1 : System.out.println(super.name + " ���������� �ֹ��ϼ̽��ϴ�.");
		         break;
		case 2 : System.out.println(super.name + " ���߹ٻ���� �ֹ��ϼ̽��ϴ�.");
				 break;
		case 3 : System.out.println(super.name + " ����õ���� �ֹ��ϼ̽��ϴ�.");
        		 break;
		
		}
		
	} 

}
