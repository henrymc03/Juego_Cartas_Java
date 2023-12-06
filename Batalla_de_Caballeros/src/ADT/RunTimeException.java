package ADT;

public class RunTimeException extends Exception{

	private String errorMsg;
	
	public RunTimeException(String errorMsg) {
		// TODO Auto-generated constructor stub
		this.errorMsg = errorMsg;
	}
	
	public String getMessage(){
		return errorMsg;
	}
}
