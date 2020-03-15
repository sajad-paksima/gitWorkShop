public class Date {
    private int year;
    private int month;
    private int day;
    public Date(int year, int month, int day){
        setYear(year);
        setMonth(month);
        setDay(day);
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        if (year<0 || year>1400){
            this.year = 0;
            System.out.println("not valid year");
        }
        else {
           this.year = year;
        }
    }
    public int getMonth(){
        return month;
    }
    public void setMonth(int month){
        if (month<1 || month>12){
            this.month = 0;
            System.out.println("not valid month");
        }
        else {
            this.month = month;
        }
    }
    public int getDay(){
        return day;
    }
    public void setDay(int day){
        if (day<1 || day>30){
            this.day = 0;
            System.out.println("not valid day");
        }
        else {
            this.day = day;
        }
    }
    public void printDate(){
        System.out.println(year + "/" + month + "/" + day);
    }
}
