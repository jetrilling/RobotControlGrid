import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	DataOutputStream dos;

	public Client() throws IOException {

		Socket socket = new Socket("10.0.1.1", 8080);
		dos = new DataOutputStream(socket.getOutputStream());
	}

	public void goToButtonPressed(int row, int col) throws IOException {
		dos.writeInt(row);
		dos.writeInt(col);
		dos.flush();
	}
}
