import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.omg.PortableInterceptor.USER_EXCEPTION;

public class Example {
	/*
	 * 65-90 A,B,..,Z ... 97-122 a,b,...,z
	 */
	byte[] asciiChar = null;
	String message = "";
	String cipherText = "";
	String decryptedText = "";

	public static void main(String[] args) throws UnsupportedEncodingException {
		String message, cipherText;
		int key;
		Example example = new Example();
		System.out.println("  NOT:\n* Anahtar 0-26 aralığında olmalıdır \n* Türkçe karakter olmamalı\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("Anahtarı giriniz: ");
		key = scan.nextInt();
		scan = new Scanner(System.in);
		System.out.println("Mesajı giriniz: ");
		message = scan.nextLine();
		example.encrypt(message, key);

		System.out.println("Çözmek için şifreli mesajı giriniz: ");
		cipherText = scan.nextLine();
		System.out.println("Anahtarı giriniz: ");
		key = scan.nextInt();
		scan = new Scanner(System.in);

		example.decrypt(cipherText, key);
	}

	public void encrypt(String message, int key) throws UnsupportedEncodingException {
		this.message = message;
		asciiChar = message.getBytes("US-ASCII");
		for (int i = 0; i < asciiChar.length; i++) {
			int tmp;
			if (asciiChar[i] >= 97 && asciiChar[i] <= 122) { // küçük alfabe aralığındaysa bunları yap
				if ((asciiChar[i] + key) > 122) {
					tmp = 96 + (key - (122 - asciiChar[i]));
					cipherText += (char) (tmp);
				} else {
					cipherText += (char) ((asciiChar[i] + key));
				}

			} else if (asciiChar[i] >= 65 && asciiChar[i] <= 90) { // büyük alfabe aralığındaysa bunları yap
				if ((asciiChar[i] + key) > 90) {
					tmp = 64 + (key - (90 - asciiChar[i]));
					cipherText += (char) (tmp);
				} else {
					cipherText += (char) ((asciiChar[i] + key));
				}
			} else {
				if (asciiChar[i] == 32) {
					cipherText += (char) ((asciiChar[i]));
				}
			}
		}
		System.out.println("Mesajın şifrelenmiş hali: " + cipherText + "\n________________________");
	}

	public void decrypt(String cipherText, int key) throws UnsupportedEncodingException {

		asciiChar = cipherText.getBytes("US-ASCII");
		for (int i = 0; i < asciiChar.length; i++) {
			int tmp;
			if (asciiChar[i] >= 97 && asciiChar[i] <= 122) { // küçük alfabe aralığındaysa bunları yap
				if ((asciiChar[i] - key) < 97) {
					tmp = 123 - (97 - (asciiChar[i] - key));
					decryptedText += (char) (tmp);
				} else {
					decryptedText += (char) ((asciiChar[i] - key));
				}

			} else if (asciiChar[i] >= 65 && asciiChar[i] <= 90) { // büyük alfabe aralığındaysa bunları yap
				if ((asciiChar[i] - key) < 65) {
					tmp = 91 - (65 - (asciiChar[i] - key));
					decryptedText += (char) (tmp);
				} else {
					decryptedText += (char) ((asciiChar[i] - key));
				}
			} else {
				if (asciiChar[i] == 32) {
					decryptedText += (char) ((asciiChar[i]));
				}
			}
		}

		if (message.equals(decryptedText)) {
			System.out.println("Şifre Çözüldü.\n" + "Mesajın şifresinin çözülmüş hali: " + decryptedText);
		} else {
			System.out.println("Şifre çözülemedi. Anahtarı kontrol edin.");
		}

	}

}
