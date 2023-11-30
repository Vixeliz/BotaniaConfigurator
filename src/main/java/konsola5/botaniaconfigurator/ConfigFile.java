package konsola5.botaniaconfigurator;

import eu.midnightdust.lib.config.MidnightConfig;

public class ConfigFile extends MidnightConfig {

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment dandelifeon;
    @Entry(category = "generating", min = 0, max = 100000) public static int dandelifeonManaCapacity = 50000;
    @Entry(category = "generating", min = 0, max = 1000) public static int dandelifeonDelay = 10;
    @Entry(category = "generating", min = 0, max = 100000) public static int dandelifeonManaPerGeneration = 60;


    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment endoflame;
    @Entry(category = "generating", min = 0, max = 100000) public static int endoflameManaCapacity = 300;
    @Entry(category = "generating", min = 0, max = 100000) public static int endoflameManaGenerationRate = 3;
    @Entry(category = "generating", min = 0, max = 100) public static int endoflameRange = 3;
    @Entry(category = "generating", min = 0, max = 100000) public static int endoflameFuelCap = 32000;

    @SuppressWarnings("unused")
    @Comment(category = "generating", centered = true) public static Comment entropinnyum;
    @Entry(category = "generating", min = 0, max = 100000) public static int entropinnyumManaCapacity = 6500;
    @Entry(category = "generating", min = 0, max = 100000) public static int entropinnyumManaGenerationRate = 6500;
    @Entry(category = "generating", min = 0, max = 100000) public static int entropinnyumDupedTNTGenerationRate = 3;
    @Entry(category = "generating", min = 0, max = 100) public static int entropinnyumRange = 12;

    // Functional Flowers

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment agricarnation;
        @Entry(category = "functional", min = 0, max = 100000) public static int agricarnationManaCapacity = 200;
        @Entry(category = "functional", min = 0, max = 100000) public static int agricarnationManaCost = 5;
        @Entry(category = "functional", min = 0, max = 100) public static int agricarnationRange = 5;
        @Entry(category = "functional", min = 0, max = 100) public static int agricarnationRangeMini = 2;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment bellethorne;
        @Entry(category = "functional", min = 0, max = 100000) public static int bellethorneManaCapacity = 1000;
        @Entry(category = "functional", min = 0, max = 100000) public static int bellethorneManaCost = 24;
        @Entry(category = "functional", min = 0, max = 100) public static int bellethorneRange = 6;
        @Entry(category = "functional", min = 0, max = 100) public static int bellethorneRangeMini = 1;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment bergamute;
        @Entry(category = "functional", min = 0, max = 100) public static double bergamuteRadius = 4;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment bubbell;
        @Entry(category = "functional", min = 0, max = 100000) public static int bubbellManaCapacity = 2000;
        @Entry(category = "functional", min = 0, max = 100000) public static int bubbellManaCost = 4;
        @Entry(category = "functional", min = 0, max = 100) public static int bubbellRange = 12;
        @Entry(category = "functional", min = 0, max = 100) public static int bubbellRangeMini = 6;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment clayconia;
        @Entry(category = "functional", min = 0, max = 100000) public static int clayconiaManaCapacity = 640;
        @Entry(category = "functional", min = 0, max = 100000) public static int clayconiaManaCost = 80;
        @Entry(category = "functional", min = 0, max = 100) public static int clayconiaRangeXZ = 5;
        @Entry(category = "functional", min = 0, max = 100) public static int clayconiaRangeY = 3;
        @Entry(category = "functional", min = 0, max = 100) public static int clayconiaRangeXZMini = 2;
        @Entry(category = "functional", min = 0, max = 100) public static int clayconiaRangeYMini = 1;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment daffomill;
        @Entry(category = "functional", min = 0, max = 100000) public static int daffomillManaCapacity = 100;
        @Entry(category = "functional", min = 0, max = 100) public static int daffomillWidth = 2;
        @Entry(category = "functional", min = 0, max = 100) public static int daffomillHeight = 3;
        @Entry(category = "functional", min = 0, max = 100) public static int daffomillLength = 16;
        @Entry(category = "functional", min = 0, max = 10) public static double daffomillStrength = 0.05;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment dreadthorne;
        @Entry(category = "functional", min = 0, max = 100000) public static int dreadthorneManaCost = 30;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment exoflame;
        @Entry(category = "functional", min = 0, max = 100000) public static int exoflameManaCapacity = 300;
        @Entry(category = "functional", min = 0, max = 100000) public static int exoflameManaCost = 300;
        @Entry(category = "functional", min = 0, max = 100) public static int exoflameRangeXZ = 5;
        @Entry(category = "functional", min = 0, max = 100) public static int exoflameRangeY = 2;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment fallenKanade;
        @Entry(category = "functional", min = 0, max = 100000) public static int fallenKanadeManaCapacity = 900;
        @Entry(category = "functional", min = 0, max = 100000) public static int fallenKanadeManaCost = 120;
        @Entry(category = "functional", min = 0, max = 100) public static int fallenKanadeRange = 2;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment heiseiDream;
        @Entry(category = "functional", min = 0, max = 100000) public static int heiseiDreamManaCapacity = 1000;
        @Entry(category = "functional", min = 0, max = 100000) public static int heiseiDreamManaCost = 100;
        @Entry(category = "functional", min = 0, max = 100) public static int heiseiDreamRange = 5;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment hopperhock;
        @Entry(category = "functional", min = 0, max = 100000) public static int hopperhockManaCostPerPull = 1;
        @Entry(category = "functional", min = 0, max = 100000) public static int hopperhockManaCapacity = 20;
        @Entry(category = "functional", min = 0, max = 100) public static int hopperhockRange = 6;
        @Entry(category = "functional", min = 0, max = 100) public static int hopperhockRangeMana = 10;
        @Entry(category = "functional", min = 0, max = 100) public static int hopperhockRangeMini = 1;
        @Entry(category = "functional", min = 0, max = 100) public static int hopperhockRangeManaMini = 2;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment hyacidus;
        @Entry(category = "functional", min = 0, max = 100000) public static int hyacidusManaCapacity = 180;
        @Entry(category = "functional", min = 0, max = 100000) public static int hyacidusManaCost = 20;
        @Entry(category = "functional", min = 0, max = 100) public static int hyacidusRange = 6;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment jadedAmaranthus;
        @Entry(category = "functional", min = 0, max = 100000) public static int jadedAmaranthusManaCapacity = 100;
        @Entry(category = "functional", min = 0, max = 100000) public static int jadedAmaranthusManaCost = 100;
        @Entry(category = "functional", min = 0, max = 100) public static int jadedAmaranthusRange = 4;
        @Entry(category = "functional", min = 0, max = 100) public static int jadedAmaranthusDelay = 30;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment jiyuulia;
        @Entry(category = "functional", min = 0, max = 100) public static int jiyuuliaRange = 8;
        @Entry(category = "functional", min = 0, max = 100) public static int jiyuuliaRangeMini = 3;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment labellia;
        @Entry(category = "functional", min = 0, max = 100000) public static int labelliaManaCapacity = 6000;
        @Entry(category = "functional", min = 0, max = 100000) public static int labelliaManaCost = 500;
        @Entry(category = "functional", min = 0, max = 100) public static int labelliaPickupRange = 0;
        @Entry(category = "functional", min = 0, max = 100) public static int labelliaRenameRange = 2;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment loonium;
        @Entry(category = "functional", min = 0, max = 100000) public static int looniumManaCapacity = 35000;
        @Entry(category = "functional", min = 0, max = 100000) public static int looniumManaCost = 35000;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment marimorphosis;
        @Entry(category = "functional", min = 0, max = 100000) public static int marimorphosisManaCapacity = 1000;
        @Entry(category = "functional", min = 0, max = 100000) public static int marimorphosisManaCost = 12;
        @Entry(category = "functional", min = 0, max = 100000) public static int marimorphosisDelay = 2;
        @Entry(category = "functional", min = 0, max = 100) public static int marimorphosisRangeXZ = 8;
        @Entry(category = "functional", min = 0, max = 100) public static int marimorphosisRangeY = 5;
        @Entry(category = "functional", min = 0, max = 100) public static int marimorphosisRangeXZMini = 2;
        @Entry(category = "functional", min = 0, max = 100) public static int marimorphosisRangeYMini = 1;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment medumone;
        @Entry(category = "functional", min = 0, max = 100000) public static int medumoneManaCapacity = 4000;
        @Entry(category = "functional", min = 0, max = 100000) public static int medumoneManaCost = 1;
        @Entry(category = "functional", min = 0, max = 100) public static int medumoneRange = 6;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment orechid;
        @Entry(category = "functional", min = 0, max = 100000) public static int orechidManaCapacity = 17500;
        @Entry(category = "functional", min = 0, max = 100000) public static int orechidManaCost = 17500;
        @Entry(category = "functional", min = 0, max = 100000) public static int orechidDelay = 100;
        @Entry(category = "functional", min = 0, max = 100) public static int orechidRangeXZ = 5;
        @Entry(category = "functional", min = 0, max = 100) public static int orechidRangeY = 3;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment orechidIgnem;
        @Entry(category = "functional", min = 0, max = 100000) public static int orechidIgnemManaCapacity = 20000;
        @Entry(category = "functional", min = 0, max = 100000) public static int orechidIgnemManaCost = 20000;
        @Entry(category = "functional", min = 0, max = 100000) public static int orechidIgnemDelay = 100;
        @Entry(category = "functional", min = 0, max = 100) public static int orechidIgnemRangeXZ = 5;
        @Entry(category = "functional", min = 0, max = 100) public static int orechidIgnemRangeY = 3;
        @Entry(category = "functional") public static boolean orechidIgnemOnlyWorksInNether = true;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment pollidisiac;
        @Entry(category = "functional", min = 0, max = 100000) public static int pollidisiacManaCapacity = 120;
        @Entry(category = "functional", min = 0, max = 100000) public static int pollidisiacManaCost = 12;
        @Entry(category = "functional", min = 0, max = 100) public static int pollidisiacRange = 6;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment rannucarpus;
        @Entry(category = "functional", min = 0, max = 100000) public static int rannucarpusManaCapacity = 120;
        @Entry(category = "functional", min = 0, max = 100000) public static int rannucarpusManaCost = 12;
        @Entry(category = "functional", min = 0, max = 100) public static int rannucarpusPickupRangeXZ = 2;
        @Entry(category = "functional", min = 0, max = 100) public static int rannucarpusPickupRangeY = 3;
        @Entry(category = "functional", min = 0, max = 100) public static int rannucarpusPlacementRangeXZ = 6;
        @Entry(category = "functional", min = 0, max = 100) public static int rannucarpusPlacementRangeXZMana = 8;
        @Entry(category = "functional", min = 0, max = 100) public static int rannucarpusPlacementRangeY = 6;
        @Entry(category = "functional", min = 0, max = 100) public static int rannucarpusPlacementRangeXZMini = 3;
        @Entry(category = "functional", min = 0, max = 100) public static int rannucarpusPlacementRangeXZManaMini = 2;
        @Entry(category = "functional", min = 0, max = 100) public static int rannucarpusPlacementRangeYMini = 2;
        @Entry(category = "functional", min = 0, max = 100000) public static int rannucarpusDelay = 10;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment solegnolia;
        @Entry(category = "functional", min = 0, max = 100) public static double solegnoliaRadius = 5;
        @Entry(category = "functional", min = 0, max = 100) public static double solegnoliaRadiusMini = 1;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment spectrantheum;
        @Entry(category = "functional", min = 0, max = 100000) public static int spectrantheumManaCapacity = 5000;
        @Entry(category = "functional", min = 0, max = 100) public static double spectrantheumManaCostMultiplier = 1;
        @Entry(category = "functional", min = 0, max = 100000) public static int spectrantheumPickupRange = 2;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment tangleberrie;
        @Entry(category = "functional", min = 0, max = 100000) public static int tangleberrieManaCapacity = 20;
        @Entry(category = "functional", min = 0, max = 100000) public static int tangleberrieManaCost = 1;
        @Entry(category = "functional", min = 0, max = 100) public static int tangleberrieRange = 7;
        @Entry(category = "functional", min = 0, max = 100) public static int tangleberrieMaxDistance = 6;
        @Entry(category = "functional", min = 0, max = 100) public static int tangleberrieRangeMini = 3;
        @Entry(category = "functional", min = 0, max = 100) public static int tangleberrieMaxDistanceMini = 2;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment tigerseye;
        @Entry(category = "functional", min = 0, max = 100000) public static int tigerseyeManaCapacity = 1000;
        @Entry(category = "functional", min = 0, max = 100000) public static int tigerseyeManaCost = 70;
        @Entry(category = "functional", min = 0, max = 100) public static int tigerseyeRangeXZ = 10;
        @Entry(category = "functional", min = 0, max = 100) public static int tigerseyeRangeY = 4;

    @SuppressWarnings("unused")
    @Comment(category = "functional", centered = true) public static Comment vinculotus;
        @Entry(category = "functional", min = 0, max = 100000) public static int vinculotusManaCapacity = 500;
        @Entry(category = "functional", min = 0, max = 100000) public static int vinculotusManaCost = 50;
        @Entry(category = "functional", min = 0, max = 100) public static double vinculotusRadius = 64;

    
//    @Comment(category = "text") public static Comment text1;                       // Comments are rendered like an option without a button and are excluded from the config file
//    @Comment(category = "text", centered = true) public static Comment text2;      // Centered comments are the same as normal ones - just centered!
//    @Comment(category = "text") public static Comment spacer1;                     // Comments containing the word "spacer" will just appear as a blank line
//    @Entry(category = "text") public static boolean showInfo = true;               // Example for a boolean option
//    @Entry(category = "text") public static String name = "Hello World!";          // Example for a string option, which is in a category!
//    @Entry(category = "text") public static TestEnum testEnum = TestEnum.FABRIC;   // Example for an enum option
//    public enum TestEnum {                               // Enums allow the user to cycle through predefined options
//        QUILT, FABRIC, FORGE
//    }
//    @Entry(category = "numbers") public static int fabric = 16777215;                 // Example for an int option
//    @Entry(category = "numbers") public static double world = 1.4D;                   // Example for a double option
//    @Entry(category = "numbers", min=69,max=420) public static int hello = 420;   // - The entered number has to be larger than 69 and smaller than 420
//    @Entry(category = "text", width = 7, min = 7, isColor = true, name = "I am a color!") public static String titleColor = "#ffffff"; // The isColor property adds a preview box for a hexadecimal color
//    @Entry(category = "text", name = "I am an array list!") public static List<String> arrayList = Lists.newArrayList("String1", "String2"); // Array String Lists are also supported
//    @Entry(category = "sliders", name = "I am an int slider.",isSlider = true, min = 0, max = 100) public static int intSlider = 35; // Int fields can also be displayed as a Slider
//    @Entry(category = "sliders", name = "I am a float slider!", isSlider = true, min = 0f, max = 1f, precision = 1000) public static float floatSlider = 0.24f; // And so can floats! Precision defines the amount of decimal places
//    // The name field can be used to specify a custom translation string or plain text
//
//    public static int imposter = 16777215; // - Entries without an @Entry or @Comment annotation are ignored

}
