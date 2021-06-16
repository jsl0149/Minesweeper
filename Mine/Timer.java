
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timer extends JPanel implements Runnable{

	public JLabel label;
	private JFrame frame;	
	public String time;
	public String time2;
	public boolean stop         = true;
	public int hour1            = 0;
	public int hour2            = 0;
	public int minute1          = 0;
	public int minute2          = 0;
	public int sec1             = 0;
	public int sec2             = 0;
	public int loadCount        = 0;
	public Thread t1            = new Thread(this);
	
	public Timer() {
		
		label = new JLabel("버튼을 클릭하면 시작");
		label.setFont(new Font("TimesRoman", Font.BOLD, 30));
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
       
	}
	
	@Override
	public void run() {
		
		while(stop==true) {
			try {
				Thread.sleep(1000);
				String time = getTime();
				label.setText(time);
				sec2++;
				if(sec2==10) {
					sec2=0;
					sec1++;
				}
				
				if(sec1==6) {
					sec1=0;
					minute2++;
				}
				
				if(minute2==10) {
					minute2=0;
					minute1++;
				}
				
				if(minute1==6) {
					minute1=0;
					hour2++;
				}
				
				time2 = "0" + hour2 + ":" + minute1 + minute2 +":"+sec1 + sec2;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getTime() {

		String time = "0" + hour2 + ":" + minute1 + minute2 + ":" +sec1 + sec2;
		
		return time;
	}
	

}


