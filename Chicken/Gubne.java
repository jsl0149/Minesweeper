import java.util.Scanner;

class Gubne extends ChickenAbs {

	public Gubne(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void printName() {
		System.out.printf("%30s","어서오세요 굽네치킨입니다\n");
		// TODO Auto-generated method stub
		
	}

	@Override
	void Menu() {
		// TODO Auto-generated method stub
		System.out.printf("%30s", "메뉴를 선택해주세요\n");
		System.out.printf("%30s","-----------------------------------------\n");
		System.out.printf("%30s","1.오리지날   2.고추바사삭    3.갈비천왕 \n");
		System.out.printf("%30s","-----------------------------------------\n");
		
		
        Scanner sc = new Scanner(System.in);
		
	 	int Choice = sc.nextInt();
		
		
		switch(Choice) {
		
		case 1 : System.out.println(super.name + " 오리지날을 주문하셨습니다.");
		         break;
		case 2 : System.out.println(super.name + " 고추바사삭을 주문하셨습니다.");
				 break;
		case 3 : System.out.println(super.name + " 갈비천왕을 주문하셨습니다.");
        		 break;
		
		}
		
	} 

}
