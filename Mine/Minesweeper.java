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
	public int firstCount        = 0; //ùŬ�� ī��Ʈ
	public int endCount          = 0; //���ڸ� ������ Ÿ���� � ���µƴ��� ī��Ʈ (Ÿ��-���ڼ�)�� �Ǹ� ��������
	JPanel menuPanel             = new JPanel(new BorderLayout()); // �޴��� ���ڸ� ���� ���� �г�
	JPanel menuPanel2            = new JPanel(new BorderLayout()); // ui�� �г�
	public JButton btn [][]      = new JButton [100][100]; // ���ڹ�ư��
	public JFrame frame          = new JFrame(); // ������
	public JPanel panelLv1       = new JPanel(new GridLayout(0,10)); //����Ÿ�� ���� �г�
	public JPanel panelLv2       = new JPanel(new GridLayout(0,20)); //����Ÿ�� ���� �г�
	public int mineRoom[][]      = new int[100][100]; //2���� �迭�� ��Ÿ�Ͽ� ���ڸ� �ο� ��ĭ��, ����, �ֺ��� ���ڼ��� ���ִ� ��ư�� ����
	public int openCheck[][]     = new int[100][100]; // Ÿ���� ���� ���¶�� ��� OPEN�� �־� �������� Ȯ��
	public int endCheck[][]      = new int [100][100]; // Ÿ���� �ѹ� ���������� endCount�� �ø��� �ʰ� �������ִ� �迭 openTile �޼����� ������ ��Ͷ� �޸� ��ƸԴ� ����ΰͰ���.
	public int clickCheck[][]    = new int [100][100];
	public int mineX[]           = new int[100]; // ������ X��ǥ ����
	public int mineY[]           = new int[100]; //������ Y��ǥ ����
	final int SIZE_OF_PANEL_GARO = 10; //������ ũ�� ����
	final int SIZE_OF_PANEL_SERO = 10; // ������ ũ�� ����
	final int NUM_OF_MINE        = 12;
	final int OPEN               = 2;
	boolean loadEmptyCheck       = false;
	private Timer timer          = new Timer();             //Ÿ�̸� 
	

        public Minesweeper(){
		
		
		
		playLevel1();
		makeMenu();
		
		
		menuPanel2.add(timer,BorderLayout.NORTH);
		menuPanel2.add(new JLabel("Lv1 " + NUM_OF_MINE + "��"),BorderLayout.CENTER);
		menuPanel2.add(panelLv1,BorderLayout.SOUTH);
		menuPanel.add(menuPanel2,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(menuPanel);
	 	frame.setTitle("����ã�� ");
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
			//��
			
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
			//�Ʒ�
			
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
			//����
			
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
			//������
			
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
			//�밢��������
			
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
		    //�밢����������
			
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
		    //�밢�����ʾƷ�
			
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
		    //�밢�������ʾƷ�
			
			
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
    			JOptionPane.showMessageDialog(null, "���ڸ� ��ҽ��ϴ�. ������ �����մϴ�.");
    			
    			System.exit(0);
    		}
            
	}
	
	// 0�� Ÿ���� Ŭ���ϸ� 4������ ���µǴ� �Լ�
	
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
	
    //Ÿ���� �����ϴ� �Լ� (����Լ��̿�)
	
	
public void randomMine(int GARO,int SERO, int NumOfMine, int first1, int first2) {
		
		int upleftx = first1 - 1; int uplefty = first2 -1;
		int upx = first1-1; int upy = first2;
		int uprightx = first1-1; int uprighty = first2+1;
		int leftx = first1; int lefty = first2-1;
		int rightx = first1; int righty = first2+1;
		int downleftx = first1 + 1; int downlefty = first2 -1;
		int downx = first1 + 1; int downy = first2;
		int downrightx = first1 + 1; int downrighty = first2 + 1;
		
		// first1, first2�� ���� ù Ŭ���� ��ǥ�� �ѷ��� ��ǥ���� ������ �����Ѵ�.
         
         for(int i=0;i<NumOfMine;i++) {
      	   int random = (int) (Math.random()*SERO); 
				   mineX[i] = random;
      	   int random2 = (int) (Math.random()*GARO); 
			       mineY[i] = random2;
			       
			       if((upleftx==mineX[i] && uplefty == mineY[i])||(upx==mineX[i] && upy == mineY[i]) || (uprightx==mineX[i] && uprighty == mineY[i]) ||
		      	      (leftx==mineX[i] && lefty == mineY[i]) || (rightx==mineX[i] && righty == mineY[i])||(downleftx==mineX[i] && downlefty == mineY[i]) || 
		      	      (downx==mineX[i] && downy == mineY[i]) || (downrightx==mineX[i] && downrighty == mineY[i]) || (first2==mineY[i]&&first1==mineX[i])) 
			           i--; // ùŬ���� �����ϰ� ùŬ�� ��ǥ���� �ѷ��� ��ǥ���� ���ں����� ���� i�� ���ҽ��� �ٽ� �����Ѵ�.
			    
      	     for(int j = 0;j<i;j++) {
      		   if (mineY[i] == mineY[j] && mineX[i]==mineX[j]) {
      			       i--; j=0; break;
   			     } // i���� �ȿ��� �Ѿ�� ������ ������ �տ� �̹� ���� ���� �������� i�� ���ҽ��� �ٽ� ���Խ�Ų��.
      			 
      		  }    
      	  }
      	   
         
		// ������ ��ǥ�� ����
		
		
		for(int i=0;i<SERO;i++) {
			for(int j=0;j<GARO;j++) {
	            		  mineRoom[i][j] = 0;
	         }
	            	  
		}  //�켱 ��ư�鿡 �����ϴ� mineRoom�迭�� ��� 0���� ���� mineRoom(first1,first2)�� �� �ֺ����� ������ 0�� �Ŷ� �̷����ص� �������. ������ ��ǥ�� �߿�.
		
		
		
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
		            		  
		            		  //�� ��� ��
		            		  
		            		  else if(mineX[k]==0 && mineY[k]==GARO-1) {
		            			  
		            			  mineRoom[0][GARO-2]++;
		            			  mineRoom[1][GARO-2]++;
		            			  mineRoom[1][GARO-1]++;
		            			  
		            		  } //������ ��� ��
		            		  
		            		  else if(mineX[k]==SERO-1  && mineY[k]==0) {
		            			  
		            			  mineRoom[SERO-2][0]++;
		            			  mineRoom[SERO-2][1]++;
		            			  mineRoom[SERO-1][1]++;
		            			  
		            		  } //�� �ϴ� ��
		            		  
		            		  else if(mineX[k]==SERO-1 && mineY[k]==GARO-1) {
		            			  
		            			  mineRoom[SERO-1][GARO-2]++;
		            			  mineRoom[SERO-2][GARO-2]++;
		            			  mineRoom[SERO-2][GARO-1]++;
		            			  
		            		  } // �� �ϴ� ��
		            		  
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
				
			} //������ ��ǥ��� �ֺ��� �� ���� ���ڰ� �ִ��� ��ư�� �����ϴ� �迭�� ����
		
		
	}
	

public void playLevel1() {
	
	for(int i =0;i<SIZE_OF_PANEL_SERO ;i++) {
		
		for(int j=0;j<SIZE_OF_PANEL_GARO ;j++) {
			
			btn[i][j] = new JButton(" ");
			panelLv1.add(btn[i][j]);
			
		}
		
	}
	
// ��ư����	
	
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
		JMenu m1 = new JMenu("����");
		m1.setMnemonic(KeyEvent.VK_F);
		JMenu m2 = new JMenu("����");
		m2.setMnemonic(KeyEvent.VK_C);
		JMenu m3 = new JMenu("����");
		m3.setMnemonic(KeyEvent.VK_H);
		
	    item = new JMenuItem("������", KeyEvent.VK_N);
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
	    item = new JMenuItem("�����ϱ�", KeyEvent.VK_X);
	    item.addActionListener(MenuListener);
	    m1.add(item);
	    
	    item = new JMenuItem("����", KeyEvent.VK_S);
	    item.addActionListener(MenuListener);
	    m2.add(item);
	    item = new JMenuItem("�ҷ�����", KeyEvent.VK_R);
	    item.addActionListener(MenuListener);
	    m2.add(item);
	    
	    item = new JMenuItem("����", KeyEvent.VK_D);
	    item.addActionListener(MenuListener);
	    m3.add(item);
       	  
	    
	    item = new JMenuItem("���� ����", KeyEvent.VK_O);
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
	    
       } //�������� firstCount�� �����ϰ� ù���࿡�� ���� ������ ����ǵ��� ���� �� ������ ù ��ư�� 0���� �����.
		
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
			
			
			
		} // ��� ���� �� ������ �۵��ϸ� �ȵǴϱ� ������ firstCount++ ���ְ� �� �������ʹ� ������ ���� 
		
		else;
		
		
		for(int i=0;i<SIZE_OF_PANEL_SERO;i++) {
			
			for(int j=0;j<SIZE_OF_PANEL_GARO;j++) {
				
				if(openCheck[i][j]==2 && endCheck[i][j] == 0) {
					endCheck[i][j]++;
					endCount++;
				}
				
			}
			
			
		
		}  //endCheck �迭�� ���� OPEN�� Ÿ�ϴ� �ѹ��� endCount�� �ø�������.
		
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
			JOptionPane.showMessageDialog(null, "����� �¸��Դϴ�. ����ð� " + timer.time2); 
			
		}
		
		
	}
	
	
	
};

ActionListener MenuListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem jm = (JMenuItem) (e.getSource()); 
			
			
			
			switch(jm.getText()) {
			
			case "������":    new Minesweeper();
			                frame.dispose();
			                break;
			case "���� ����": System.out.println("���� ����");
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
            case "�����ϱ�":  System.exit(0); 
                            break;
	        
            case "����":  saveGame();
                         break;
                         
            case "�ҷ�����":loadGame();
                         break;
                         
            case "����":JOptionPane.showMessageDialog(null, "�� �б� ���� �������� ������ϴ�. �ڹٸ� ó�� ����� ������ ���������� \n����� ���鼭 ū �ڽŰ��� ������ϴ�. �����մϴ�. ������ ������!"); 
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
			JOptionPane.showMessageDialog(null, "������ �����Ͱ� �����ϴ�. ���� ������ �����ϼ���");		
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
			JOptionPane.showMessageDialog(null, "�ҷ��� �����Ͱ� �����ϴ�.");	
		}
		
		
		
	   if(firstCount == 0) {
       try {
			
			FileInputStream fks = new FileInputStream(mine);
			if( fks.read() == -1 ) {
			JOptionPane.showMessageDialog(null, "�ҷ��� �����Ͱ� �����ϴ�.");
			loadEmptyCheck = true;
				
			}
		
		
		}
	   
		catch(IOException e) {}
	   }
	   
        if(firstCount == 1 && loadEmptyCheck == true) {
         JOptionPane.showMessageDialog(null, "���� ���� �� �ҷ����⸦ �� �� �����ϴ�.");
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
			
			JOptionPane.showMessageDialog(null, "���� ���� �� �ҷ����⸦ �� �� �����ϴ�.");
			
		else;
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    new Minesweeper();

 		

    }
}