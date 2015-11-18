package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple left = leftChild.next();
		Tuple right = rightChild.next();
		ArrayList<Attribute> temp = new ArrayList<Attribute>();
		
		while (left != null){
			tuples1.add(left);
			left = leftChild.next();
		}
		
		while(right != null){
			for (int i = 0;i<right.getAttributeList().size();i++){
				for (Tuple leftTemp:tuples1){
					for(int j = 0;j<leftTemp.getAttributeList().size();j++){
						if(right.getAttributeName(i).equals(leftTemp.getAttributeName(j))
						&& right.getAttributeValue(i).equals(leftTemp.getAttributeValue(j))){
							temp.addAll(leftTemp.getAttributeList());
							temp.addAll(right.getAttributeList());
							return new Tuple(temp);
						}
					}
				}
			}
			right = rightChild.next();
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}