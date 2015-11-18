package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		Tuple tuple = child.next();
		if (child.from.equals(whereTablePredicate)){
			while (tuple != null){
				for (int i=0;i<tuple.getAttributeList().size();i++){
					if (tuple.getAttributeList().get(i).getAttributeName().equals(whereAttributePredicate)&&tuple.getAttributeList().get(i).getAttributeValue().equals(whereValuePredicate)){
						return new Tuple(tuple.getAttributeList());
					}
					
				}
				tuple = child.next();
			}
			return null;
		}else{
			return tuple;
		}
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}