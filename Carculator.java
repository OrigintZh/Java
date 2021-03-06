package wz13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Carculator extends JFrame implements ActionListener{
	/**
	 * 计算器
	 */
	private static final long serialVersionUID = 657900675620347482L;
	private JPanel jp_north = new JPanel();
	private JTextField input_text = new JTextField();  
	private JButton c_Btn = new JButton("C");     
	
	private JPanel jp_center = new JPanel();
	private JPanel jp_south = new JPanel();
	
	public static final int FRAME_W = 400;
	public static final int FRAME_H = 450;		
	private String[] labels = {"1","2","3","+","4","5","6","-","7","8","9","*",".","0","+/-","/","√","x³","←","="};
	public static final int SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().width;     
	public static final int SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().height;    
	
	public int frame_x = (SCREEN_W - FRAME_W)/2;  
	public int frame_y = (SCREEN_H - FRAME_H)/2;
	
	public Carculator() {
		this.setTitle("计算器");
		this.setSize(FRAME_W,FRAME_H);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocation(frame_x,frame_y);     
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.input_text.setPreferredSize(new Dimension(310,50));   
		this.input_text.setForeground(Color.black);
		this.input_text.setFont(new Font("粗体",Font.BOLD,18));		
		jp_north.add(input_text);
		this.c_Btn.setForeground(Color.gray);			
		this.c_Btn.setFont(new Font("粗体",Font.BOLD,20));
		this.c_Btn.setPreferredSize(new Dimension(80,50));   
		jp_north.add(c_Btn);
		
		c_Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				input_text.setText("");
			}
		});
		
		this.add(jp_north,BorderLayout.NORTH);   
		this.addCenterButton();				
	}
	
	public void addCenterButton() {				
		
		this.jp_center.setLayout(new GridLayout(5,4));
		this.jp_south.setLayout(new GridLayout(1,1));
		Font font = new Font("Sanserif", Font.BOLD, 24);
		for (int i = 0; i < 20; i++) {
            JButton btn = new JButton(labels[i]);
    		btn.addActionListener(this);
            jp_center.add(btn);
            btn.setFont(font);
            if(i>=0&&i<=2 || i>=4&&i<=6 || i>=8&&i<=10 || i==12 || i==13)
            btn.setForeground(Color.black);
            else   btn.setForeground(Color.gray);
        }
        JButton btn1 = new JButton("Designed by Origint Zh");
        jp_south.add(btn1);
        btn1.setForeground(Color.gray);
		btn1.setFont(new Font("粗",Font.BOLD,16));
		btn1.setPreferredSize(new Dimension(310,40));
		btn1.addActionListener(this);
        this.add(jp_center,BorderLayout.CENTER);
		this.add(jp_south,BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		Carculator carculator = new Carculator();
		carculator.setVisible(true);
	}
private String firstInput = null;
private String operator = null;
String c = null;
@Override
public void actionPerformed(ActionEvent e) {		
	// TODO Auto-generated method stub
	String clickStr = e.getActionCommand();
	if(".1234567890".indexOf(clickStr) != -1){
		this.input_text.setForeground(Color.black);
		this.input_text.setText(input_text.getText() + clickStr);		
		this.input_text.setHorizontalAlignment(JTextField.RIGHT);}		
	else if(clickStr == "Designed by Origint Zh") {
		this.input_text.setHorizontalAlignment(JTextField.RIGHT);		
		this.input_text.setText("WELCOME! (≧ ﹏ ≦)");
		this.input_text.setForeground(Color.gray);
		//JOptionPane.showMessageDialog(this,clickStr);
	}else if(clickStr.matches("[\\+\\-*/]{1}")){						
		this.input_text.setForeground(Color.black);
		operator = clickStr;
		firstInput = this.input_text.getText();
		this.input_text.setText("");
	}else if(clickStr.equals("=")) {
		if(operator == "+" || operator == "-" || operator == "*" || operator == "/" || operator == "←"){
		this.input_text.setForeground(Color.black);
		Double a = Double.valueOf(firstInput);
		Double b = Double.valueOf(this.input_text.getText());
		Double result = null;
		switch(operator) {
			case "+":
				 result = a + b;
				 break;
			case "-":
				result = a - b;
				break;
			case "*":
				result = a * b;
				break;
			case "/":
				if(b != 0) {
					result = a / b;
				}
				else 	this.input_text.setText("ERROR!");
				break;
			case "←":
				c = String.valueOf((int) (+a));
	            c = c.substring(0, c.length() - 1);
	            break;
		}
		if(operator=="+" || operator=="-" || operator=="*" || operator=="/") 
		this.input_text.setText(result.toString());
		else this.input_text.setText(c);
		}
	}
	 else if(clickStr=="√" || clickStr=="+/-" || clickStr=="x³" || clickStr=="³√" || clickStr=="←") {
		this.input_text.setForeground(Color.black);
		firstInput = this.input_text.getText();
		Double b = Double.valueOf(firstInput);
		Double result = null;
		String result1 = null;
		switch(clickStr) {
			case "√":
				result = Math.sqrt(b);
				break;
			case "+/-":
				result1 = String.valueOf((int) (-b));
				break;
			case "x³":
				result = b*b*b;
				break;
			case "³√":
				result = Math.pow(b,1/3.0);
				break;
			case "←":
				c = String.valueOf((int) (+b));
	            c = c.substring(0, c.length() - 1);
	            break;
			}
		if(clickStr=="√" || clickStr=="x³" || clickStr=="³√") 
		this.input_text.setText(result.toString());
		else if(clickStr=="+/-")  this.input_text.setText(result1);
		else this.input_text.setText(c);
	}
  }
}