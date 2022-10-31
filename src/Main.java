import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.*;

public class Main {
	public static boolean inverse = false;

	JRadioButton[] rdo = new JRadioButton[2];
	static class testGui extends JFrame{
		String[][] m1 = new String[4][4];
//		String[][] m1 = {
//				{"54","08","ab","93"},
//				{"18","82","ff","93"},
//				{"bd","66","bc","73"},
//				{"16","da","2b","5d"}
//				{"01","05","09","03"},
//				{"02","06","00","04"},
//				{"03","07","01","05"},
//				{"04","08","02","06"}

//				{"20","02","dc","19"},
//				{"25","dc","11","6a"},
//				{"84","09","85","0b"},
//				{"1d","fb","97","32"}
//				{"10","14","18","22"},
//				{"11","15","19","23"},
//				{"12","16","20","24"},
//				{"13","17","21","25"}
//				{"e9","54","37","1e"},
//				{"b5","19","1c","7a"},
//				{"23","1f","4b","47"},
//				{"90","f4","a6","e0"}
//		};
		String[][] key = new String[4][4];
			//{
//				{"01","05","09","03"},
//				{"02","06","00","04"},
//				{"03","07","01","05"},
//				{"04","08","02","06"}
//				{"2b","28","ab","09"},
//				{"7e","ae","f7","cf"},
//				{"15","d2","15","4f"},
//				{"16","a6","88","3c"}
//		};
		int[][] x = new int[4][4]; //10진변환 평문
		int[][] t = new int[key.length][key[0].length]; //10진변환 키
		int[][] t2 = new int[key.length][key[0].length];
		Key k = new Key();
		SubByte sb = new SubByte();
		ShiftsRows sh = new ShiftsRows();
		Mixcolumn mix = new Mixcolumn();
		
		JTextField[][] jt = new JTextField[4][4];
		JTextField[][] jt2 = new JTextField[4][4];
		JTextField[][] jt3 = new JTextField[4][4];


		JLabel[] roundlabel = new JLabel[12];
		JLabel[] startlabel = new JLabel[12];
		JLabel[] sublabel = new JLabel[12];
		JLabel[] shiftlabel = new JLabel[12];
		JLabel[] Mixlabel = new JLabel[12];
		JLabel[] Keylabel = new JLabel[12];
		
		JRadioButton[] rdo = new JRadioButton[2];
		JLabel txtpl1 = new JLabel("start of round");
		JLabel txtpl2 = new JLabel("after SubBytes");
		JLabel txtpl3= new JLabel("after ShiftRows");
		JLabel txtpl4= new JLabel("after MixColumns");
		JLabel txtpl5= new JLabel("    Round Key   ");
		

		 testGui(){
			JFrame m = new JFrame("AES");
			m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			m.setLayout(new BorderLayout(3,1));
			m.setSize(800,1200);
			
			JPanel btnf = new JPanel(new FlowLayout());
			JPanel inputp = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));		
			JPanel keyp = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			JPanel outputp = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			JPanel topp = new JPanel(new FlowLayout());
			
			JPanel roundp = new JPanel(new FlowLayout(FlowLayout.CENTER,10,58));
			JPanel p3 = new JPanel(new GridLayout(4,4));	//평문이나 암호문
			JPanel p4 = new JPanel(new GridLayout(4,4));	// 키			
			JPanel p6 = new JPanel(new GridLayout(4,4));	//출력
			
			JPanel centerpanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			
			JPanel mpanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			JPanel subpanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			JPanel shiftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			JPanel mixpanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			JPanel rkeypanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			
			inputp.setPreferredSize(new Dimension(120,200));
			keyp.setPreferredSize(new Dimension(120,200));
			outputp.setPreferredSize(new Dimension(120,200));
			btnf.setPreferredSize(new Dimension(70,150));
			roundp.setPreferredSize(new Dimension(120,1000));
			mpanel.setPreferredSize(new Dimension(120,1000));
			subpanel.setPreferredSize(new Dimension(120,1000));
			shiftpanel.setPreferredSize(new Dimension(120,1000));
			mixpanel.setPreferredSize(new Dimension(120,1000));
			rkeypanel.setPreferredSize(new Dimension(120,1000));
			
			centerpanel.setPreferredSize(new Dimension(1000,1000));
			
			JLabel l1 = new JLabel("input");
			JLabel l2 = new JLabel("cipher key");
			JLabel l3= new JLabel("output");
			

			mpanel.add(txtpl1);
			subpanel.add(txtpl2);
			shiftpanel.add(txtpl3);
			mixpanel.add(txtpl4);
			rkeypanel.add(txtpl5);
			
			rdo[0] = new JRadioButton("암호화");
			rdo[1] = new JRadioButton("복호화");
			ButtonGroup grp = new ButtonGroup();
			
			grp.add(rdo[0]);
			grp.add(rdo[1]);
			rdo[0].setSelected(true);
			JButton startbtn = new JButton("start");
			
			for(int i = 0; i<jt.length; i++) {
				for(int j = 0; j<jt.length; j++) {
					jt[i][j] = new JTextField(2);
					jt2[i][j] = new JTextField(2);
					jt3[i][j] = new JTextField(2);

					p3.add(jt[i][j]);
					p4.add(jt2[i][j]);
					p6.add(jt3[i][j]);
					jt3[i][j].setEditable(false);
					jt[i][j].setDocument(new JTLimit());
					jt2[i][j].setDocument(new JTLimit());
					
				}
				
			}

			for(int i = 0; i<startlabel.length; i++) {
				roundlabel[i] = new JLabel("round"+i+"->");
				startlabel[i] = new JLabel("<html>00 00 00 00<br>00 00 00 00<br>00 00 00 00<br>00 00 00 00</html>");
				sublabel[i] = new JLabel("<html>00 00 00 00<br>00 00 00 00<br>00 00 00 00<br>00 00 00 00</html>");
				shiftlabel[i] = new JLabel("<html>00 00 00 00<br>00 00 00 00<br>00 00 00 00<br>00 00 00 00</html>");
				Mixlabel[i] = new JLabel("<html>00 00 00 00<br>00 00 00 00<br>00 00 00 00<br>00 00 00 00</html>");
				Keylabel[i] = new JLabel("<html>00 00 00 00<br>00 00 00 00<br>00 00 00 00<br>00 00 00 00</html>");
				
				roundp.add(roundlabel[i]);
				mpanel.add(startlabel[i]);
				subpanel.add(sublabel[i]);
				shiftpanel.add(shiftlabel[i]);
				mixpanel.add(Mixlabel[i]);
				rkeypanel.add(Keylabel[i]);
			}
			

			
			startbtn.addActionListener(new Eventhandler());
			rdo[0].addItemListener(new MyItemListener());
			rdo[1].addItemListener(new MyItemListener());


			inputp.add(l1);
			keyp.add(l2);
			outputp.add(l3);
			
			inputp.add(p3);
			keyp.add(p4);
			outputp.add(p6);
			
			btnf.add(rdo[0]);
			btnf.add(rdo[1]);
			btnf.add(startbtn);
			
					
			topp.add(inputp);
			topp.add(keyp);
			topp.add(outputp);
			topp.add(btnf);		
			
			centerpanel.add(roundp);
			centerpanel.add(mpanel);
			centerpanel.add(subpanel);
			centerpanel.add(shiftpanel);
			centerpanel.add(mixpanel);
			centerpanel.add(rkeypanel);
			m.add(topp,BorderLayout.NORTH);
			m.add(centerpanel,BorderLayout.CENTER);
			m.setVisible(true);
			pack();
		}
//		 "^[0-9a-fA-F]+$"
		public class JTLimit extends PlainDocument{
		      private int limit = 2;
		      String pattern = "^[0-9a-fA-F]+$";
		      
		      public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {

		    	  	boolean b =Pattern.matches(pattern, str);
		            if (str == null)
		                  return;

		            if (getLength() + str.length() <= limit && b ) {
		            	super.insertString(offset, str, attr);
		            }

		                  
		       }
		}
		class Eventhandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i<m1.length; i++) {
					for(int j = 0; j<m1[i].length; j++) {
						m1[i][j] = jt[i][j].getText();
						key[i][j] = jt2[i][j].getText();	
					}

				}
				t = hextodec(key);
				t2 = hextodec(key);
				x = hextodec(m1);

				if(inverse) {
					
					for(int i = 0; i<10; i++) {
						k.schedule(t2,i);
					}
					printlabel(x,startlabel,0);
					printlabel(t2,Keylabel,0);
					for(int r = 9; r>-1; r--) {
						x = k.Addround(x, t2);
						printlabel(x,startlabel,10-r);
						if(r+1<10) {
							x = mix.Mixcolumn(x, inverse);
							printlabel(x,sublabel,10-r);
			//				printblock(x);
						}	
						x = sh.ShiftsRows(x,inverse);
						printlabel(x,shiftlabel,10-r);
			//			printblock(x);
			//			printblock(t2);
						for(int i = 0; i<m1.length; i++) {
							for(int j = 0; j<m1[i].length; j++) {
								x[i][j] = sb.afmapping(x[i][j],inverse);
								x[i][j] = sb.Gfinverse(x[i][j]);
			
								//System.out.printf("%02x ", x[i][j]);
							}
							//System.out.println();
						}
						printlabel(x,Mixlabel,10-r);
			//			printblock(x);
			
						k.invschedule(t2, r);
						printlabel(t2,Keylabel,10-r);
		
					}

					x = k.Addround(x, t2);
					printlabel(x,startlabel,11);
					printJTF(x,jt3);
			}
			else {
				printlabel(x,startlabel,0);
				printlabel(t,Keylabel,0);
				x = k.Addround(x, t);

//				printblock(x);
				for(int r = 0; r<10; r++){
					t2 = k.schedule(t,r);
					printlabel(x,startlabel,r+1);
					printlabel(t,Keylabel,r+1);
		
					for(int i = 0; i<m1.length; i++) {
						for(int j = 0; j<m1[i].length; j++) {
							x[i][j] = sb.Gfinverse(x[i][j]);
							x[i][j] = sb.afmapping(x[i][j],inverse);
							//System.out.printf("%02x ", x[i][j]);
						}
						//System.out.println();
					}
					printlabel(x,sublabel,r+1);
					x = sh.ShiftsRows(x,inverse);
					printlabel(x,shiftlabel,r+1);
					if(r+1<10) {
						x = mix.Mixcolumn(x, inverse);
						printlabel(x,Mixlabel,r+1);
					}
		
					x = k.Addround(x, t2);
			
		
					}
				printlabel(x,startlabel,11);
				printJTF(x,jt3);
			}	
				
		}
			void printlabel(int[][] x,JLabel[] l,int r) {
				String s = "<html>";
				for(int i =0; i<x.length; i++) {
					for(int j = 0; j<x.length; j++) {
						s += String.format("%02X ", x[i][j]);
					}
					s += "<br>";
				}
				s+="</html>";
				l[r].setText(s);
			}
			void printJTF(int[][] x, JTextField[][] jt) {
				for(int i =0; i<x.length; i++) {
					for(int j = 0; j<x.length; j++) {
						jt[i][j].setText(String.format("%02X", x[i][j]));
					}
					
				}
			}
//			String.format("%05d", Integer.parseInt(Integer.toBinaryString(x[2]).toString())
		}
		class MyItemListener implements ItemListener{

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(rdo[0].isSelected()) {
					inverse = false;
					txtpl2.setText("after SubBytes");
					txtpl3.setText("after ShiftRows");
					txtpl4.setText("after MixColumns");
					txtpl5.setText("    Round Key   ");
				}
				else if(rdo[1].isSelected()) {
					inverse = true;
					txtpl2.setText("after invMixColumns");
					txtpl3.setText("after invShiftRows");
					txtpl4.setText("after invSubByte");
					txtpl5.setText("    Round Key   ");
					
				}
				resetlabel();
			}
			public void resetlabel() {
				for(int i =0; i<startlabel.length; i++) {
					startlabel[i].setText("<html>00 00 00 00<br>00 00 00 00<br>00 00 00 00<br>00 00 00 00</html>");
					sublabel[i].setText("<html>00 00 00 00<br>00 00 00 00<br>00 00 00 00<br>00 00 00 00</html>");
					shiftlabel[i].setText("<html>00 00 00 00<br>00 00 00 00<br>00 00 00 00<br>00 00 00 00</html>");
					Mixlabel[i].setText("<html>00 00 00 00<br>00 00 00 00<br>00 00 00 00<br>00 00 00 00</html>");
					Keylabel[i].setText("<html>00 00 00 00<br>00 00 00 00<br>00 00 00 00<br>00 00 00 00</html>");
				}
			}
			
		}


		
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		new testGui();
//		

	}
	static void printblock(int x[][]) {
		System.out.println();
		for(int i = 0; i<x.length; i++) {
			for(int j = 0; j<x[i].length; j++) {
				System.out.printf("%02x ", x[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static int[][] hextodec(String x[][]){
		int[][] t = new int[x.length][x[0].length]; 
		for(int i = 0; i<x.length; i++) {
			for(int j = 0; j<x[i].length;j++) {
				t[i][j] = Integer.parseInt(x[i][j], 16);
			}			
		}
		
		return t;
	}
		
}

