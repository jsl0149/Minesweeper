import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MenuDemo extends JFrame implements ActionListener {

	MenuDemo(){
		setTitle("메뉴 구성하기");
		setSize(250,170);
		
		makeMenu();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	void makeMenu() {
		JPanel panel = new JPanel(new BorderLayout());
		JMenuItem item;
		KeyStroke key;
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("파일");
		m1.setMnemonic(KeyEvent.VK_F);
		JMenu m2 = new JMenu("색상");
		m2.setMnemonic(KeyEvent.VK_C);
	    item = new JMenuItem("새 파일", KeyEvent.VK_N);
	    item.addActionListener(this);
	    m1.add(item);
	    item = new JMenuItem("파일 열기", KeyEvent.VK_O);
	    mb.add(m1);
	    mb.add(m2);
	    
	    panel.add(mb,BorderLayout.NORTH);
	    
	    add(panel);
	   
	    
	    
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		new MenuDemo();
		
	}

	

}
