package es.deusto.sd.eurostyletuning.external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ZILClient {
	private static final String SERVER_ADDRESS = "localhost";
	private static final int SERVER_PORT = 9000;

	public static void main(String[] args) {
		try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				Scanner scanner = new Scanner(System.in)) {

			System.out.println("Connected to server");
			System.out.println("Select an option:");
			System.out.println("1. Get all parts");
			System.out.println("2. Get parts by brand and category");
			System.out.print("Enter your choice (1 or 2): ");

			String choice = scanner.nextLine();

			switch (choice) {
			case "1":
				out.println("getAllParts");
				break;
			case "2":
				System.out.print("Enter brand (e.g., Audi): ");
				String brand = scanner.nextLine();
				System.out.print("Enter category (e.g., Steering Wheel): ");
				String category = scanner.nextLine();
				out.println("getPartsByBrandAndCategory [" + brand + ", " + category + "]");
				break;
			default:
				System.out.println("Invalid option.");
				return;
			}

			// Read and display the server response
			String response;
			while ((response = in.readLine()) != null && !response.isEmpty()) {
				System.out.println("Server Response: " + response);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}