package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Client {
    public Client(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public Client(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.phone = br.readLine();
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(phone + '\n');
    }
    @Override
    public String toString() {
        return name + " (" + phone + ")";
    }
    String name;
    String phone;
}
