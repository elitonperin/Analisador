package minijavacompiler;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class JFrameExemplo extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//toolbar
	private JToolBar toolbar = new JToolBar();

	public JFrameExemplo(){
		super("Analisador");
	
		toolbar.add(new JButton("Abrir"));
		toolbar.add(new JButton("Novo"));
		toolbar.add(new JButton("Salvar"));
		toolbar.add(new JButton("Fechar"));
	
		Container pane = this.getContentPane();
		//define como layout o layout de borda
		pane.setLayout(new BorderLayout());
	
		//adiciona o toolbar no topo
		pane.add(BorderLayout.NORTH, toolbar);
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(310, 360);
		this.setVisible(true);
		
		addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buttonConfirmaActionPerformed(e);	
			}

	}
	
	
	
}
