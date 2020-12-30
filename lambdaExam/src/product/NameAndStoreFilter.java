package product;

public class NameAndStoreFilter implements FilterPredicate {
	private String[] mContents;
	
	public NameAndStoreFilter(String... args) {
		mContents = args;
	}

	@Override
	public boolean filter(Product product) {
		if (product.getName().equals(mContents[0]) && product.getStore().equals(mContents[1])) {
			return true;
		}
		return false;
	}

}
