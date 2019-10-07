import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;

public class RobotControlGrid {

	JFrame frame;
	int rowDest;
	int colDest;
	Client client;
	Grid grid;

	public static void main(String[] args) {

		RobotControlGrid rcg = new RobotControlGrid();
		rcg.go();
	}

	void go() {

		grid = new Grid();

		Thread rt = new Thread() {
			public void run() {

				while (true) {
					try {
						grid.setRobotPosition(client.getCol(), client.getRow());
						System.out.println("Recieved Robot Position");

					} catch (IOException e) {

						e.printStackTrace();
					}

				}
			}
		};
		rt.start();

		frame = new JFrame("Robot Navigator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JButton connect = new JButton("Connect");
		connect.addActionListener(new ConnectListener());

		JButton drive = new JButton("Drive");
		drive.addActionListener(new DriveListener());

		JTextField robot = new JTextField(20);
		robot.setMaximumSize(robot.getPreferredSize());
		JLabel name = new JLabel("Robot Name:");

		final JTextField column = new JTextField(20);
		column.setMaximumSize(robot.getPreferredSize());
		JLabel c = new JLabel("Column:");

		final JTextField row = new JTextField(20);
		row.setMaximumSize(robot.getPreferredSize());
		JLabel r = new JLabel("Row:");

		panel.add(name);
		panel.add(robot);
		panel.add(connect);
		panel.add(c);
		panel.add(column);
		panel.add(r);
		panel.add(row);
		panel.add(drive);

		frame.getContentPane().add(BorderLayout.EAST, panel);

		frame.getContentPane().add(BorderLayout.CENTER, grid);

		grid.repaint();

		grid.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + "," + e.getY());
				colDest = e.getX();
				rowDest = e.getY();
				column.setText(grid.getColumn(colDest) + "");
				row.setText(grid.getRow(rowDest) + "");
			}
		});

		frame.setSize(1000, 750);
		frame.setVisible(true);

	}

	class ConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (client == null) {
				try {
					client = new Client();
					System.out.println("Connected");
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
	}

	class DriveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				client.goToButtonPressed(grid.getColumn(colDest), grid.getRow(rowDest));

				System.out.println(grid.getColumn(colDest) + "," + grid.getRow(rowDest));

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
