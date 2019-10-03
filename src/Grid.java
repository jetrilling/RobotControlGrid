import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class Grid extends JPanel {
	
	int gridSize;
	
	int currentX;
	int currentY;

	
	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		int columns = 9;
		int rows = 5;

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
		
		g.fillOval(currentX * gridSize + margin - 5, currentY * gridSize + margin - 5, 10, 10);
	}

	public int getColumn (int xPos) {
		int col = (xPos - xPos%gridSize)/gridSize;
		return col;
	}
	
	public int getRow (int yPos) {
		int row = (yPos - yPos%gridSize)/gridSize;
		return row;
	}

	public void setRobotPosition(int robotRow, int robotColumn) {
		currentX = robotColumn;
		currentY = robotRow;

	}

}

