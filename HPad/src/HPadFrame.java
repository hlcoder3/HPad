import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class HPadFrame extends JFrame
{
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 500;
	private final JTextArea text;
	private JPanel panel;
	
	public HPadFrame()
	{
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);

		menuBar.add(createFileMenu());
		menuBar.add(createEditMenu());
		menuBar.add(createFontMenu());
		menuBar.add(createHelpMenu());
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
		panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		
		text = new JTextArea(10,20);
	    text.setFont( new Font("Serif",Font.PLAIN,16));  
	   
	    JScrollPane scrollPane = new JScrollPane(text);
	    panel.add(scrollPane,BorderLayout.CENTER);
		this.add(panel);
		this.setTitle("HPad");	
	}

	
	//******************** FILE MENU ******************//
	
	public JMenu createFileMenu()
	{
		JMenu menu=new JMenu ("File");
		menu.add(createFileNewItem());
		menu.add(createFileOpenItem());
		menu.add(createFileSaveItem());
		menu.add(createFileSaveAsItem());
		menu.add(createFilePrintItem());
		menu.add(createFileExitItem());
		
		return menu;
	}
}
