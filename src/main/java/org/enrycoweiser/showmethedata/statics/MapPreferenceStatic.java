package org.enrycoweiser.showmethedata.statics;

import org.enrycoweiser.showmethedata.domain.BanMap;
import org.enrycoweiser.showmethedata.dto.helper.MapPreferenceHelper;
import org.enrycoweiser.showmethedata.dto.response.MapPreferenceTile;
import org.enrycoweiser.showmethedata.utils.GeneralUtil;

import java.util.*;
import java.util.stream.Collectors;

public class MapPreferenceStatic {
    public static List<MapPreferenceTile> getMapPreferenceTiles(List<BanMap> banMapAll, String team) {
        List<BanMap> banMapBestOf1s = new ArrayList<>();
        List<BanMap> banMapBestOf3s = new ArrayList<>();
        List<BanMap> banMapBestOf5s = new ArrayList<>();

        Map<String, MapPreferenceHelper> helperMap = createHelperMap();

        for (BanMap banMap : banMapAll) {
            switch (banMap.getBanMapType().getCode().charAt(2)) {
                case '1' -> banMapBestOf1s.add(banMap);
                case '3' -> banMapBestOf3s.add(banMap);
                case '5' -> banMapBestOf5s.add(banMap);
                default -> {}
            }
        }

        populateHelperMapBO1(helperMap, banMapBestOf1s, team);
        populateHelperMapBO3(helperMap, banMapBestOf3s, team);
        populateHelperMapBO5(helperMap, banMapBestOf5s, team);

        List<MapPreferenceHelper> helperFinal = new ArrayList<>(helperMap.values());

        helperFinal = valueRanks(helperFinal);

        List<MapPreferenceTile> tiles = new ArrayList<>();
        for (MapPreferenceHelper helper : helperFinal) {
            MapPreferenceTile tile = new MapPreferenceTile();
            tile.setMapName(helper.getMapName());
            tile.setBan(helper.getTeamTotalBans());
            tile.setPick(helper.getTeamTotalPicks());
            tile.setOppBan(helper.getOppTotalBans());
            tile.setOppPick(helper.getOppTotalPicks());
            tile.setDecider(helper.getDeciders());
            tile.setValue(helper.getValue());
            tile.setRank(helper.getRank());
            tiles.add(tile);
        }

        return tiles;
    }

    private static List<MapPreferenceHelper> valueRanks(List<MapPreferenceHelper> helperFinal) {
        List<MapPreferenceHelper> helpers = helperFinal.stream().sorted(Comparator.comparingDouble(MapPreferenceHelper::getValue)).collect(Collectors.toList());

        int rank = 9;
        for (MapPreferenceHelper helper : helpers) {
            helper.setRank(rank);
            rank--;
        }

        helpers = helpers.stream().sorted(Comparator.comparing(MapPreferenceHelper::getMapName)).collect(Collectors.toList());

        return helpers;
    }

    private static Map<String, MapPreferenceHelper> createHelperMap() {
        Map<String, MapPreferenceHelper> helperMap = new HashMap<>();

        helperMap.put(GeneralUtil.MAP_BANK, new MapPreferenceHelper(GeneralUtil.MAP_BANK));
        helperMap.put(GeneralUtil.MAP_BORDER, new MapPreferenceHelper(GeneralUtil.MAP_BORDER));
        helperMap.put(GeneralUtil.MAP_CHALET, new MapPreferenceHelper(GeneralUtil.MAP_CHALET));
        helperMap.put(GeneralUtil.MAP_CLUBHOUSE, new MapPreferenceHelper(GeneralUtil.MAP_CLUBHOUSE));
        helperMap.put(GeneralUtil.MAP_CONSULATE, new MapPreferenceHelper(GeneralUtil.MAP_CONSULATE));
        helperMap.put(GeneralUtil.MAP_FORTRESS, new MapPreferenceHelper(GeneralUtil.MAP_FORTRESS));
        helperMap.put(GeneralUtil.MAP_KAFE_DOSTOYEVKSY, new MapPreferenceHelper(GeneralUtil.MAP_KAFE_DOSTOYEVKSY));
        helperMap.put(GeneralUtil.MAP_LAIR, new MapPreferenceHelper(GeneralUtil.MAP_LAIR));
        helperMap.put(GeneralUtil.MAP_NIGHTHAVEN_LABS, new MapPreferenceHelper(GeneralUtil.MAP_NIGHTHAVEN_LABS));

        return helperMap;
    }

    private static void populateHelperMapBO1(Map<String, MapPreferenceHelper> helperMap, List<BanMap> banMaps, String team) {
        for (BanMap bm : banMaps) {
            boolean isTeamA = bm.getMatch().getTeamA().getCode().equals(team);
            boolean choiceTeamA;

            String firstMap = bm.getFirst();
            String secondMap = bm.getSecond();
            String thirdMap = bm.getThird();
            String fourthMap = bm.getFourth();
            String fifthMap = bm.getFifth();
            String sixthMap = bm.getSixth();
            String seventhMap = bm.getSeventh();
            String eighthMap = bm.getEighth();
            String decider = bm.getDecider();

            /* FIRST MAP */
            choiceTeamA = bm.getBanMapType().getFirst().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(firstMap).addTeamBanBO1();
            } else {
                helperMap.get(firstMap).addOppBanBO1();
            }

            /* SECOND MAP */
            choiceTeamA = bm.getBanMapType().getSecond().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(secondMap).addTeamBanBO1();
            } else {
                helperMap.get(secondMap).addOppBanBO1();
            }

            /* THIRD MAP */
            choiceTeamA = bm.getBanMapType().getThird().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(thirdMap).addTeamBanBO1();
            } else {
                helperMap.get(thirdMap).addOppBanBO1();
            }

            /* FOURTH MAP */
            choiceTeamA = bm.getBanMapType().getFourth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(fourthMap).addTeamBanBO1();
            } else {
                helperMap.get(fourthMap).addOppBanBO1();
            }

            /* FIFTH MAP */
            choiceTeamA = bm.getBanMapType().getFifth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(fifthMap).addTeamBanBO1();
            } else {
                helperMap.get(fifthMap).addOppBanBO1();
            }

            /* SIXTH MAP */
            choiceTeamA = bm.getBanMapType().getSixth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(sixthMap).addTeamBanBO1();
            } else {
                helperMap.get(sixthMap).addOppBanBO1();
            }

            /* SEVENTH MAP */
            choiceTeamA = bm.getBanMapType().getSeventh().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(seventhMap).addTeamBanBO1();
            } else {
                helperMap.get(seventhMap).addOppBanBO1();
            }

            /* EIGHT MAP */
            choiceTeamA = bm.getBanMapType().getEighth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(eighthMap).addTeamBanBO1();
            } else {
                helperMap.get(eighthMap).addOppBanBO1();
            }

            /* DECIDER */
            helperMap.get(decider).addDeciderBO1();
        }
    }

    // the only bo3 ban map type I consider is BB-BB-PP-BB-D  -> if I see there are other type I will modify the method
    private static void populateHelperMapBO3(Map<String, MapPreferenceHelper> helperMap, List<BanMap> banMaps, String team) {
        for (BanMap bm : banMaps) {
            boolean isTeamA = bm.getMatch().getTeamA().getCode().equals(team);
            boolean choiceTeamA;

            String firstMap = bm.getFirst();
            String secondMap = bm.getSecond();
            String thirdMap = bm.getThird();
            String fourthMap = bm.getFourth();
            String fifthMap = bm.getFifth();
            String sixthMap = bm.getSixth();
            String seventhMap = bm.getSeventh();
            String eighthMap = bm.getEighth();
            String decider = bm.getDecider();

            /* FIRST MAP */
            choiceTeamA = bm.getBanMapType().getFirst().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(firstMap).addTeamFirstBanBO3();
            } else {
                helperMap.get(firstMap).addOppFirstBanBO3();
            }

            /* SECOND MAP */
            choiceTeamA = bm.getBanMapType().getSecond().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(secondMap).addTeamFirstBanBO3();
            } else {
                helperMap.get(secondMap).addOppFirstBanBO3();
            }

            /* THIRD MAP */
            choiceTeamA = bm.getBanMapType().getThird().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(thirdMap).addTeamFirstBanBO3();
            } else {
                helperMap.get(thirdMap).addOppFirstBanBO3();
            }

            /* FOURTH MAP */
            choiceTeamA = bm.getBanMapType().getFourth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(fourthMap).addTeamFirstBanBO3();
            } else {
                helperMap.get(fourthMap).addOppFirstBanBO3();
            }

            /* FIFTH MAP - FIRST PICK */
            choiceTeamA = bm.getBanMapType().getFifth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(fifthMap).addTeamPickBO3();
            } else {
                helperMap.get(fifthMap).addOppPickBO3();
            }

            /* SIXTH MAP - FIRST PICK */
            choiceTeamA = bm.getBanMapType().getSixth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(sixthMap).addTeamPickBO3();
            } else {
                helperMap.get(sixthMap).addOppPickBO3();
            }

            /* SEVENTH MAP */
            choiceTeamA = bm.getBanMapType().getSeventh().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(seventhMap).addTeamSecondBanBO3();
            } else {
                helperMap.get(seventhMap).addOppSecondBanBO3();
            }

            /* EIGHT MAP */
            choiceTeamA = bm.getBanMapType().getEighth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                helperMap.get(eighthMap).addTeamSecondBanBO3();
            } else {
                helperMap.get(eighthMap).addOppSecondBanBO3();
            }

            /* DECIDER */
            helperMap.get(decider).addDeciderBO3();
        }
    }

    private static void populateHelperMapBO5(Map<String, MapPreferenceHelper> helperMap, List<BanMap> banMaps, String team) {
        for (BanMap bm : banMaps) {
            boolean isTeamA = bm.getMatch().getTeamA().getCode().equals(team);
            boolean choiceTeamA;
            boolean isPick;

            String firstMap = bm.getFirst();
            String secondMap = bm.getSecond();
            String thirdMap = bm.getThird();
            String fourthMap = bm.getFourth();
            String fifthMap = bm.getFifth();
            String sixthMap = bm.getSixth();
            String seventhMap = bm.getSeventh();
            String eighthMap = bm.getEighth();
            String decider = bm.getDecider();

            /* FIRST MAP */
            choiceTeamA = bm.getBanMapType().getFirst().charAt(0) == 'A';
            isPick = bm.getBanMapType().getFirst().charAt(2) == 'P';
            if(choiceTeamA == isTeamA) {
                if(isPick) {
                    helperMap.get(firstMap).addTeamPickBO5();
                } else {
                    helperMap.get(firstMap).addTeamBanBO5();
                }
            } else {
                if(isPick) {
                    helperMap.get(firstMap).addOppPickBO5();
                } else {
                    helperMap.get(firstMap).addOppBanBO5();
                }
            }

            /* SECOND MAP */
            choiceTeamA = bm.getBanMapType().getSecond().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                if(isPick) {
                    helperMap.get(secondMap).addTeamPickBO5();
                } else {
                    helperMap.get(secondMap).addTeamBanBO5();
                }
            } else {
                if(isPick) {
                    helperMap.get(secondMap).addOppPickBO5();
                } else {
                    helperMap.get(secondMap).addOppBanBO5();
                }
            }

            /* THIRD MAP */
            choiceTeamA = bm.getBanMapType().getThird().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                if(isPick) {
                    helperMap.get(thirdMap).addTeamPickBO5();
                } else {
                    helperMap.get(thirdMap).addTeamBanBO5();
                }
            } else {
                if(isPick) {
                    helperMap.get(thirdMap).addOppPickBO5();
                } else {
                    helperMap.get(thirdMap).addOppBanBO5();
                }
            }

            /* FOURTH MAP */
            choiceTeamA = bm.getBanMapType().getFourth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                if(isPick) {
                    helperMap.get(fourthMap).addTeamPickBO5();
                } else {
                    helperMap.get(fourthMap).addTeamBanBO5();
                }
            } else {
                if(isPick) {
                    helperMap.get(fourthMap).addOppPickBO5();
                } else {
                    helperMap.get(fourthMap).addOppBanBO5();
                }
            }

            /* FIFTH MAP */
            choiceTeamA = bm.getBanMapType().getFifth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                if(isPick) {
                    helperMap.get(fifthMap).addTeamPickBO5();
                } else {
                    helperMap.get(fifthMap).addTeamBanBO5();
                }
            } else {
                if(isPick) {
                    helperMap.get(fifthMap).addOppPickBO5();
                } else {
                    helperMap.get(fifthMap).addOppBanBO5();
                }
            }

            /* SIXTH MAP */
            choiceTeamA = bm.getBanMapType().getSixth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                if(isPick) {
                    helperMap.get(sixthMap).addTeamPickBO5();
                } else {
                    helperMap.get(sixthMap).addTeamBanBO5();
                }
            } else {
                if(isPick) {
                    helperMap.get(sixthMap).addOppPickBO5();
                } else {
                    helperMap.get(sixthMap).addOppBanBO5();
                }
            }

            /* SEVENTH MAP */
            choiceTeamA = bm.getBanMapType().getSeventh().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                if(isPick) {
                    helperMap.get(seventhMap).addTeamPickBO5();
                } else {
                    helperMap.get(seventhMap).addTeamBanBO5();
                }
            } else {
                if(isPick) {
                    helperMap.get(seventhMap).addOppPickBO5();
                } else {
                    helperMap.get(seventhMap).addOppBanBO5();
                }
            }

            /* EIGHT MAP */
            choiceTeamA = bm.getBanMapType().getEighth().charAt(0) == 'A';
            if(choiceTeamA == isTeamA) {
                if(isPick) {
                    helperMap.get(eighthMap).addTeamPickBO5();
                } else {
                    helperMap.get(eighthMap).addTeamBanBO5();
                }
            } else {
                if(isPick) {
                    helperMap.get(eighthMap).addOppPickBO5();
                } else {
                    helperMap.get(eighthMap).addOppBanBO5();
                }
            }

            /* DECIDER */
            helperMap.get(decider).addDeciderBO5();
        }
    }
}
