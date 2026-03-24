package org.enrycoweiser.showmethedata.statics;

import org.enrycoweiser.showmethedata.domain.Bombsite;
import org.enrycoweiser.showmethedata.domain.Round;
import org.enrycoweiser.showmethedata.domain.Team;
import org.enrycoweiser.showmethedata.dto.response.MapStatsTile;
import org.enrycoweiser.showmethedata.utils.GeneralUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapStatsStatic {

    public static List<List<MapStatsTile>> getMapStatsTiles(List<Round> roundsAll, String team, List<Bombsite> bombsiteAll) {

        Map<String, List<Bombsite>> mapBombsiteMap = createMapBombsiteMap(bombsiteAll);
        Map<String, Map<Bombsite, MapStatsTile>> mapBombsiteAttack = createHelperMap(mapBombsiteMap);
        Map<String, Map<Bombsite, MapStatsTile>> mapBombsiteDefence = createHelperMap(mapBombsiteMap);

        if(team != null && !team.isEmpty()) {
            populateMapBombsiteSideByTeam(roundsAll, team, mapBombsiteAttack, mapBombsiteDefence);
        } else {
            populateMapBombsiteSide(roundsAll, mapBombsiteAttack, mapBombsiteDefence);
        }

        List<MapStatsTile> attackTiles = createFinalTiles(mapBombsiteAttack);
        List<MapStatsTile> defenceTiles = createFinalTiles(mapBombsiteDefence);

        attackTiles = orderTiles(attackTiles);
        defenceTiles = orderTiles(defenceTiles);

        List<List<MapStatsTile>> allList = new ArrayList<>();
        allList.add(attackTiles);
        allList.add(defenceTiles);
        return allList;
    }

    private static Map<String, List<Bombsite>> createMapBombsiteMap(List<Bombsite> bombsiteAll) {
        Map<String, List<Bombsite>> mapBombsiteMap = new HashMap<>();

        for (Bombsite bombsite : bombsiteAll) {
            String map = bombsite.getMap();
            mapBombsiteMap.putIfAbsent(map, new ArrayList<>());
            mapBombsiteMap.get(map).add(bombsite);
        }


        return mapBombsiteMap;
    }

    private static Map<String, Map<Bombsite, MapStatsTile>> createHelperMap(Map<String, List<Bombsite>> mapBombsiteMap) {
        Map<String, Map<Bombsite, MapStatsTile>> mapHelper = new HashMap<>();
        for (Map.Entry<String, List<Bombsite>> entry : mapBombsiteMap.entrySet()) {
            String mapName = entry.getKey();
            List<Bombsite> bombsites = entry.getValue();
            mapHelper.putIfAbsent(mapName, new HashMap<>());
            for (Bombsite bombsite : bombsites) {
                mapHelper.get(mapName).put(bombsite, new MapStatsTile());
            }
        }

        return mapHelper;
    }

    private static void populateMapBombsiteSideByTeam(List<Round> roundsAll, String team,
                                                      Map<String, Map<Bombsite, MapStatsTile>> attackMap, Map<String, Map<Bombsite, MapStatsTile>> defenceMap) {
        for (Round round : roundsAll) {
            String mapName = round.getPlayedMap().getName().toUpperCase();
            Bombsite bombsite = round.getBombsite();

            boolean myTeamA = round.getPlayedMap().getMatch().getTeamA().getCode().equals(team);
            boolean myTeamDef = myTeamA ? round.getTeamARole().equals(GeneralUtil.ROLE_DEF) : round.getTeamBRole().equals(GeneralUtil.ROLE_DEF);
            boolean myTeamWin = round.getWinTeam().getCode().equals(team);
            boolean myTeamEntry = round.getEntryTeam().getCode().equals(team);
            boolean isPlant = round.getPlant().equals(GeneralUtil.YES);

            if(myTeamDef) {
                MapStatsTile defence = defenceMap.get(mapName).get(bombsite);
                defence.addRound();
                if(isPlant) {
                    defence.addPlant();
                    if(myTeamWin) {
                        defence.addPlantWin();
                    }
                }
                if(myTeamWin) {
                    defence.addWin();
                    if(myTeamEntry) {
                        defence.addEntryKill();
                        defence.addEntryKillWin();
                    } else {
                        defence.addEntryDeathWin();
                    }
                } else if(myTeamEntry) {
                    defence.addEntryKill();
                }
            } else {
                MapStatsTile attack = attackMap.get(mapName).get(bombsite);
                attack.addRound();
                if(isPlant) {
                    attack.addPlant();
                    if(myTeamWin) {
                        attack.addPlantWin();
                    }
                }
                if(myTeamWin) {
                    attack.addWin();
                    if(myTeamEntry) {
                        attack.addEntryKill();
                        attack.addEntryKillWin();
                    } else {
                        attack.addEntryDeathWin();
                    }
                } else if(myTeamEntry) {
                    attack.addEntryKill();
                }
            }
        }
    }

    private static void populateMapBombsiteSide(List<Round> roundsAll, Map<String, Map<Bombsite, MapStatsTile>> attackMap, Map<String, Map<Bombsite, MapStatsTile>> defenceMap) {
        for (Round round : roundsAll) {
            String mapName = round.getPlayedMap().getName().toUpperCase();
            Bombsite bombsite = round.getBombsite();

            attackMap.get(mapName).get(bombsite).addRound();
            defenceMap.get(mapName).get(bombsite).addRound();

            Team teamA = round.getPlayedMap().getMatch().getTeamA();
            boolean defTeamA = round.getTeamARole().equals(GeneralUtil.ROLE_DEF);
            boolean plant = (round.getPlant().equals(GeneralUtil.YES));

            if(plant) {
                defenceMap.get(mapName).get(bombsite).addPlant();
                attackMap.get(mapName).get(bombsite).addPlant();
            }

            boolean entryTeamA = round.getEntryTeam().equals(teamA);
            if(entryTeamA == defTeamA) {
                defenceMap.get(mapName).get(bombsite).addEntryKill();
            } else {
                attackMap.get(mapName).get(bombsite).addEntryKill();
            }

            boolean winTeamA = round.getWinTeam().equals(teamA);
            if(winTeamA == defTeamA) {
                /* defence win */
                defenceMap.get(mapName).get(bombsite).addWin();
                if(plant) {
                    defenceMap.get(mapName).get(bombsite).addPlantWin();
                }
            } else {
                /* attack win */
                attackMap.get(mapName).get(bombsite).addWin();
                if(plant) {
                    attackMap.get(mapName).get(bombsite).addPlantWin();
                }
            }

            /* I'm not good enough to use the logic above for EntryKillWin / EntryLoseWin */
            /* So I will duplicate part of it. I'll take teamA since teamB is the exact opposite */

            boolean myTeamWin = winTeamA;
            boolean myTeamDefence = defTeamA;
            boolean myTeamEntry = entryTeamA;

            /* teamA entry and teamA win */
            if(myTeamEntry && myTeamWin) {
                if(myTeamDefence) {
                    defenceMap.get(mapName).get(bombsite).addEntryKillWin();
                } else {
                    attackMap.get(mapName).get(bombsite).addEntryKillWin();
                }
            }

            /* teamB entry and teamB win */
            if(!myTeamEntry && !myTeamWin) {
                if(myTeamDefence) {
                    attackMap.get(mapName).get(bombsite).addEntryKillWin();
                } else {
                    defenceMap.get(mapName).get(bombsite).addEntryKillWin();
                }
            }

            /* teamA entry and teamB win */
            if(myTeamEntry && !myTeamWin) {
                if(myTeamDefence) {
                    attackMap.get(mapName).get(bombsite).addEntryDeathWin();
                } else {
                    defenceMap.get(mapName).get(bombsite).addEntryDeathWin();
                }
            }

            /* teamB entry and teamA win */
            if(!myTeamEntry && myTeamWin) {
                if(myTeamDefence) {
                    defenceMap.get(mapName).get(bombsite).addEntryDeathWin();
                } else {
                    attackMap.get(mapName).get(bombsite).addEntryDeathWin();
                }
            }
        }
    }

    private static List<MapStatsTile> createFinalTiles(Map<String, Map<Bombsite, MapStatsTile>> mapBombsiteMap) {
        List<MapStatsTile> tiles = new ArrayList<>();

        for (Map.Entry<String, Map<Bombsite, MapStatsTile>> entryMap : mapBombsiteMap.entrySet()) {
            String mapName = entryMap.getKey();

            int roundSum = 0;
            int winSum = 0;
            int entryKillSum = 0;
            int entryKillWinSum = 0;
            int entryDeathWinSum = 0;
            int plantSum = 0;
            int plantWinSum = 0;

            for (Map.Entry<Bombsite, MapStatsTile> entry : entryMap.getValue().entrySet()) {
                String bombsite = entry.getKey().getName();

                MapStatsTile tile = entry.getValue();
                tile.setMap(mapName);
                tile.setBombsite(bombsite);
                tiles.add(tile);

                roundSum += tile.getRound();
                winSum += tile.getWin();
                entryKillSum += tile.getEntryKill();
                entryKillWinSum += tile.getEntryKillWin();
                entryDeathWinSum += tile.getEntryDeathWin();
                plantSum += tile.getPlant();
                plantWinSum += tile.getPlantWin();
            }

            /* I create a null-bombsite MapStatsTile which will be the map sum */
            MapStatsTile sum = new MapStatsTile();
            sum.setMap(mapName);
            sum.setBombsite(null);
            sum.setRound(roundSum);
            sum.setWin(winSum);
            sum.setEntryKill(entryKillSum);
            sum.setEntryKillWin(entryKillWinSum);
            sum.setEntryDeathWin(entryDeathWinSum);
            sum.setPlant(plantSum);
            sum.setPlantWin(plantWinSum);
            tiles.add(sum);
        }

        return tiles;
    }

    private static List<MapStatsTile> orderTiles(List<MapStatsTile> tiles) {
        List<MapStatsTile> ordered = tiles
                .stream()
                .sorted(Comparator
                        .comparing(MapStatsTile::getMap)
                        .thenComparing(MapStatsTile::getBombsite, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());

        return ordered;
    }
}
