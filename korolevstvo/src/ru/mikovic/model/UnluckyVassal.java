package ru.mikovic.model;



import java.util.*;

public class UnluckyVassal extends Entity {

    private King owner;

    public UnluckyVassal() {
        super();
    }

    public UnluckyVassal(String name, String title) {
        super(name, title);
    }

    public void setOwner(King owner) {
        this.owner = owner;
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
                    unluckyVassal.add(vassal);
                }
            }
            owner.getReport().add(unluckyVassal);

        }
        owner.display();
        owner.getReport().stream().sorted(Comparator.comparing(UnluckyVassal::getValue).reversed())
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

        }

        return vassal;
    }
}
