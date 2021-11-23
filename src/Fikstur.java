import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fikstur {
    private ArrayList<String> teams=new ArrayList<>();
    private Map<String,String[]> fixture=new HashMap<>();

    public Fikstur(String[] teams) {
        for (int i = 0; i < teams.length; i++) {
            this.teams.add(teams[i]);
        }
        if (this.teams.size() % 2 != 0) this.teams.add("Bay");
        showTeams();
        createFixture();
    }


    public ArrayList<String> getTeams(){
            return teams;
    }

    private void showTeams() {
            System.out.println("Ligdeki takimlar:");
            for(String t : teams) {
                if(t.equals("Bay")) continue;
                System.out.println("\t" + t);
            }
            System.out.println();
    }

    private void createFixture() {
        int toplamMacSayisi = (this.teams.size() - 1) * 2;
        List<String> takimlar1 = this.teams.subList(0, this.teams.size() / 2);
        List<String> takimlar2 = this.teams.subList(this.teams.size() / 2, this.teams.size());

        for(int i = 0; i < toplamMacSayisi; i++) {
            if(i == (toplamMacSayisi / 2)) {
                List<String> temp = new ArrayList<String>(takimlar1);
                takimlar1 = new ArrayList<String>(takimlar2);
                takimlar2 = new ArrayList<String>(temp);
            }

            String[] maclar = new String[this.teams.size() / 2];
            for(int j = 0; j < maclar.length; j++) {
                maclar[j] = takimlar1.get(j) + " vs " + takimlar2.get(j);
            }

            fixture.put("Round" + (i + 1), maclar);

            List<String>[] res = rotate(takimlar1, takimlar2);
            takimlar1 = res[0];
            takimlar2 = res[1];
        }
    }

    private List<String>[] rotate(List<String> l1, List<String> l2) {
        String temp = l1.get(l1.size()-1);
        for(int i = l1.size()-1; i > 1; i--) {
            l1.set(i, l1.get(i-1));
        }
        l1.set(1, l2.get(0));

        for(int i = 0; i < l2.size() - 1; i++) {
            l2.set(i, l2.get(i+1));
        }
        l2.set(l2.size()-1, temp);

        List<String>[] ret = new List[2];
        ret[0] = l1;
        ret[1] = l2;

        return ret;
    }

    public void showFixture() {
        System.out.println("Fikstur\n------------------------------------------");

        int roundStr = 1;
        for(String[] sr : this.fixture.values()) {
            System.out.println("Round " + roundStr);
            for(String s : sr) {
                System.out.println(s);
            }
            System.out.println();
            roundStr++;
        }
    }

}

