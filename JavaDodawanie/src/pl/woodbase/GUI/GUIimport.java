package pl.woodbase.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import pl.woodbase.data.ExcelData;

public class GUIimport implements ActionListener{

	private JFrame importFrame = new JFrame("Import");
	private JToolBar toolBar = new JToolBar();
	private JButton importButton = new JButton("Importuj");
	private JButton browseButton = new JButton("Przegl¹daj");
	private JButton cancelButton = new JButton("Anuluj");
	private JLabel browseLabel = new JLabel("Œcie¿ka:");
	private JTextField browseText = new JTextField(30);
	private JPanel importPane = new JPanel();
	private JOptionPane jOption = new JOptionPane();
	private ExcelData excelData = new ExcelData();
	
	public GUIimport() {
		
		importFrame.setSize(420,200);
		importFrame.setLayout(new BorderLayout());
		importFrame.add(toolBar, BorderLayout.PAGE_START);
		importFrame.add(importPane, BorderLayout.CENTER);
		cancelButton.addActionListener(this);
		browseButton.addActionListener(this);
		importButton.addActionListener(this);
		toolBar.add(importButton);
		toolBar.add(browseButton);
		toolBar.add(cancelButton);
		importPane.setLayout(new FlowLayout());
		importPane.add(browseLabel);
		importPane.add(browseText);
		importFrame.setLocationRelativeTo(null);
		importFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == cancelButton) {
			
			importFrame.dispose();
		}
		if(e.getSource() == browseButton) {
			
			final JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(null);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				
				File file = fileChooser.getSelectedFile();
				browseText.setText(file.getPath());
			}
		}
		if(e.getSource() == importButton) {
			
			excelData.readExcelFile(browseText.getText());
			if(excelData.getError() == true) {
				jOption.showMessageDialog(importFrame, "Sprawdz iloœæ kolumn w excelu (wymagana iloœæ: 2)"
						,"B³¹d Excel", JOptionPane.ERROR_MESSAGE);
			}else if(excelData.getError() == false) {
				if(excelData.getException() == true) {
					jOption.showMessageDialog(importFrame, excelData.getStackTrace()
							,"Exception", JOptionPane.ERROR_MESSAGE);
				}else {
				jOption.showMessageDialog(importFrame, "Dodano rekordów: " + excelData.getRecords()
						,"Dodano rekordy", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			importFrame.dispose();
		}
		
	}
	
}
