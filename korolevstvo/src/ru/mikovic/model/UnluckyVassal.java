package ru.mikovic.model;



import java.util.*;
import java.util.stream.Collectors;

public class UnluckyVassal extends Entity {

    private King owner;
    private boolean sluga = false;


    public UnluckyVassal() {
        super();
    }

    public UnluckyVassal(String name, String title) {
        super(name, title);
    }

    public void setOwner(King owner) {
        this.owner = owner;
    }

    public boolean isSluga() {
        return sluga;
    }

    public void setSluga(boolean sluga) {
        this.sluga = sluga;
    }

    public void display() {
        super.display();
        if (this.getVassals() != null) {
            pr(this.getVassals(), n);
        }

    }
    String n =" ";
    String b =" ";
    private void pr(List<UnluckyVassal> lt, String n){

            List<UnluckyVassal> list =  lt.stream().sorted(Comparator.comparing(UnluckyVassal :: getTitle)).collect(Collectors.toList());
            for (UnluckyVassal v: list){
                String str = v.getTitle() + " " + v.getName();
                String formatted = str;
                System.out.println(n +str);
                if (v.getVassals()!= null) {
                    n = n + b;
                    pr(v.getVassals(), n);

                }
                if (v.getVassals()!= null) n =" ";
            }

        }



    public void printReportForKing(List<String> pollResults) {
        UnluckyVassal unluckyVassal = null;
        String[] arr = null;
        for (String str : pollResults) {
            arr = str.split(":");
            unluckyVassal = getVassal(arr[0]);
            owner.add(unluckyVassal);
            if (arr.length == 2) {
               String[] vas = arr[1].split(",");
                for(int i = 0; i < vas.length; i++){
                    UnluckyVassal vassal = getVassal(vas[i]);
                    vassal.setSluga(true);
                    unluckyVassal.add(vassal);
                }
            }


        }
        owner.display();
        List<UnluckyVassal> result =  owner.getReport().stream().filter(x-> x.isSluga()==false).collect(Collectors.toList());
        result.stream().sorted(Comparator.comparing(UnluckyVassal::getTitle))
                .forEach(vassal -> vassal.display());


    }

    public UnluckyVassal getVassal(String str) {
        String name;
        String title;

        String[] r = str.split("(?=\\p{Lu})");
        title = r[0].trim();
        if(r.length == 1){
            name= "";
        }else {
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < r.length; i++) {
                sb.append(r[i]);
                sb.append(" ");
            }
            name = sb.toString().trim();
        }
        UnluckyVassal vassal =owner.getReport().stream().filter(x -> title.equals(x.getTitle()))
                .findAny().orElse(null);
        if(vassal == null) {
            vassal = new UnluckyVassal(name, title);
            owner.getReport().add(vassal);

        }

        return vassal;
    }
}
