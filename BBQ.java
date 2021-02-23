import java.util.Scanner;

class BBQ extends ChickenAbs{


	public BBQ(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
    enum menus{ 황금올리브 , 양념치킨 , 자메이카 };
    
	@Override
	void printName() {
		System.out.printf("%30s","어서오세요 BBQ입니다\n");
		// TODO Auto-generated method stub
		
	}

	@Override
	void Menu() {
		System.out.printf("%30s", "메뉴를 선택해주세요\n");
		System.out.printf("%30s","-----------------------------------------\n");
		System.out.printf("%30s","1.황금올리브   2.양념치킨   3.자메이칸\n");
		System.out.printf("%30s","-----------------------------------------\n");
		
		Scanner sc = new Scanner(System.in);
		
	 	int Choice = sc.nextInt();
		
		
		switch(Choice) {
		
		case 1 : System.out.println(super.name + " 황금올리브를 주문하셨습니다.");
		         break;
		case 2 : System.out.println(super.name + " 양념치킨을 주문하셨습니다.");
				 break;
		case 3 : System.out.println(super.name + " 자메이칸을 주문하셨습니다.");
        		 break;
		
		}
		
		// TODO Auto-generated method stub
		
	} 

}
