import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Minesweeper extends JFrame {
	public int firstCount        = 0; //첫클릭 카운트
	public int endCount          = 0; //지뢰를 제외한 타일이 몇개 오픈됐는지 카운트 (타일-지뢰수)가 되면 게임종료
	JPanel menuPanel             = new JPanel(new BorderLayout()); // 메뉴와 지뢰를 같이 넣을 패널
	JPanel menuPanel2            = new JPanel(new BorderLayout()); // ui용 패널
	public JButton btn [][]      = new JButton [100][100]; // 지뢰버튼들
	public JFrame frame          = new JFrame(); // 프레임
	public JPanel panelLv1       = new JPanel(new GridLayout(0,10)); //지뢰타일 넣을 패널
	public JPanel panelLv2       = new JPanel(new GridLayout(0,20)); //지뢰타일 넣을 패널
	public int mineRoom[][]      = new int[100][100]; //2차원 배열로 각타일에 숫자를 부여 빈칸과, 지뢰, 주변에 지뢰수가 들어가있는 버튼을 구분
	public int openCheck[][]     = new int[100][100]; // 타일이 열린 상태라면 상수 OPEN을 넣어 열린것을 확인
	public int endCheck[][]      = new int [100][100]; // 타일이 한번 열린곳에는 endCount를 올리지 않게 구분해주는 배열 openTile 메서드의 로직이 재귀라 메모리 잡아먹는 방법인것같다.
	public int clickCheck[][]    = new int [100][100];
	public int mineX[]           = new int[100]; // 지뢰의 X좌표 저장
	public int mineY[]           = new int[100]; //지뢰의 Y좌표 저장
	final int SIZE_OF_PANEL_GARO = 10; //지뢰판 크기 설정
	final int SIZE_OF_PANEL_SERO = 10; // 지뢰판 크기 설정
	final int NUM_OF_MINE        = 12;
	final int OPEN               = 2;
	boolean loadEmptyCheck       = false;
	private Timer timer          = new Timer();             //타이머 
	

        public Minesweeper(){
		
		
		
		playLevel1();
		makeMenu();
		
		
		menuPanel2.add(timer,BorderLayout.NORTH);
		menuPanel2.add(new JLabel("Lv1 " + NUM_OF_MINE + "개"),BorderLayout.CENTER);
		menuPanel2.add(panelLv1,BorderLayout.SOUTH);
		menuPanel.add(menuPanel2,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(menuPanel);
	 	frame.setTitle("지뢰찾기 ");
	 	frame.setSize(428,440);
	   
	 	frame.setLocationRelativeTo(null); 
	    frame.setVisible(true);      
		
		
		}
	
	
	
      
	
	

	public void ButtonAction(int x, int y, int GARO, int SERO) {
		
            if(mineRoom[x][y] == 0 ) {
			
			btn[x][y].setBackground(Color.WHITE);
			btn[x][y].setText(" ");
			clickCheck[x][y] = 3;
			openCheck[x][y]=2;
			

			if(x!=0 && 0<=y && y<=GARO-1 && mineRoom[x-1][y] == 0 ) {
				btn[x-1][y].setBackground(Color.WHITE);
				btn[x-1][y].setText(" ");
				clickCheck[x-1][y] = 3;
			}
			else if(x!=0 && 0<=y && y<=GARO-1 && mineRoom[x-1][y] > 0 ) {
				btn[x-1][y].setText(Integer.toString(mineRoom[x-1][y]));
			    openCheck[x-1][y]=OPEN;
			    clickCheck[x-1][y] = 3;
			    btn[x-1][y].setBackground(null);
			    
			}
			else;
			//위
			
			if(x!=SERO-1 && 0<=y && y<=GARO-1 && mineRoom[x+1][y] == 0 ) {
				btn[x+1][y].setBackground(Color.WHITE);
				btn[x+1][y].setText(" ");
				clickCheck[x+1][y] = 3;
				
			}
			else if(x!=SERO-1 && 0<=y && y<=GARO-1 && mineRoom[x+1][y] > 0 ) {
				btn[x+1][y].setText(Integer.toString(mineRoom[x+1][y]));
				openCheck[x+1][y]=OPEN;
				btn[x+1][y].setBackground(null);
				clickCheck[x+1][y] = 3;
			}
			else;
			//아래
			
			if(y!=0 && 0<=x && x<=SERO-1 && mineRoom[x][y-1] == 0 ) {
				btn[x][y-1].setBackground(Color.WHITE);
				btn[x][y-1].setText(" ");
				clickCheck[x][y-1] = 3;
				
			}
			else if(y!=0 && 0<=x && x<=SERO-1 && mineRoom[x][y-1] > 0 ) {
				btn[x][y-1].setText(Integer.toString(mineRoom[x][y-1]));
				openCheck[x][y-1]=OPEN;
				btn[x][y-1].setBackground(null);
				clickCheck[x][y-1] = 3;
			}
			else;
			//왼쪽
			
			if(y!=GARO-1 && 0<=x && x<=SERO-1 && mineRoom[x][y+1] == 0 ) {
				btn[x][y+1].setBackground(Color.WHITE);
				btn[x][y+1].setText(" ");
				clickCheck[x][y+1] = 3;
			
			}
			else if(y!=GARO-1 && 0<=x && x<=SERO-1 && mineRoom[x][y+1] > 0 ) {
				btn[x][y+1].setText(Integer.toString(mineRoom[x][y+1]));
				openCheck[x][y+1]=OPEN;
				btn[x][y+1].setBackground(null);
				clickCheck[x][y+1] = 3;
			}
			else;
			//오른쪽
			
			if(x!=0 && 1<=y && y<=GARO-1 && mineRoom[x-1][y-1] == 0 ) {
				btn[x-1][y-1].setBackground(Color.WHITE);
				btn[x-1][y-1].setText(" ");
				clickCheck[x-1][y-1] = 3;
			}
			else if(x!=0 && 1<=y && y<=GARO-1 && mineRoom[x-1][y-1] > 0 ) {
				btn[x-1][y-1].setText(Integer.toString(mineRoom[x-1][y-1]));
				openCheck[x-1][y-1]=OPEN;
				btn[x-1][y-1].setBackground(null);
				clickCheck[x-1][y-1] = 3;
			}
			else;
			//대각선왼쪽위
			
			if(x!=0 && 0<=y && y<=GARO-2 && mineRoom[x-1][y+1] == 0) {
				btn[x-1][y+1].setBackground(Color.WHITE);
				btn[x-1][y+1].setText(" ");
				clickCheck[x-1][y+1] = 3;
			}
			else if(x!=0 && 0<=y && y<=GARO-2 && mineRoom[x-1][y+1] > 0 ) {
				btn[x-1][y+1].setText(Integer.toString(mineRoom[x-1][y+1]));
				openCheck[x-1][y+1]=OPEN;
				btn[x-1][y+1].setBackground(null);
				clickCheck[x-1][y+1] = 3;
			}
			else;
		    //대각선오른쪽위
			
			if(x!=SERO-1 && 1<=y && y<=GARO-1 && mineRoom[x+1][y-1] == 0 ) {
				btn[x+1][y-1].setBackground(Color.WHITE);
				btn[x+1][y-1].setText(" ");
				clickCheck[x+1][y-1] = 3;
			}
			else if(x!=SERO-1 && 1<=y && y<=GARO-1 && mineRoom[x+1][y-1] > 0 ) {
				btn[x+1][y-1].setText(Integer.toString(mineRoom[x+1][y-1]));
				openCheck[x+1][y-1]=OPEN;
				btn[x+1][y-1].setBackground(null);
				clickCheck[x+1][y-1] = 3;
			}
			else;
		    //대각선왼쪽아래
			
			if(x!=SERO-1 && 0<=y && y<=GARO-2 && mineRoom[x+1][y+1] == 0) {
				btn[x+1][y+1].setBackground(Color.WHITE);
				btn[x+1][y+1].setText(" ");
				clickCheck[x+1][y+1] = 3;
			}
		    else if(x!=SERO-1 && 0<=y && y<=GARO-2 && mineRoom[x+1][y+1] > 0 ) {
		    	btn[x+1][y+1].setText(Integer.toString(mineRoom[x+1][y+1]));
		    	openCheck[x+1][y+1]=OPEN;
		    	btn[x+1][y+1].setBackground(null);
		    	clickCheck[x+1][y+1] = 3;
		    }
			else;
		    //대각선오른쪽아래
			
			
		}
		
            else if(10 > mineRoom[x][y]  && mineRoom[x][y] > 0) {
    			
    			btn[x][y].setText(Integer.toString(mineRoom[x][y]));
    			btn[x][y].setBackground(null);
    			openCheck[x][y] = OPEN;
    		
    			
    		}	
    		
    		else if(mineRoom[x][y] >= 10){
    	

    			for(int i = 0;i<SIZE_OF_PANEL_SERO;i++) {
    				
    				for(int j=0;j<SIZE_OF_PANEL_GARO;j++) {
    					
    					if(mineRoom[i][j] >= 10) {
    						btn[i][j].setBackground(Color.RED);
    						btn[i][j].setText(" ");
    					}
    			
    				}
    			}
    			timer.stop = false;
    			JOptionPane.showMessageDialog(null, "지뢰를 밟았습니다. 게임을 종료합니다.");
    			
    			System.exit(0);
    		}
            
	}
	
	// 0인 타일을 클릭하면 4방향이 오픈되는 함수
	
    public void openTile(int x, int y, int GARO, int SERO) {
    	
        ButtonAction(x,y,GARO,SERO);
    	
        if(x!=0 && 0<=y && y<=GARO-1 && mineRoom[x-1][y] == 0 && openCheck[x-1][y]!=OPEN)
        	openTile(x-1,y,GARO,SERO);
      
        if(x!=SERO-1 && 0<=y && y<=GARO-1 && mineRoom[x+1][y] == 0 && openCheck[x+1][y]!=OPEN)
        	openTile(x+1,y,GARO,SERO);		
    	
        if(y!=0 && 0<=x && x<=SERO-1 && mineRoom[x][y-1] == 0 && openCheck[x][y-1]!=OPEN)
        	openTile(x,y-1,GARO,SERO);
        
        if(y!=GARO-1 && 0<=x && x<=SERO-1 && mineRoom[x][y+1] == 0 && openCheck[x][y+1]!=OPEN)
        	openTile(x,y+1,GARO,SERO);
        
        if(x!=0 && 1<=y && y<=GARO-1 && mineRoom[x-1][y-1] == 0 && openCheck[x-1][y-1]!=OPEN)
        	openTile(x-1,y-1,GARO,SERO);
        
        if(x!=0 && 0<=y && y<=GARO-2 && mineRoom[x-1][y+1] == 0 && openCheck[x-1][y+1]!=OPEN)
        	openTile(x-1,y+1,GARO,SERO);
        
        if(x!=SERO-1 && 1<=y && y<=GARO-1 && mineRoom[x+1][y-1] == 0 && openCheck[x+1][y-1]!=OPEN)
        	openTile(x+1,y-1,GARO,SERO);
        
        if(x!=SERO-1 && 0<=y && y<=GARO-2 && mineRoom[x+1][y+1] == 0 && openCheck[x+1][y+1]!=OPEN)
        	openTile(x+1,y+1,GARO,SERO);
        
    }
	
    //타일을 오픈하는 함수 (재귀함수이용)
	
	
public void randomMine(int GARO,int SERO, int NumOfMine, int first1, int first2) {
		
		int upleftx = first1 - 1; int uplefty = first2 -1;
		int upx = first1-1; int upy = first2;
		int uprightx = first1-1; int uprighty = first2+1;
		int leftx = first1; int lefty = first2-1;
		int rightx = first1; int righty = first2+1;
		int downleftx = first1 + 1; int downlefty = first2 -1;
		int downx = first1 + 1; int downy = first2;
		int downrightx = first1 + 1; int downrighty = first2 + 1;
		
		// first1, first2로 들어온 첫 클릭의 좌표를 둘러싼 좌표들을 변수에 대입한다.
         
         for(int i=0;i<NumOfMine;i++) {
      	   int random = (int) (Math.random()*SERO); 
				   mineX[i] = random;
      	   int random2 = (int) (Math.random()*GARO); 
			       mineY[i] = random2;
			       
			       if((upleftx==mineX[i] && uplefty == mineY[i])||(upx==mineX[i] && upy == mineY[i]) || (uprightx==mineX[i] && uprighty == mineY[i]) ||
		      	      (leftx==mineX[i] && lefty == mineY[i]) || (rightx==mineX[i] && righty == mineY[i])||(downleftx==mineX[i] && downlefty == mineY[i]) || 
		      	      (downx==mineX[i] && downy == mineY[i]) || (downrightx==mineX[i] && downrighty == mineY[i]) || (first2==mineY[i]&&first1==mineX[i])) 
			           i--; // 첫클릭을 포함하고 첫클릭 좌표들을 둘러싼 좌표들이 지뢰변수에 들어가면 i를 감소시켜 다시 대입한다.
			    
      	     for(int j = 0;j<i;j++) {
      		   if (mineY[i] == mineY[j] && mineX[i]==mineX[j]) {
      			       i--; j=0; break;
   			     } // i루프 안에서 넘어온 선별된 값들중 앞에 이미 같은 값이 들어가있으면 i를 감소시켜 다시 대입시킨다.
      			 
      		  }    
      	  }
      	   
         
		// 지뢰의 좌표를 설정
		
		
		for(int i=0;i<SERO;i++) {
			for(int j=0;j<GARO;j++) {
	            		  mineRoom[i][j] = 0;
	         }
	            	  
		}  //우선 버튼들에 대입하는 mineRoom배열을 모두 0으로 설정 mineRoom(first1,first2)과 그 주변들은 어차피 0일 거라서 이렇게해도 상관없다. 지뢰의 좌표가 중요.
		
		
		
		  for(int i=0;i<SERO;i++) {
				
				 for(int j=0;j<GARO;j++) {
					
		              for(int k = 0; k<NumOfMine;k++) {
		            	  
		            	  if(i==mineX[k] && j == mineY[k]) {
		            		  
		            		  mineRoom[i][j] = 10;
		            		  openCheck[i][j] = 9;
		            		  endCheck[i][j] = 10;
		                    
		            		  if(mineX[k]==0 && mineY[k]==0) {
		            			  
		            			  mineRoom[1][0]++;
		            			  mineRoom[1][1]++;
		            			  mineRoom[0][1]++;
		            			  
		            		  }
		            		  
		            		  //좌 상단 끝
		            		  
		            		  else if(mineX[k]==0 && mineY[k]==GARO-1) {
		            			  
		            			  mineRoom[0][GARO-2]++;
		            			  mineRoom[1][GARO-2]++;
		            			  mineRoom[1][GARO-1]++;
		            			  
		            		  } //오른쪽 상단 끝
		            		  
		            		  else if(mineX[k]==SERO-1  && mineY[k]==0) {
		            			  
		            			  mineRoom[SERO-2][0]++;
		            			  mineRoom[SERO-2][1]++;
		            			  mineRoom[SERO-1][1]++;
		            			  
		            		  } //좌 하단 끝
		            		  
		            		  else if(mineX[k]==SERO-1 && mineY[k]==GARO-1) {
		            			  
		            			  mineRoom[SERO-1][GARO-2]++;
		            			  mineRoom[SERO-2][GARO-2]++;
		            			  mineRoom[SERO-2][GARO-1]++;
		            			  
		            		  } // 우 하단 끝
		            		  
		            		  else if(mineX[k] == 0) {
		            			  
		            			  mineRoom[i][j-1]++;
		            			  mineRoom[i+1][j-1]++;
		            			  mineRoom[i+1][j]++;
		            			  mineRoom[i+1][j+1]++;
		            			  mineRoom[i][j+1]++;
		            			  
		            		  }
		            		  
		            		  else if(mineX[k]==SERO-1) {
		            			  
		            			  mineRoom[i][j-1]++;
		            			  mineRoom[i-1][j-1]++;
		            			  mineRoom[i-1][j]++;
		            			  mineRoom[i-1][j+1]++;
		            			  mineRoom[i][j+1]++;
		            			  
		            			  
		            		  }
		            		  
		            		  else if(mineY[k]==0) {
		            			  
		            			  mineRoom[i-1][j]++;
		            			  mineRoom[i-1][j+1]++;
		            			  mineRoom[i][j+1]++;
		            			  mineRoom[i+1][j+1]++;
		            			  mineRoom[i+1][j]++;
		            			  
		            			  
		            		  }
		            		  
		            		  else if(mineY[k]==GARO-1) {
		            			  
		            			  mineRoom[i-1][j]++;
		            			  mineRoom[i-1][j-1]++;
		            			  mineRoom[i][j-1]++;
		            			  mineRoom[i+1][j-1]++;
		            			  mineRoom[i+1][j]++;
		            			  
		            			  
		            		  }
		            		  
		            		 
		            		  else {
		            			  
		            			  mineRoom[i-1][j-1]++;
		            			  mineRoom[i-1][j]++;
		            			  mineRoom[i-1][j+1]++;
		            			  mineRoom[i][j-1]++;
		            			  mineRoom[i][j+1]++;
		            			  mineRoom[i+1][j-1]++;
		            			  mineRoom[i+1][j]++;
		            			  mineRoom[i+1][j+1]++;
		            			  
		            		  }
		            		  
		            	  }
		            
		            
		            	  
		            	  
		              }

					   
				}
				
			} //지뢰의 좌표들과 주변에 몇 개의 지뢰가 있는지 버튼에 대응하는 배열에 대입
		
		
	}
	

public void playLevel1() {
	
	for(int i =0;i<SIZE_OF_PANEL_SERO ;i++) {
		
		for(int j=0;j<SIZE_OF_PANEL_GARO ;j++) {
			
			btn[i][j] = new JButton(" ");
			panelLv1.add(btn[i][j]);
			
		}
		
	}
	
// 버튼생성	
	
   	for(int i =0;i<SIZE_OF_PANEL_SERO;i++) {
   		
   		for(int j =0;j<SIZE_OF_PANEL_GARO;j++) {
   			
   			btn[i][j].addActionListener(openTileListener);
   			btn[i][j].addMouseListener(RightClickListener);
   		}
   	
  }
   	
}



public void makeMenu() {
		
		JMenuItem item;
		
		KeyStroke key;
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("게임");
		m1.setMnemonic(KeyEvent.VK_F);
		JMenu m2 = new JMenu("파일");
		m2.setMnemonic(KeyEvent.VK_C);
		JMenu m3 = new JMenu("도움말");
		m3.setMnemonic(KeyEvent.VK_H);
		
	    item = new JMenuItem("새게임", KeyEvent.VK_N);
	    item.addActionListener(MenuListener);
	    m1.add(item);
	    item = new JMenuItem("LV1");
	    item.addActionListener(MenuListener);
	    m1.add(item);
	    item = new JMenuItem("LV2");
	    item.addActionListener(MenuListener);
	    m1.add(item);
	    item = new JMenuItem("LV3");
	    item.addActionListener(MenuListener);
	    m1.add(item);
	    item = new JMenuItem("종료하기", KeyEvent.VK_X);
	    item.addActionListener(MenuListener);
	    m1.add(item);
	    
	    item = new JMenuItem("저장", KeyEvent.VK_S);
	    item.addActionListener(MenuListener);
	    m2.add(item);
	    item = new JMenuItem("불러오기", KeyEvent.VK_R);
	    item.addActionListener(MenuListener);
	    m2.add(item);
	    
	    item = new JMenuItem("도움말", KeyEvent.VK_D);
	    item.addActionListener(MenuListener);
	    m3.add(item);
       	  
	    
	    item = new JMenuItem("파일 열기", KeyEvent.VK_O);
	    mb.add(m1);
	    mb.add(m2);
	    mb.add(m3);
	    
	    frame.setJMenuBar(mb);
	    
	  
	    
	}

ActionListener openTileListener = new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        

		if(firstCount == 0) {
			
			(timer.t1).start();
			
		for(int i =0;i<SIZE_OF_PANEL_SERO;i++) {
			
			int icount = 0;
			
			for(int j=0;j<SIZE_OF_PANEL_GARO ;j++) {
				
				if(e.getSource()==btn[i][j]) {
					mineRoom[i][j] = 0;
					firstCount++;
					randomMine(SIZE_OF_PANEL_GARO,SIZE_OF_PANEL_SERO,NUM_OF_MINE ,i,j);
				}
				
				
				
				if(e.getSource()==btn[i][j] && mineRoom[i][j]==0) {
					
				    openTile(i,j,SIZE_OF_PANEL_GARO,SIZE_OF_PANEL_SERO);	
		
					icount++;
					break;
					
				}
				
				else if(e.getSource()==btn[i][j] && mineRoom[i][j] > 0)
					ButtonAction(i,j,SIZE_OF_PANEL_GARO,SIZE_OF_PANEL_SERO);
				else if(e.getSource()==btn[i][j] && mineRoom[i][j] < 0)
				    ButtonAction(i,j,SIZE_OF_PANEL_GARO,SIZE_OF_PANEL_SERO);
			if(icount==1)
				break;
				
			}
			
		}
	    
       } //전역변수 firstCount를 설정하고 첫실행에만 위에 루프가 실행되도록 설정 위 루프는 첫 버튼을 0으로 만든다.
		
		else if(firstCount == 1) {
			
			for(int i =0;i<SIZE_OF_PANEL_SERO;i++) {
				
				int icount = 0;
				
				for(int j=0;j<SIZE_OF_PANEL_GARO ;j++) {
			
					if(e.getSource()==btn[i][j] && mineRoom[i][j]==0) {
						
					    openTile(i,j,SIZE_OF_PANEL_GARO,SIZE_OF_PANEL_SERO);	
			
						icount++;
						break;
						
					}
					
					else if(e.getSource()==btn[i][j] && mineRoom[i][j] > 0)
						ButtonAction(i,j,SIZE_OF_PANEL_GARO,SIZE_OF_PANEL_SERO);
					else if(e.getSource()==btn[i][j] && mineRoom[i][j] < 0)
					    ButtonAction(i,j,SIZE_OF_PANEL_GARO,SIZE_OF_PANEL_SERO);
				if(icount==1)
					break;
					
				}
				
			}
			
			
			
		} // 계속 제일 위 루프가 작동하면 안되니까 위에서 firstCount++ 해주고 그 다음부터는 정상적 실행 
		
		else;
		
		
		for(int i=0;i<SIZE_OF_PANEL_SERO;i++) {
			
			for(int j=0;j<SIZE_OF_PANEL_GARO;j++) {
				
				if(openCheck[i][j]==2 && endCheck[i][j] == 0) {
					endCheck[i][j]++;
					endCount++;
				}
				
			}
			
			
		
		}  //endCheck 배열을 통해 OPEN인 타일당 한번만 endCount를 올리게해줌.
		
		if(endCount==SIZE_OF_PANEL_SERO*SIZE_OF_PANEL_GARO-NUM_OF_MINE && firstCount == 1) {
			firstCount = 2;
			timer.stop = false;
			
			for(int i = 0;i<SIZE_OF_PANEL_SERO;i++) {
				
				for(int j=0;j<SIZE_OF_PANEL_GARO;j++) {
					
					if(mineRoom[i][j] >= 10) {
						btn[i][j].setBackground(Color.RED);
						btn[i][j].setText(" ");
					}
			
				}
			}
			JOptionPane.showMessageDialog(null, "당신의 승리입니다. 종료시간 " + timer.time2); 
			
		}
		
		
	}
	
	
	
};

ActionListener MenuListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem jm = (JMenuItem) (e.getSource()); 
			
			
			
			switch(jm.getText()) {
			
			case "새게임":    new Minesweeper();
			                frame.dispose();
			                break;
			case "파일 열기": System.out.println("파일 열기");
	                        break;
	                        
			case "LV1":     new Minesweeper();
                            frame.dispose();
                            break;
			case "LV2":     new Level2();
			                frame.dispose();
                            break;
            case "LV3":     new Level3();
                            frame.dispose();
                            break;               
            case "종료하기":  System.exit(0); 
                            break;
	        
            case "저장":  saveGame();
                         break;
                         
            case "불러오기":loadGame();
                         break;
                         
            case "도움말":JOptionPane.showMessageDialog(null, "한 학기 동안 많은것을 배워갑니다. 자바를 처음 써봐서 미흡한 과제이지만 \n만들어 가면서 큰 자신감을 얻었습니다. 감사합니다. 교수님 조교님!"); 
                         break;    
            default : ;            
			} 
			
		}

		
 };
 
MouseListener RightClickListener = new MouseListener() {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
    public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		if(e.getButton()==3 && firstCount==1) {
			
			for(int i = 0;i<SIZE_OF_PANEL_SERO;i++) {
				
				for(int j=0;j<SIZE_OF_PANEL_GARO;j++) {
					
					 if(e.getSource() == btn[i][j] && openCheck[i][j]!=OPEN) {
						 
						 switch(clickCheck[i][j]) {
						 
						 case 0 : btn[i][j].setBackground(Color.BLUE);
						          btn[i][j].setText("");
						          clickCheck[i][j]++; break;
						          
						 case 1 : btn[i][j].setBackground(null);
				                  btn[i][j].setText("?");
                                  clickCheck[i][j]++; break; 
                                  
						 case 2 : btn[i][j].setBackground(null);
						          btn[i][j].setText("");
		                          clickCheck[i][j]=0; break;
		                         
						 default: ;
						 
						 }
					 }
					
				}
				
			} 
			
		}
		
	}

 };
 

 public void saveGame() {
	 
	    int sHour = timer.hour2;
	    int sMinute = timer.minute1;
	    int sMinute2 = timer.minute2;
	    int sSec= timer.sec1;
	    int sSec2 = timer.sec2;
		String mine = "E:\\Temp\\mineRoom.txt";
		String open = "E:\\Temp\\openCheck.txt";
		String click = "E:\\Temp\\clickCheck.txt";
		String timerPath = "E:\\Temp\\timer.txt";
		
		
		if(firstCount == 0) {
			JOptionPane.showMessageDialog(null, "저장할 데이터가 없습니다. 먼저 게임을 시작하세요");		
		}
		
		else if(firstCount == 1) {
		try{
			FileOutputStream fos = new FileOutputStream(mine);
			FileOutputStream fos2 = new FileOutputStream(open);
			FileOutputStream fos3 = new FileOutputStream(click);
			FileOutputStream fos4 = new FileOutputStream(timerPath);
			
			for(int i =0;i<SIZE_OF_PANEL_SERO;i++) {
				
				for(int j =0;j<SIZE_OF_PANEL_GARO;j++) {
					
					fos.write(mineRoom[i][j]);
					fos2.write(openCheck[i][j]);
					fos3.write(clickCheck[i][j]);	
					
				}
			
			}
			
			fos4.write(sHour); fos4.write(sMinute); fos4.write(sMinute2); fos4.write(sSec); fos4.write(sSec2);
			
			
			
		
			
			
			fos.close();
			fos2.close();
			fos3.close();
			fos4.close();
			
		} catch (IOException e) {}
	    
		}
		
	}


	public void loadGame() {
		
		String mine = "E:\\Temp\\mineRoom.txt";
		String open = "E:\\Temp\\openCheck.txt";
		String click = "E:\\Temp\\clickCheck.txt";
		String timerPath = "E:\\Temp\\timer.txt";
		File file = new File(mine);
		boolean existCheck = file.exists();
		
		if(firstCount == 0 && existCheck == false) {
			JOptionPane.showMessageDialog(null, "불러올 데이터가 없습니다.");	
		}
		
		
		
	   if(firstCount == 0) {
       try {
			
			FileInputStream fks = new FileInputStream(mine);
			if( fks.read() == -1 ) {
			JOptionPane.showMessageDialog(null, "불러올 데이터가 없습니다.");
			loadEmptyCheck = true;
				
			}
		
		
		}
	   
		catch(IOException e) {}
	   }
	   
        if(firstCount == 1 && loadEmptyCheck == true) {
         JOptionPane.showMessageDialog(null, "게임 진행 중 불러오기를 할 수 없습니다.");
        }
		
		if(firstCount==0 && loadEmptyCheck == false && existCheck == true) {
			
			firstCount = 1;
	
		try{
			
			FileInputStream fis = new FileInputStream(mine);
			FileInputStream fis2 = new FileInputStream(open);
			FileInputStream fis3 = new FileInputStream(click);
			FileInputStream fis4 = new FileInputStream(timerPath);
			
			
			
			
			     int loadmine;
			     int loadopen;
			     int loadclick;
			     timer.hour2 = fis4.read();
			     timer.minute1 = fis4.read();
			     timer.minute2 = fis4.read();
			     timer.sec1 = fis4.read();
			     timer.sec2 = fis4.read();			    
			     (timer.t1).start();
			     
	             int x = 0;
	             int y = 0;
			     for(int i =0;i<SIZE_OF_PANEL_SERO;i++) {
			    	 
			    	 for(int j=0;j<SIZE_OF_PANEL_GARO;j++) {
			    		 
			    		 if((loadmine = fis.read())!=-1 && (loadopen = fis2.read())!=-1) {
			    			 
			    			 if(loadopen == 2 && loadmine ==0) {
			    				 btn[i][j].setText(" ");
			    			     btn[i][j].setBackground(Color.WHITE);
			    			     openCheck[i][j] = loadopen;
			    			     mineRoom[i][j] = loadmine;
			    			    
			    			 }
			    			 
			    			 else if(loadopen == 2 && loadmine <10) {
			    			     btn[i][j].setText(Integer.toString(loadmine));
			    			     openCheck[i][j] = loadopen;
			    			     mineRoom[i][j] = loadmine;
			    			 }
			    			 
			    
			    			 
			    			 else {
			    				 openCheck[i][j] = loadopen;
			    			     mineRoom[i][j] = loadmine;
			    			 }
			    				 
			    		 }
			    		 
			    		 
			    		 
			    		 loadclick = fis3.read();
	    			     clickCheck[i][j] = loadclick;
			    		 
                         switch(clickCheck[i][j]) {
						 
							          
                         case 1 : btn[i][j].setBackground(Color.BLUE);
						          btn[i][j].setText("");
						          break;
						 case 2 : btn[i][j].setBackground(null);
		                          btn[i][j].setText("?");
                                  break; 
		                  
						 default: ;
						 
						 }

			    	 }
			    	 
			     }
			    
       
			        fis.close();
					fis2.close();
					fis3.close();
					fis4.close();
			
				
		} catch (IOException e) {}
			
		
		
		}
		
		else if(firstCount==1 && loadEmptyCheck == false)
			
			JOptionPane.showMessageDialog(null, "게임 진행 중 불러오기를 할 수 없습니다.");
			
		else;
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    new Minesweeper();

 		

    }
}