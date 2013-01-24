package interfaz.ventanas;

import interfaz.menús.Inicio;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class VentanaPrincipal extends JFrame
{
	private final JPanel contentPane;

	public VentanaPrincipal()
	{
		setTitle("PoketMon");
		setAlwaysOnTop(true);
		setResizable(false);
		setUndecorated(true);
		setBounds(getGraphicsConfiguration().getBounds());
		getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		getContentPane().add(new Inicio(), BorderLayout.CENTER);

		setVisible(true);
	}
	public static void main(String[] args)
	{
		new VentanaPrincipal();
	}
}
