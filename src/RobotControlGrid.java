import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class RobotControlGrid {

	static RobotControlGrid rcg;
	JFrame frame;

	public static void main(String[] args) {
		rcg = new RobotControlGrid();
		rcg.go();
	}

	void go() {
		frame = new JFrame("Robot Navigator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JButton connect = new JButton("Connect");
		JButton drive = new JButton("Drive");

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

		final Grid grid = new Grid();
		frame.getContentPane().add(BorderLayout.CENTER, grid);

		grid.repaint();

		grid.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + "," + e.getY());
				column.setText(grid.getColumn(e.getX()).toString());
				row.setText(grid.getRow(e.getY()).toString());
			}
		});

		frame.setSize(1000, 750);
		frame.setVisible(true);

	}

	class Grid extends JPanel {
		
		int gridSize;
		
		public void paintComponent(Graphics g) {
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			int columns = 9;
			int rows = 6;

			int margin = 50;

			int rowSize = (this.getHeight() - 2 * margin) / (rows - 1);
			int columnSize = (this.getWidth() - 2 * margin) / (columns - 1);

			gridSize = rowSize;

			if (columnSize < rowSize) {
				gridSize = columnSize;
			}

			g.setColor(Color.CYAN);

			for (int i = 0; i < rows; i++) {
				g.drawLine(margin, gridSize * i + margin, gridSize * (columns - 1) + margin, gridSize * i + margin);
			}

			for (int j = 0; j < columns; j++) {
				g.drawLine(gridSize * j + margin, margin, gridSize * j + margin, gridSize * (rows - 1) + margin);
			}
		}

		public Integer getColumn (int xPos) {
			int col = (xPos - xPos%gridSize)/gridSize;
			return new Integer(col);
		}
		
		public Integer getRow (int yPos) {
			int row = (yPos - yPos%gridSize)/gridSize;
			return new Integer(row);
		}
	}
	 
	
	class ConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

		}
	}

	class DriveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}

	}

}
