package konsola5.botaniaconfigurator;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.List;

import static konsola5.botaniaconfigurator.BotaniaConfigurator.LOGGER;

public class ConfigFile extends MidnightConfig {


    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment dandelifeon;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int dandelifeonManaCapacity = 50000;
    @Entry(category = "generating", min = 1, max = 1000) public static int dandelifeonDelay = 10;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int dandelifeonManaPerGeneration = 60;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int dandelifeonDecayTime = 72000;
    @Entry(category = "generating") public static boolean dandelifeonDecays = false;


    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment endoflame;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int endoflameManaCapacity = 300;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int endoflameManaGenerationRate = 3;
    @Entry(category = "generating", min = 0, max = 25) public static int endoflameRange = 3;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int endoflameFuelCap = 32000;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int endoflameDecayTime = 72000;
    @Entry(category = "generating") public static boolean endoflameDecays = false;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment entropinnyum;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int entropinnyumManaCapacity = 6500;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int entropinnyumManaGenerationRate = 6500;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int entropinnyumDupedTNTGenerationRate = 3;
    @Entry(category = "generating", min = 0, max = 100) public static int entropinnyumRange = 12;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int entropinnyumDecayTime = 72000;
    @Entry(category = "generating") public static boolean entropinnyumDecays = false;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment gourmaryllis;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int gourmaryllisManaCapacity = 10000;
    @Entry(category = "generating") public static List<String> gourmaryllisStreakMultipliers = List.of("0", "1", "1.3", "1.5", "1.6", "1.7", "1.75", "1.8");
    public static double[] gourmaryllisStreakList() {
        double[] list;
        try {
            list = ConfigFile.gourmaryllisStreakMultipliers.stream().mapToDouble(Double::parseDouble).toArray();
        } catch (NumberFormatException e) {
            list = new double[]{0, 1, 1.3, 1.5, 1.6, 1.7, 1.75, 1.8};
            LOGGER.error("Unable to parse the Gourmaryllis Streak Multipliers array, loading defaults!");
            e.printStackTrace();
        }
        return list;
    }
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int gourmaryllisMaxFoodValue = 12;
    @Entry(category = "generating", min = 0, max = 1000) public static int gourmaryllisFoodCooldownFactor = 10;
    @Entry(category = "generating", min = 0, max = 1000) public static int gourmaryllisFoodManaFactor = 70;
    @Entry(category = "generating", min = 0, max = 25) public static int gourmaryllisRange = 1;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int gourmaryllisDecayTime = 72000;
    @Entry(category = "generating") public static boolean gourmaryllisDecays = false;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment hydroangeas;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int hydroangeasManaCapacity = 150;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int hydroangeasBurnTime = 40;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int hydroangeasManaGenerationRate = 1;
    @Entry(category = "generating", min = 1, max = 1000) public static int hydroangeasDelay = 3;
    @Entry(category = "generating", min = 1, max = 1000) public static int hydroangeasDelayRain = 2;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int hydroangeasDecayTime = 72000;
    @Entry(category = "generating") public static boolean hydroangeasDecays = true;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment kekimurus;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int kekimurusManaCapacity = 9001;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int kekimurusManaGenerationRate = 1800;
    @Entry(category = "generating", min = 1, max = 1000) public static int kekimurusDelay = 80;
    @Entry(category = "generating", min = 1, max = 100) public static int kekimurusRange = 5;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int kekimurusDecayTime = 72000;
    @Entry(category = "generating") public static boolean kekimurusDecays = false;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment munchdew;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int munchdewManaCapacity = 10000;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int munchdewManaGenerationRate = 160;
    @Entry(category = "generating", min = 1, max = 100) public static int munchdewDelay = 4;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int munchdewCooldown = 1600;
    @Entry(category = "generating", min = 0, max = 100) public static int munchdewRangeXZ = 8;
    @Entry(category = "generating", min = 0, max = 100) public static int munchdewRangeY = 16;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int munchdewDecayTime = 72000;
    @Entry(category = "generating") public static boolean munchdewDecays = false;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment narslimmus;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int narslimmusManaCapacity = 19200;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int narslimmusBaseManaGenerationRate = 1200;
    @Entry(category = "generating", min = 0, max = 100) public static int narslimmusRange = 2;

    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int narslimmusDecayTime = 72000;
    @Entry(category = "generating") public static boolean narslimmusDecays = false;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment rafflowsia;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int rafflowsiaManaCapacity = 638554;
    @Entry(category = "generating") public static List<String> rafflowsiaStreakOutputs = List.of("2000", "2100", "2200", "2300", "3280", "4033", "4657", "5150", "6622", "7860", "10418", "12600", "14769", "16671", "19000", "25400", "33471", "40900", "47579", "53600", "59057", "64264", "69217", "74483", "79352", "83869", "88059", "92129", "96669", "100940", "105239", "112044", "118442", "124612", "130583", "136228", "141703", "178442", "213959", "247725", "279956", "313671", "345833", "377227", "437689", "495526", "553702", "638554");
    public static int[] rafflowsiaStreakList() {
        int[] list;
        try {
            list = ConfigFile.rafflowsiaStreakOutputs.stream().mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            list = new int[]{ 2000, 2100, 2200, 2300, 3280, 4033, 4657, 5150, 6622, 7860, 10418, 12600, 14769, 16671, 19000, 25400, 33471, 40900, 47579, 53600, 59057, 64264, 69217, 74483, 79352, 83869, 88059, 92129, 96669, 100940, 105239, 112044, 118442, 124612, 130583, 136228, 141703, 178442, 213959, 247725, 279956, 313671, 345833, 377227, 437689, 495526, 553702, 638554 };
            LOGGER.error("Unable to parse the Rafflowsia Streak Outputs array, loading defaults!");
            e.printStackTrace();
        }
        return list;
    }
    @Entry(category = "generating", min = 0, max = 25) public static int rafflowsiaRange = 1;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int rafflowsiaDecayTime = 72000;
    @Entry(category = "generating") public static boolean rafflowsiaDecays = false;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment rosaArcana;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int rosaArcanaManaCapacity = 6000;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int rosaArcanaManaGenerationRate = 50;
    @Entry(category = "generating", min = 0, max = 100) public static int rosaArcanaRange = 1;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int rosaArcanaDecayTime = 72000;
    @Entry(category = "generating") public static boolean rosaArcanaDecays = false;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment shulkMeNot;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int shulkMeNotManaCapacity = 75000;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int shulkMeNotManaGenerationRate = 75000;
    @Entry(category = "generating", min = 0, max = 100) public static double shulkMeNotRadius = 8;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int shulkMeNotDecayTime = 72000;
    @Entry(category = "generating") public static boolean shulkMeNotDecays = false;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment spectrolus;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int spectrolusManaCapacity = 16000;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int spectrolusManaPerWool = 1200;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int spectrolusManaPerSheep = 5000;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int spectrolusManaPerBabySheep = 1;
    @Entry(category = "generating") public static boolean spectrolusRandomStartingColor = false;
    @Entry(category = "generating", min = 0, max = 1000) public static int spectrolusRange = 1;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int spectrolusDecayTime = 72000;
    @Entry(category = "generating") public static boolean spectrolusDecays = false;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment thermalily;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int thermalilyManaCapacity = 750;
    @Entry(category = "generating", min = 1, max = Integer.MAX_VALUE) public static int thermalilyBurnTime = 600;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int thermalilyManaGenerationRate = 45;
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int thermalilyCooldownFactor = 400;
    @Entry(category = "generating") public static List<String> thermalilyCooldownRolls = List.of("10", "5", "3", "2", "1", "1", "3", "3", "3", "2", "1", "1", "1", "2", "2");
    @Entry(category = "generating", min = 0, max = Integer.MAX_VALUE) public static int thermalilyDecayTime = 72000;
    @Entry(category = "generating") public static boolean thermalilyDecays = false;

    public static int[] thermalilyCooldownList() {
        int[] list;
        try {
            list = ConfigFile.thermalilyCooldownRolls.stream().mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            list = new int[]{ 10, 5, 3, 2, 1, 1, 3, 3, 3, 2, 1, 1, 1, 2, 2 };
            LOGGER.error("Unable to parse the Gourmaryllis Streak Multipliers array, loading defaults!");
            e.printStackTrace();
        }
        if (list.length != 15) {
            list = new int[]{ 10, 5, 3, 2, 1, 1, 3, 3, 3, 2, 1, 1, 1, 2, 2 };
            LOGGER.error("The list is not 15 entries long, loading defaults!");
        }
        return list;
    }

    // Functional Flowers

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment agricarnation;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int agricarnationManaCapacity = 200;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int agricarnationManaCost = 5;
        @Entry(category = "misc", min = 0, max = 100) public static int agricarnationRange = 5;
        @Entry(category = "misc", min = 0, max = 100) public static int agricarnationRangeMini = 2;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment bellethorne;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int bellethorneManaCapacity = 1000;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int bellethorneManaCost = 24;
        @Entry(category = "misc", min = 0, max = 100) public static int bellethorneRange = 6;
        @Entry(category = "misc", min = 0, max = 100) public static int bellethorneRangeMini = 1;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment bergamute;
        @Entry(category = "misc", min = 0, max = 100) public static double bergamuteRadius = 4;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment bubbell;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int bubbellManaCapacity = 2000;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int bubbellManaCost = 4;
        @Entry(category = "misc", min = 0, max = 100) public static int bubbellRange = 12;
        @Entry(category = "misc", min = 0, max = 100) public static int bubbellRangeMini = 6;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment clayconia;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int clayconiaManaCapacity = 640;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int clayconiaManaCost = 80;

        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int clayconiaDelay = 5;
        @Entry(category = "misc", min = 0, max = 100) public static int clayconiaRangeXZ = 5;
        @Entry(category = "misc", min = 0, max = 100) public static int clayconiaRangeY = 3;
        @Entry(category = "misc", min = 0, max = 100) public static int clayconiaRangeXZMini = 2;
        @Entry(category = "misc", min = 0, max = 100) public static int clayconiaRangeYMini = 1;
        @Entry(category = "misc") public static boolean clayconiaBuff = false;
        @SuppressWarnings("unused")
        @Comment(category = "misc") public static Comment clayconiaNote;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment daffomill;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int daffomillManaCapacity = 100;
        @Entry(category = "misc", min = 0, max = 100) public static int daffomillWidth = 2;
        @Entry(category = "misc", min = 0, max = 100) public static int daffomillHeight = 3;
        @Entry(category = "misc", min = 0, max = 100) public static int daffomillLength = 16;
        @Entry(category = "misc", min = 0, max = 10) public static double daffomillStrength = 0.05;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment dreadthorne;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int dreadthorneManaCost = 30;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment exoflame;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int exoflameManaCapacity = 300;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int exoflameManaCost = 300;
        @Entry(category = "misc", min = 0, max = 100) public static int exoflameRangeXZ = 5;
        @Entry(category = "misc", min = 0, max = 100) public static int exoflameRangeY = 2;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment fallenKanade;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int fallenKanadeManaCapacity = 900;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int fallenKanadeManaCost = 120;
        @Entry(category = "misc", min = 0, max = 100) public static int fallenKanadeRange = 2;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment heiseiDream;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int heiseiDreamManaCapacity = 1000;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int heiseiDreamManaCost = 100;
        @Entry(category = "misc", min = 0, max = 100) public static int heiseiDreamRange = 5;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment hopperhock;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int hopperhockManaCapacity = 20;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int hopperhockManaCostPerPull = 1;
        @Entry(category = "misc", min = 0, max = 100) public static int hopperhockRange = 6;
        @Entry(category = "misc", min = 0, max = 100) public static int hopperhockRangeMana = 10;
        @Entry(category = "misc", min = 0, max = 100) public static int hopperhockRangeMini = 1;
        @Entry(category = "misc", min = 0, max = 100) public static int hopperhockRangeManaMini = 2;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment hyacidus;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int hyacidusManaCapacity = 180;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int hyacidusManaCost = 20;
        @Entry(category = "misc", min = 0, max = 100) public static int hyacidusRange = 6;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment jadedAmaranthus;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int jadedAmaranthusManaCapacity = 100;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int jadedAmaranthusManaCost = 100;
        @Entry(category = "misc", min = 0, max = 100) public static int jadedAmaranthusRange = 4;
        @Entry(category = "misc", min = 1, max = 100) public static int jadedAmaranthusDelay = 30;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment jiyuulia;
        @Entry(category = "misc", min = 0, max = 100) public static int jiyuuliaRange = 8;
        @Entry(category = "misc", min = 0, max = 100) public static int jiyuuliaRangeMini = 3;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment labellia;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int labelliaManaCapacity = 6000;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int labelliaManaCost = 500;
        @Entry(category = "misc", min = 0, max = 100) public static int labelliaPickupRange = 0;
        @Entry(category = "misc", min = 0, max = 100) public static int labelliaRenameRange = 2;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment loonium;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int looniumManaCapacity = 35000;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int looniumManaCost = 35000;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment marimorphosis;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int marimorphosisManaCapacity = 1000;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int marimorphosisManaCost = 12;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int marimorphosisDelay = 2;
        @Entry(category = "misc", min = 0, max = 100) public static int marimorphosisRangeXZ = 8;
        @Entry(category = "misc", min = 0, max = 100) public static int marimorphosisRangeY = 5;
        @Entry(category = "misc", min = 0, max = 100) public static int marimorphosisRangeXZMini = 2;
        @Entry(category = "misc", min = 0, max = 100) public static int marimorphosisRangeYMini = 1;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment medumone;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int medumoneManaCapacity = 4000;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int medumoneManaCost = 1;
        @Entry(category = "misc", min = 0, max = 100) public static int medumoneRange = 6;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment orechid;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int orechidManaCapacity = 17500;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int orechidManaCost = 17500;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int orechidDelay = 100;
        @Entry(category = "misc", min = 0, max = 100) public static int orechidRangeXZ = 5;
        @Entry(category = "misc", min = 0, max = 100) public static int orechidRangeY = 3;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment orechidIgnem;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int orechidIgnemManaCapacity = 20000;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int orechidIgnemManaCost = 20000;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int orechidIgnemDelay = 100;
        @Entry(category = "misc", min = 0, max = 100) public static int orechidIgnemRangeXZ = 5;
        @Entry(category = "misc", min = 0, max = 100) public static int orechidIgnemRangeY = 3;
        @Entry(category = "misc") public static boolean orechidIgnemOnlyWorksInNether = true;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment pollidisiac;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int pollidisiacManaCapacity = 120;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int pollidisiacManaCost = 12;
        @Entry(category = "misc", min = 0, max = 100) public static int pollidisiacRange = 6;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment rannucarpus;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int rannucarpusManaCapacity = 120;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int rannucarpusManaCost = 12;
        @Entry(category = "misc", min = 0, max = 100) public static int rannucarpusPickupRangeXZ = 2;
        @Entry(category = "misc", min = 0, max = 100) public static int rannucarpusPickupRangeY = 3;
        @Entry(category = "misc", min = 0, max = 100) public static int rannucarpusPlacementRangeXZ = 6;
        @Entry(category = "misc", min = 0, max = 100) public static int rannucarpusPlacementRangeXZMana = 8;
        @Entry(category = "misc", min = 0, max = 100) public static int rannucarpusPlacementRangeY = 6;
        @Entry(category = "misc", min = 0, max = 100) public static int rannucarpusPlacementRangeXZMini = 3;
        @Entry(category = "misc", min = 0, max = 100) public static int rannucarpusPlacementRangeXZManaMini = 2;
        @Entry(category = "misc", min = 0, max = 100) public static int rannucarpusPlacementRangeYMini = 2;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int rannucarpusDelay = 10;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment solegnolia;
        @Entry(category = "misc", min = 0, max = 100) public static double solegnoliaRadius = 5;
        @Entry(category = "misc", min = 0, max = 100) public static double solegnoliaRadiusMini = 1;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment spectrantheum;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int spectrantheumManaCapacity = 5000;
        @Entry(category = "misc", min = 0, max = 100) public static double spectrantheumManaCostMultiplier = 1;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int spectrantheumPickupRange = 2;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment tangleberrie;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int tangleberrieManaCapacity = 20;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int tangleberrieManaCost = 1;
        @Entry(category = "misc", min = 0, max = 100) public static int tangleberrieRange = 7;
        @Entry(category = "misc", min = 0, max = 100) public static int tangleberrieMaxDistance = 6;
        @Entry(category = "misc", min = 0, max = 100) public static int tangleberrieRangeMini = 3;
        @Entry(category = "misc", min = 0, max = 100) public static int tangleberrieMaxDistanceMini = 2;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment tigerseye;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int tigerseyeManaCapacity = 1000;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int tigerseyeManaCost = 70;
        @Entry(category = "misc", min = 0, max = 100) public static int tigerseyeRangeXZ = 10;
        @Entry(category = "misc", min = 0, max = 100) public static int tigerseyeRangeY = 4;

    @SuppressWarnings("unused")
    @Comment(category = "misc", centered = true) public static Comment vinculotus;
        @Entry(category = "misc", min = 1, max = Integer.MAX_VALUE) public static int vinculotusManaCapacity = 500;
        @Entry(category = "misc", min = 0, max = Integer.MAX_VALUE) public static int vinculotusManaCost = 50;
        @Entry(category = "misc", min = 0, max = 100) public static double vinculotusRadius = 64;

    // Other tweaks
    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment manaFluxfield;
        @Entry(category = "functional", min = 0, max = Integer.MAX_VALUE) public static double manaFluxfieldRatio = 3;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int manaFluxfieldCapacity = 3840;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment manaPools;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int dilutedManaPoolCapacity = 10000;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int manaPoolCapacity = 1000000;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int fabulousManaPoolCapacity = 1000000;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int guiltyPoolCapacity = 1000000;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int manaCartCapacity = 1000000;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int manaPumpTransferRate = 10000;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment manaRelatedItems;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int manaTabletCapacity = 500000;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int manaBandCapacity = 500000;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int manaBandGreaterCapacity = 2000000;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int auraBandIntervalMultiplier = 5;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int auraBandManaGenerationRate = 5;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment elvenGateway;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int elvenGatewayOpeningCost = 200000;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int elvenGatewayTradeCost = 500;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int elvenGatewayMinimumPylons = 2;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment runicAltar;
        @Entry(category = "functional") public static boolean runicAltarLosesMana = false;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int runicAltarTicksBeforeLoss = 1000;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int runicAltarManaLoss = 1;
        @Entry(category = "functional", min = 1, max = Integer.MAX_VALUE) public static int runicAltarManaLossInterval = 1;
}
