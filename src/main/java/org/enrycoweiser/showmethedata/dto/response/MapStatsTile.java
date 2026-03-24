package org.enrycoweiser.showmethedata.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapStatsTile {
    private String map;
    private String bombsite;
    private int round;
    private int win;
    private String winPerc;
    private int entryKill;
    private String entryKillPerc;
    private int entryKillWin;
    private String entryKillWinPerc;
    private int entryDeathWin;
    private String entryDeathWinPerc;
    private int plant;
    private String plantPerc;
    private int plantWin;
    private String plantWinPerc;

    /* CUSTOM METHODS */

    public void addRound() {
        round++;
    }

    public void addWin() {
        win++;
    }

    public void addEntryKill() {
        entryKill++;
    }

    public void addEntryKillWin() {
        entryKillWin++;
    }

    public void addEntryDeathWin() {
        entryDeathWin++;
    }

    public void addPlant() {
        plant++;
    }

    public void addPlantWin() {
        plantWin++;
    }

    public String getPerc(int number1, int number2) {
        if(number2 == 0) {
            return " - ";
        }

        double perc = ((double) number1 / (double) number2) * 100.0;

        return String.format("%.0f", perc) + "%";
    }
}
