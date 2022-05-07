public class Person{

   private String name;
   
   public Person(String name){
      this.name = name;
   }
   
   public String getName(){
      return name;
   }
   
   public int hashCode(){
      int sum=0;
      for (int i = 0; i < name.length(); i++){
         int asciiValue = name.charAt(i);
         sum = sum+ asciiValue;
      }
      return sum;
   }
   
   public String toString(){
      return name;
   }

   public boolean equals(Object obj){
      Person str = (Person)obj;
      if (str.name.equals(name))
         return true;
      return false;
   }
}