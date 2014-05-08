package minijavacompiler;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.java.swing.plaf.windows.resources.windows;

public class SwingContainerDemo {
	   private JFrame mainFrame;
	   private JPanel controlPanel;
	   private JLabel headerLabel;
	   private JLabel statusLabel;
	   private JLabel msglabel;

	   public SwingContainerDemo(){
	      prepareGUI();
	   }

//	   public static void main(String[] args){
//	      SwingContainerDemo  swingContainerDemo = new SwingContainerDemo();  
//	      swingContainerDemo.showJFrameDemo();
//	   }

	   private void prepareGUI(){
	      mainFrame = new JFrame("Analisador B+-");
	      mainFrame.setSize(400,400);
	      mainFrame.setLayout(new GridLayout(3, 1));
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	      headerLabel = new JLabel("", JLabel.CENTER);        
	      statusLabel = new JLabel("",JLabel.CENTER);    

	      statusLabel.setSize(350,100);

	      msglabel = new JLabel("Welcome to TutorialsPoint SWING Tutorial."
	         , JLabel.CENTER);

	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());

	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(statusLabel);
	      mainFrame.setVisible(true);  
	   }

	   public void showJFrameDemo(){
	      headerLabel.setText("Container in action: JFrame");   

	      final JFrame frame = new JFrame();
	      frame.setSize(300, 300);
	      frame.setLayout(new FlowLayout());       
	      frame.add(msglabel);
	      
	      frame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            frame.dispose();
	         }
	      });
	      
	      JButton abrirArquivo = new JButton("Abrir arquivo para analise");
	      JButton fechar = new JButton("Fechar");
	      JButton escreverArquivo = new JButton("Escrever um arquivo");
	      
	      abrirArquivo.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            statusLabel.setText("A Frame shown to the user.");
	            frame.setVisible(true);
	         }
	      });
	      fechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.addWindowListener(new WindowAdapter() {
					
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						frame.dispose();
					}
				});
			}
			
		});
	      
	      controlPanel.add(abrirArquivo);
	      controlPanel.add(fechar);
	      controlPanel.add(escreverArquivo);
	      
	      
	      mainFrame.setVisible(true);  
	   }
	}