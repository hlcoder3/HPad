import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class HPadFrame extends JFrame
{
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 500;
	private final JTextArea txtLog;
	private JPanel panel;
	private final JFileChooser fc;
	private File file = null;
	
	public HPadFrame()
	{
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);

		fc = new JFileChooser();
		menuBar.add(createFileMenu());
		//menuBar.add(createEditMenu());
		//menuBar.add(createFontMenu());
		//menuBar.add(createHelpMenu());
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
		panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		
		txtLog = new JTextArea(10,20);
	    txtLog.setFont( new Font("Serif",Font.PLAIN,16));  
	   
	    JScrollPane scrollPane = new JScrollPane(txtLog);
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
	
	
	/**
	 * 
	 * @return
	 */
	public JMenuItem createFileNewItem()
	{
		JMenuItem item = new JMenuItem("New");
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				HPadFrame f = new HPadFrame();
				f.setVisible(true);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	/**
	 * 
	 * @param title
	 * @param defDir
	 * @param fileType
	 * @return
	 */
	 private String showOpenFileFC(String title, String defDir, String fileType)
	 {
		 
		 int returnVal = fc.showOpenDialog(this);
		 if (returnVal == JFileChooser.APPROVE_OPTION) 
		 {
			 file = fc.getSelectedFile();
		 }
		 
		 //Reset the file chooser for the next time it's shown.
	     fc.setSelectedFile(null);
	     txtLog.setCaretPosition(txtLog.getDocument().getLength());
		 return file.getPath();
	 }
	 
	 /**
	  * 
	  * @return
	  */
	public JMenuItem createFileOpenItem()
	{
		JMenuItem item = new JMenuItem("Open");
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				  String filename = showOpenFileFC("Open File", "","" );
				  System.out.println(filename);
				  
				  file=new File(filename);
				  Scanner s = null;
				  
				  try 
				  {
						s = new Scanner(file);
				  } 
				  catch (FileNotFoundException e)
				  {
					e.printStackTrace();
				  }
				  String words="";
				  while(s.hasNext())
				  {
					  words= words+s.nextLine()+"\n";
				  }	
				  txtLog.setText(words);  
				  txtLog.setEditable(isEnabled());
				  s.close();
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	 }
	
	
	/**
	 * 
	 * @param title
	 * @param defDir
	 * @param fileType
	 * @return
	 */
	 private String showSaveDialog(String title, String defDir, String fileType)
	 {

		 int returnVal = fc.showSaveDialog(this);
		 if (returnVal == JFileChooser.APPROVE_OPTION) 
		 {
			 file = fc.getSelectedFile();
		 }
		 
		 //Reset the file chooser for the next time it's shown.
	     fc.setSelectedFile(null);
		 return file.getPath();
	 }
	 
	/**
	 * 
	 * @return
	 */
	public JMenuItem createFileSaveItem()
	{
		JMenuItem item = new JMenuItem("Save");
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{		 	
				String filename=showSaveDialog("Save File", "","" );
				try 
				{
					PrintWriter pw=new PrintWriter(filename);
					pw.println(txtLog.getText());
					pw.close();	
				} 
				catch (FileNotFoundException e)
				{	
					e.printStackTrace();
				}	 
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	/**
	 * 
	 * @param title
	 * @param defDir
	 * @param fileType
	 * @return
	 */
	 private String showSaveAsDialog(String title, String defDir, String fileType)
	 {

		 int returnVal = fc.showDialog(this,"Save As...");
		 if (returnVal == JFileChooser.APPROVE_OPTION) 
		 {
			 file = fc.getSelectedFile();
		 }
		 
		 //Reset the file chooser for the next time it's shown.
	     fc.setSelectedFile(null);
		 return file.getPath();
	 }
	/**
	 * 
	 * @return
	 */
	public JMenuItem createFileSaveAsItem()
	{
		JMenuItem item = new JMenuItem("Save As");
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
			
				String filename=showSaveAsDialog("Save As File", "","" );
				try
				{
					PrintWriter pw=new PrintWriter(filename);
					pw.println(txtLog.getText());
					pw.close();	
				} 
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				}
				
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public JMenuItem createFilePrintItem()
	{
		JMenuItem item = new JMenuItem("Print");
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
			
				try 
				{
					HPadFrame.this.txtLog.print();
				}
				catch (PrinterException e) {

					e.printStackTrace();
				}
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public JMenuItem createFileExitItem()
	{
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	
}
