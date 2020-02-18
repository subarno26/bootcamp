package JavaCore;
//Q4. Calculate the number & Percentage Of Lowercase Letters,Uppercase Letters, Digits And Other Special Characters In A String
public class Q4 {
    public static void main(String[] args) {
        String s = "hello1@34Up@";
        double l = s.length();

        //Counters:
        Integer LowerCounter = 0;
        Integer UpperCounter = 0;
        Integer DigitCounter = 0;
        Integer SpecialCounter = 0;

        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if (Character.isUpperCase(c)){
                UpperCounter++;
            }
            else if(Character.isLowerCase(c)){
                LowerCounter++;
            }
            else if (Character.isDigit(c)){
                DigitCounter++;
            }
            else {
                SpecialCounter++;
            }
        }

        double UpperPercentage = (UpperCounter/l) * 100;
        double LowerPercentage = (LowerCounter/l) * 100;
        double DigitPercentage = (DigitCounter/l) * 100;
        double SpecialPercentage = (SpecialCounter/l) * 100;

        System.out.println("Number of Uppercase Characters: "+ UpperCounter+ "  Percentage: " + UpperPercentage + " %");
        System.out.println("Number of Lowercase Characters: "+ LowerCounter+ "  Percentage: " + LowerPercentage + " %");
        System.out.println("Number of Digits: "+ DigitCounter+"  Percentage: " + DigitPercentage + " %");
        System.out.println("Number of Special characters: "+SpecialCounter+ "  Percentage: "  + SpecialPercentage + " %");

    }
}
