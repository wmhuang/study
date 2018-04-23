package design.strategy;

public class Context {
	private IsStrategy isStrategy;
	
	
	public Context(IsStrategy isStrategy){
		this.isStrategy = isStrategy;
	}
	
	public void opreate(){
		this.isStrategy.operate();
	}
}
