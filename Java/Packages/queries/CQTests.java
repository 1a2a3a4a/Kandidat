
public class CQTests {
	public static void main(String[] args){
		CQ cq = new CQ();
		cq.connect();

		
		
		//cq.sendQuestionToDB("What does squirrels like the most", "Acorns", "Bark", "Berries", "Dogs");
		//cq.addUser("Daniel", "DPWD123");
		//cq.SendFR(1 , 4);
		//cq.SendFR(2 , 4);
		//cq.acceptFR(4, 1);
		//AcceptFR(4, 2);
		//ResultSet friend_list = FetchPendingFriendRequests(4);
		
		//PrintFL(friend_list);
		//AcceptFR(4, 1);
		//PrintFL(FetchPendingFriendRequests(4));
		//PrintFL(friend_list);
		//RemoveUser();



		cq.disconnect();

	}
}
