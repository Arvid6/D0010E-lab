import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FIFO implements Queue{
	ArrayList<Object> f = new ArrayList<Object>();
	int ms = 0;
	
	public int size() {
		return f.size();
	}
	
	public int maxSize() {
		return ms;
	}
	
	public boolean isEmpty() {
		if(f.size() == 0) {
			return true;
		}
		return false;
	}
	
	public Object first() throws NoSuchElementException{
		if(f.size() == 0 || f == null){
			throw new NoSuchElementException("There are no elements in Queue");
		}
		return f.get(0);
	}
	
	public boolean equals(Object f) throws ClassCastException{
		if(f.getClass() != this.getClass()) {
			throw new ClassCastException("Inte samma klass");
		}
		if(((FIFO) f).size() != this.size()) {
			return false;
		}
		for(int i = 0; i < this.size(); i++) {
			if((this.f.get(i) == null && ((FIFO) f).f.get(i) != null) || (this.f.get(i) != null && ((FIFO) f).f.get(i) == null)) {
				return false;
			}
	        else if(this.f.get(i) == null && ((FIFO)f).f.get(i) == null) {}
	        else if(this.f.get(i).equals(((FIFO) f).f.get(i))) {}
	        else{
	        	 return false;
	        }
		}	
		return true;
	}
	
	public String toString() {
		String tot = "Queue: ";
		for(int i = 0; i < f.size(); i++) {
			tot += "(" + String.valueOf(f.get(i)) + ") ";
		}
		return tot;
	}
	
	public void add(Object item) {
		f.add(item);
		if(f.size() > ms) {
			ms = f.size();
		}
	}
	
	public void removeFirst() throws NoSuchElementException{
		if(f.size() == 0 || f == null){
			throw new NoSuchElementException("There are no elements in Queue");
		}
		f.remove(0);
	}

}
