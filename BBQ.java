import java.util.Scanner;

class BBQ extends ChickenAbs{


	public BBQ(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
    enum menus{ Ȳ�ݿø��� , ���ġŲ , �ڸ���ī };
    
	@Override
	void printName() {
		System.out.printf("%30s","������� BBQ�Դϴ�\n");
		// TODO Auto-generated method stub
		
	}

	@Override
	void Menu() {
		System.out.printf("%30s", "�޴��� �������ּ���\n");
		System.out.printf("%30s","-----------------------------------------\n");
		System.out.printf("%30s","1.Ȳ�ݿø���   2.���ġŲ   3.�ڸ���ĭ\n");
		System.out.printf("%30s","-----------------------------------------\n");
		
		Scanner sc = new Scanner(System.in);
		
	 	int Choice = sc.nextInt();
		
		
		switch(Choice) {
		
		case 1 : System.out.println(super.name + " Ȳ�ݿø��긦 �ֹ��ϼ̽��ϴ�.");
		         break;
		case 2 : System.out.println(super.name + " ���ġŲ�� �ֹ��ϼ̽��ϴ�.");
				 break;
		case 3 : System.out.println(super.name + " �ڸ���ĭ�� �ֹ��ϼ̽��ϴ�.");
        		 break;
		
		}
		
		// TODO Auto-generated method stub
		
	} 

}
