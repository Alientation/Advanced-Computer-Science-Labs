public class Employee {
   private String name;
   private int ID;
   
   public Employee(String name) {
      this.name = name;
   }
   
   public Employee(String name, int ID) {
      this.name = name;
      this.ID = ID;
   }
   
   public String name() {
      return name;
   }
   
   public int id() {
      return ID;
   }
   
   @Override
   public String toString(){
      return name;
   }
}