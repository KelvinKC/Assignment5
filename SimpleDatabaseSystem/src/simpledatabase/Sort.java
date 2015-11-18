package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	ArrayList<Tuple> tuplesUnsort;
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		tuplesUnsort = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		Tuple tuple = child.next();
		int min;
		
		while(tuple != null){
			tuplesUnsort.add(tuple);
			tuple = child.next();
		}
		while(tuplesUnsort.size()>0){
				min = 0;
				for(int i = 1; i <= tuplesUnsort.size()-1;i++){
					for(int j =0;j <= tuplesUnsort.get(i).getAttributeList().size()-1;j++){
						if(tuplesUnsort.get(min).getAttributeName(j).toString().equals(orderPredicate)
								&&tuplesUnsort.get(i).getAttributeName(j).equals(orderPredicate)){
							if(tuplesUnsort.get(min).getAttributeValue(j).toString().compareTo(tuplesUnsort.get(i).getAttributeValue(j).toString())>0){
								min = i;
								
							}
						}
					}
				}
				tuplesResult.add(tuplesUnsort.get(min));
				newAttributeList = tuplesUnsort.get(min).getAttributeList();
				tuplesUnsort.remove(min);
				return new Tuple(newAttributeList);
		}
		return null;
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}