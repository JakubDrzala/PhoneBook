public class User {
    public User(){
        isValidUser("Name","Surname","+1234567890","sdadawaf@daw.com");
    }

    private void isValidUser(String name,String surname,String phoneNumber,String eMail){
        String nameRegex = "^[a-zA-Z.'-]+";
        String phoneNumberRegex = "\\+[0-9]{10,15}";
        String mailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

        if (name.matches(nameRegex)){
            System.out.println("name is valid!");
        }else {
            System.out.println("bad name!");
        }

        if (surname.matches(nameRegex)){
            System.out.println("surname is valid!");
        }else {
            System.out.println("bad surname!");
        }

        if (phoneNumber.matches(phoneNumberRegex)){
            System.out.println("phoneNumber is valid!");
        }else {
            System.out.println("bad phoneNumber!");
        }

        if (eMail.matches(mailRegex)){
            System.out.println("eMail is valid!");
        }else {
            System.out.println("bad eMail!");
        }

        //ogolny if
        if(name.matches(nameRegex)&&surname.matches(nameRegex)&&phoneNumber.matches(phoneNumberRegex)&&eMail.matches(mailRegex)){
            System.out.println("All is done!");
        }else {}

    }


}