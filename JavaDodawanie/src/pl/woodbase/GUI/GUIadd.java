package pl.woodbase.GUI;

import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import pl.woodbase.dao.SqlConn;

public class GUIadd implements ActionListener{
	
	private JLabel idLabel = new JLabel("ID: ");
	private JLabel pathLabel = new JLabel("Scie¿ka: ");
	private JTextField idText = new JTextField(30);
	private JTextField pathText = new JTextField(30); 
	private JButton acceptButton = new JButton("OK");
	private JButton cancelButton = new JButton("Anuluj");
	private SqlConn sqlConn = new SqlConn();
	private JFrame addFrame = new JFrame("Dodaj rekord");
	
	public GUIadd() {
		
		addFrame.setSize(420,140);
		addFrame.setLayout(new FlowLayout());
		addFrame.add(idLabel);
		addFrame.add(idText);
		addFrame.add(pathLabel);
		addFrame.add(pathText);
		acceptButton.addActionListener(this);
		cancelButton.addActionListener(this);
		addFrame.add(acceptButton);
		addFrame.add(cancelButton);
		addFrame.setLocationRelativeTo(null);
		addFrame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == acceptButton) {
			
			sqlConn.addRecord(idText.getText(), pathText.getText());
			addFrame.dispose();
		
		}
		if(e.getSource() == cancelButton) {
			
			addFrame.dispose();
		}
		
	}
	
	
}
