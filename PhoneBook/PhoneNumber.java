public class PhoneNumber {
   String phoneNumber;
   
   public PhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }
   
   public String number() {
      return phoneNumber;
   }
   
   @Override
   public String toString() {
      return phoneNumber;
   }
}