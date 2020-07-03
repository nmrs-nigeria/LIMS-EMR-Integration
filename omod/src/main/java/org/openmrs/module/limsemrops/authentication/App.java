import com.mashape.unirest.*;

/**
 * Hello world! "C:\Users\Mubarak
 * Abdu-Aguye\.vscode\extensions\vscjava.vscode-java-debug-0.25.1\scripts\launcher.bat" "C:\Program
 * Files\Java\jdk-14.0.1\bin\java.exe" "-Dfile.encoding=UTF-8" "@C:\Users\Mubarak
 * Abdu-Aguye\AppData\Local\Temp\cp_d2qfn15yj7eudxsqf57zby37b.argfile" "com.mgic.App"
 */
public final class App {
	
	private App() {
	}
	
	/**
	 * Says hello to the world.
	 * 
	 * @param args The arguments of the program.
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
		AuthModule am = new AuthModule("MAbduAguye@mgic.umaryland.edu", "mubarak@2k1", "https://lims.ng/api/login.php");
		String tk = am.getToken();
		System.out.println(tk);
		
		//HttpResponse<String> response = Unirest.post("https://lims.ng/api/login.php").body("{\"email\": \"MAbduAguye@mgic.umaryland.edu\",\"password\": \"mubarak@2k1\"}").asString();
		//System.out.println(response.getBody());
		//return;
		//HttpResponse<String> response = Unirest.post("https://lims.ng/api/artstatus/read.php").body("{\"jwt\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvbGltcy5uZyIsImF1ZCI6Imh0dHBzOmxpbXMubmciLCJpYXQiOjEzNTY5OTk1MjQsIm5iZiI6MTM1NzAwMDAwMCwiZGF0YSI6eyJpZCI6IjkiLCJmaXJzdG5hbWUiOiJBYmR1LUFndXllIiwibGFzdG5hbWUiOiJNdWJhcmFrIiwiZW1haWwiOiJNQWJkdUFndXllQG1naWMudW1hcnlsYW5kLmVkdSIsImFjY2Vzc19sZXZlbCI6IiJ9fQ.87la_uYLsbysdbmb8JfYSR4nEcg5Oarsy5wyw1YuIz4\"}").asString();
		//System.out.println(response.getBody());
	}
}
