package org.enrycoweiser.showmethedata.dto.helper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapPreferenceHelper {
    private String mapName;
    private double value;
    private int rank;

    /* BO1 */
    private int teamBanBO1;
    private int oppBanBO1;
    private int deciderBO1;

    /* BO3 */
    private int teamFirstBanBO3;
    private int teamSecondBanBO3;
    private int teamPickBO3;
    private int oppFirstBanBO3;
    private int oppSecondBanBO3;
    private int oppPickBO3;
    private int deciderBO3;

    /* BO5 */
    private int teamBanBO5;
    private int teamPickBO5;
    private int oppBanBO5;
    private int oppPickBO5;
    private int deciderBO5;

    /* CONSTRUCTOR */
    public MapPreferenceHelper(String mapName) {
        this.mapName = mapName;
    }

    /* CUSTOM METHODS */
    public void addTeamBanBO1() {
        teamBanBO1++;
    }

    public void addOppBanBO1() {
        oppBanBO1++;
    }

    public void addDeciderBO1() {
        deciderBO1++;
    }

    public void addTeamFirstBanBO3() {
        teamFirstBanBO3++;
    }

    public void addTeamSecondBanBO3() {
        teamSecondBanBO3++;
    }

    public void addOppFirstBanBO3() {
        oppFirstBanBO3++;
    }

    public void addOppSecondBanBO3() {
        oppSecondBanBO3++;
    }

    public void addTeamPickBO3() {
        teamPickBO3++;
    }

    public void addOppPickBO3() {
        oppPickBO3++;
    }

    public void addDeciderBO3() {
        deciderBO3++;
    }

    public void addTeamBanBO5() {
        teamBanBO5++;
    }

    public void addOppBanBO5() {
        oppBanBO5++;
    }

    public void addTeamPickBO5() {
        teamPickBO5++;
    }

    public void addOppPickBO5() {
        oppPickBO5++;
    }

    public void addDeciderBO5() {
        deciderBO5++;
    }

    /* CUSTOM GETTERS AND SETTERS */
    public int getTeamBanBO3() {
        int sum = teamFirstBanBO3 + teamSecondBanBO3;
        return sum;
    }

    public int getOppBanBO3() {
        int sum = oppFirstBanBO3 + oppSecondBanBO3;
        return sum;
    }

    public int getTeamTotalBans() {
        int sum = teamBanBO1 + getTeamBanBO3() + teamBanBO5;
        return sum;
    }

    public int getOppTotalBans() {
        int sum = oppBanBO1 + getOppBanBO3() + oppBanBO5;
        return sum;
    }

    public int getTeamTotalPicks() {
        int sum = teamPickBO3 + teamPickBO5;
        return sum;
    }

    public int getOppTotalPicks() {
        int sum = oppPickBO3 + oppPickBO5;
        return sum;
    }

    public int getDeciders() {
        int sum = deciderBO1 + deciderBO3 + deciderBO5;
        return sum;
    }

    public int getNumberBO1() {
        int sum = teamBanBO1 + oppBanBO1 + deciderBO1;
        return sum;
    }

    public int getNumberBO3() {
        int sum = getTeamBanBO3() + getOppBanBO3() + getTeamPickBO3() + getOppPickBO3() + deciderBO3;
        return sum;
    }

    /* OTHERS */
    public double calcValue() {
        int numberBO1 = getNumberBO1();
        int numberBO3 = getNumberBO3();

        if(numberBO1 == 0 && numberBO3 == 0) {
            return 0;
        }

        int sumBO1 = (teamBanBO1 * 3) + (oppBanBO1 * 7) + (deciderBO1 * 10);
        int teamBO3 = (teamFirstBanBO3) + (teamSecondBanBO3 * 4) + (teamPickBO3 * 10);
        int oppBO3 = (oppFirstBanBO3 * 7) + (oppSecondBanBO3 * 5) + (oppPickBO3 * 3);
        int sumBO3 = teamBO3 + oppBO3 + (deciderBO3 * 7);

        return (double) (sumBO1 + sumBO3) / (numberBO1 + numberBO3);
    }
}
