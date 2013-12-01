public class LeavePoint{
	public Position mapPos;
	public int destMapID;
	public Position destInitPos;
	
	public LeavePoint(Position mapPos,int destMapID,Position destInitPos){
		this.mapPos = mapPos;
		this.destMapID = destMapID;
		this.destInitPos = destInitPos;
	}
	public LeavePoint(LeavePoint init){
		this.mapPos = init.mapPos;
		this.destMapID = init.destMapID;
		this.destInitPos = init.destInitPos;
	}

}