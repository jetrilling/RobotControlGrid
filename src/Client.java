import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	DataOutputStream dos;
	DataInputStream dis;

	public Client() throws IOException {

		Socket socket = new Socket("10.0.1.1", 8080);
		dos = new DataOutputStream(socket.getOutputStream());
		dis = new DataInputStream(socket.getInputStream());
	}

	public void goToButtonPressed(int col, int row) throws IOException {
		dos.writeInt(col);
		dos.writeInt(row);
		dos.flush();

	}
	
	public int getCol() throws IOException {
		int col = dis.readInt();
		return col;
	}
	
	public int getRow() throws IOException {
		int row = dis.readInt();
		return row;
	}
	
}
