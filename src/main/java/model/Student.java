package model;
import Exception.InvalidDataExc;
public class Student {
  private  int student_ID;
  private String name;
  private String email;
  private String phone;
  private double CGPA;
  private String branch;
  private int gradYear;
  public Student(int student_ID,String name,String email,String phone,double CGPA,String branch,int gradYear) throws InvalidDataExc{
      this.student_ID=student_ID;
      setName(name);
      setEmail(email);
      setPhone(phone);
      setCgpa(CGPA);

      this.branch = branch;
      setGradYear(gradYear);
  }
    public int getID(){
      return student_ID;
    }
    public String getName() {
        return name;
    }
    public String getEmail(){
      return email;
    }
    public String getPhone(){
      return phone;
    }
    public double getCgpa() {
        return CGPA;
    }
    public String getBranch(){
      return branch;
    }
    public int getGradYear(){
      return gradYear;
    }

    public void setName(String name) throws InvalidDataExc{
      if(name!=null && !name.isEmpty()) {
          this.name = name;
      } else{
          throw new InvalidDataExc("Check the name field!");
      }
    }
    public void setEmail(String email) throws InvalidDataExc{
      if(!email.isEmpty() && email != null && email.contains("@")){

              this.email = email;
          }
          else {
              throw new InvalidDataExc("Invalid email");
          }

    }
    public void setPhone(String phone) throws InvalidDataExc{
        if (phone != null && phone.length() == 10 && phone.matches("\\d+")) {
            this.phone = phone;
        } else {
           throw new InvalidDataExc("Invalid phone number");
        }
    }
    public void setCgpa(double CGPA) throws InvalidDataExc{
      if(CGPA>=0 && CGPA<=10){
          this.CGPA = CGPA;
      } else{
          throw new InvalidDataExc("Invalid CGPA");
      }
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public void setGradYear(int gradYear) throws InvalidDataExc{
      if(gradYear>=2020 && gradYear<=2035){
          this.gradYear = gradYear;
      }
    }



  public void displayProfile(){
      System.out.println("===== STUDENT PROFILE =====");
     System.out.println("ID:"+student_ID);
        System.out.println("Name:"+name);
        System.out.println("Email:"+email);
        System.out.println("Phone:"+phone);
        System.out.println("CGPA : "+CGPA);
        System.out.println("Branch: "+branch);
        System.out.println("Graduation Year : "+gradYear);
  }

}
