package product;

public class NameFilter implements FilterPredicate {
	private String[] mContents;
	
	public NameFilter(String... args) {
		mContents = args;
	}
	
	@Override
	public boolean filter(Product product) {
		if (product.getName().equals(mContents[0])) {
			return true;
		}
		return false;
	}

}
