public class Person{
   String name;
   
   public Person(String name) {
      this.name = name;
   }
   
   public String name() {
      return name;
   }
   
   @Override
   public int hashCode() {
      int hc = 0;
      for (char c : name.toCharArray())
         hc += c;
      return hc;
   }
   
   @Override
   public String toString() {
      return name;
   }
   
   @Override
   public boolean equals(Object p) {
      return p.hashCode() == hashCode();
   }
   
   
}