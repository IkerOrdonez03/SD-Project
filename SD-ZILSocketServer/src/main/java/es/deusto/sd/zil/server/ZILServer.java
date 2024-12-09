package es.deusto.sd.zil.server;

import es.deusto.sd.zil.server.entity.Part;
import es.deusto.sd.zil.server.service.ZILService;
import es.deusto.sd.zil.server.service.ZILServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ZILServer {
	private static final int PORT = 9000;
	private static ZILService zilService = new ZILServiceImpl();

	public static void main(String[] args) {
		// Inicializar datos de ejemplo
		DataInitializer dataInitializer = new DataInitializer();
		dataInitializer.initData(zilService);

		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is running (" + PORT + ")...");
			while (true) {
				Socket clientSocket = serverSocket.accept();
				new ClientHandler(clientSocket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ClientHandler extends Thread {
		private Socket socket;

		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

				String request = in.readLine();
				System.out.println("Received request: " + request); // Depuración: Verificar la solicitud

				if (request.equals("getAllParts")) {
					List<Part> parts = zilService.getAllParts();
					for (Part part : parts) {
						out.println(part.toString()); // Imprimir usando el toString() de Part
					}
				}

				if (request.startsWith("getPartsByBrandAndCategory")) {
					// Extract parameters between square brackets
					String parameters = request.substring(request.indexOf("[") + 1, request.indexOf("]"));
					String[] params = parameters.split(",");

					if (params.length == 2) {
						String brandName = params[0].trim();
						String categoryName = params[1].trim();

						List<Part> parts = zilService.getPartsByBrandAndCategory(brandName, categoryName);

						if (parts.isEmpty()) {
							out.println("No parts found for brand: " + brandName + " and category: " + categoryName);
						} else {
							for (Part part : parts) {
								out.println(part.toString());
							}
						}
					} else {
						out.println("Invalid input format. Please provide brand and category.");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}