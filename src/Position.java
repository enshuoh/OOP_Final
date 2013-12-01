public class Position{
	public int x;
	public int y;
	public Position(int x,int y){
		this.x = x;
		this.y = y;
	}
	public Position(Position init){
		this.x = init.x;
		this.y = init.y;
	}
}
