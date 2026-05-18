/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

/**
 *
 * @author an
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class bai4 {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        InetAddress ip = InetAddress.getByName("ptit.store");
        int port = 2209;

        String ma = ";B22DCAT134;urg2z2Ze";
        byte[] sendData = ma.getBytes(StandardCharsets.UTF_8);
        DatagramPacket gui = new DatagramPacket(sendData, sendData.length, ip, port);
        socket.send(gui);

        byte[] buffer = new byte[65535];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);

        byte[] data = nhan.getData();
        int packetLen = nhan.getLength();

        String requestId = new String(data, 0, 8, StandardCharsets.UTF_8);
        System.out.println(requestId);

        ByteArrayInputStream bin = new ByteArrayInputStream(data, 8, packetLen - 8);
        ObjectInputStream oin = new ObjectInputStream(bin);
        Book book = (Book) oin.readObject();
        System.out.println(book);

        // chuan hoa title
        String[] titleWords = book.getTitle().trim().toLowerCase().split("\\s+");
        StringBuilder title = new StringBuilder();
        for (int i = 0; i < titleWords.length; i++) {
            String w = titleWords[i];
            if (w.length() > 0) {
                if (i > 0) {
                    title.append(" ");
                }
                title.append(Character.toUpperCase(w.charAt(0)));
                if (w.length() > 1) {
                    title.append(w.substring(1));
                }
            }
        }
        book.setTitle(title.toString());

        // chuan hoa author 
        String[] authorWords = book.getAuthor().trim().toLowerCase().split("\\s+");
        String ho = authorWords[0].toUpperCase();

        StringBuilder ten = new StringBuilder();
        for (int i = 1; i < authorWords.length; i++) {
            String w = authorWords[i];
            if (i > 1) {
                ten.append(" ");
            }
            ten.append(Character.toUpperCase(w.charAt(0)));
            if (w.length() > 1) {
                ten.append(w.substring(1));
            }
        }

        if (authorWords.length == 1) {
            book.setAuthor(ho);
        } else {
            book.setAuthor(ho + ", " + ten);
        }

        // chuan hoa ISBN 
        String digits = book.getIsbn().replaceAll("[^0-9Xx]", "");
        if (digits.length() == 13) {
            String isbn = digits.substring(0, 3) + "-"
                    + digits.substring(3, 4) + "-"
                    + digits.substring(4, 6) + "-"
                    + digits.substring(6, 12) + "-"
                    + digits.substring(12);
            book.setIsbn(isbn);
        }

        // chuyen publishDate
        String[] dateParts = book.getPublishDate().split("-");
        if (dateParts.length == 3) {
            book.setPublishDate(dateParts[1] + "/" + dateParts[0]);
        }

        // gui
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        bout.write(requestId.getBytes(StandardCharsets.UTF_8));

        ObjectOutputStream oout = new ObjectOutputStream(bout);
        oout.writeObject(book);
        oout.flush();

        byte[] ansData = bout.toByteArray();
        DatagramPacket gui2 = new DatagramPacket(
                ansData,
                ansData.length,
                nhan.getAddress(),
                nhan.getPort()
        );
        socket.send(gui2);

        socket.close();
    }
}
