public interface IMap 
{
	/**
	 * Add the key/value pair to the table
	 * @return the number previously associated with person, or null
	 */
	public PhoneNumber put(Person person, PhoneNumber phone);

	/**
	 * perform a table lookup
	 * @param person
	 * @return phone number mapped to this person
	 */
	public PhoneNumber get(Person person);

	/** return the current number of key/value pairs in the map */
	public int size();
	
	/**
	 * Remove supplied key (person)
	 * @return value mapped to supplied person, or null
	 */
	public PhoneNumber remove(Person person);
   
   /**
   * Prints all Entries in the array (iterating through each list)
   *
   */
   public void printHashTable();
}
