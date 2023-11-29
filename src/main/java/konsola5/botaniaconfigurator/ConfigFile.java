package konsola5.botaniaconfigurator;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

import static konsola5.botaniaconfigurator.BotaniaConfigurator.MOD_ID;

public class ConfigFile {
    public static ConfigClassHandler<ConfigFile> HANDLER = ConfigClassHandler.createBuilder(ConfigFile.class)
            .id(new ResourceLocation(MOD_ID, "botaniaconfigurator"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("botania_configurator.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                    .setJson5(true)
                    .build())
            .build();

//    public void validate() {
//        if (this.client != null) this.client.validate();
//        this.common.validate();
//    }

    @SuppressWarnings("FieldMayBeFinal")
    @SerialEntry
    private Common common = new Common();

    public Common getCommon() {
        return common;
    }

    @SuppressWarnings("FieldMayBeFinal")
    @SerialEntry
    private Generators generators = new Generators();

    public Generators getGenerators() {
        return generators;
    }

    @SuppressWarnings("FieldMayBeFinal")
    @SerialEntry
    private Functional functional = new Functional();

    public Functional getFunctional() {
        return functional;
    }

    public static class Common {
        @SerialEntry
        public boolean myCoolBoolean = true;

        @SerialEntry
        public int myCoolInteger = 5;

        @SerialEntry(comment = "This string is amazing")
        public String myCoolString = "How amazing!";
    }

    public static class Generators {

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Dandelifeon dandelifeon = new Dandelifeon();

        public Dandelifeon getDandelifeon() {
            return dandelifeon;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Endoflame endoflame = new Endoflame();

        public Endoflame getEndoflame() {
            return endoflame;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Entropinnyum entropinnyum = new Entropinnyum();

        public Entropinnyum getEntropinnyum() {
            return entropinnyum;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Hydroangeas hydroangeas = new Hydroangeas();

        public Hydroangeas getHydroangeas() {
            return hydroangeas;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Kekimurus kekimurus = new Kekimurus();

        public Kekimurus getKekimurus() {
            return kekimurus;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Munchdew munchdew = new Munchdew();

        public Munchdew getMunchdew() {
            return munchdew;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Narslimmus narslimmus = new Narslimmus();

        public Narslimmus getNarslimmus() {
            return narslimmus;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Rafflowsia rafflowsia = new Rafflowsia();

        public Rafflowsia getRafflowsia() {
            return rafflowsia;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private RosaArcana rosaArcana = new RosaArcana();

        public RosaArcana getRosaArcana() {
            return rosaArcana;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private ShulkMeNot shulkMeNot = new ShulkMeNot();

        public ShulkMeNot getShulkMeNot() {
            return shulkMeNot;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Spectrolus spectrolus = new Spectrolus();

        public Spectrolus getSpectrolus() {
            return spectrolus;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Thermalily thermalily = new Thermalily();

        public Thermalily getThermalily() {
            return thermalily;
        }

        public static class Dandelifeon {

        }

        public static class Endoflame {

        }

        public static class Entropinnyum {

        }

        public static class Hydroangeas {

        }

        public static class Kekimurus {

        }

        public static class Munchdew {

        }

        public static class Narslimmus {

        }

        public static class Rafflowsia {

        }

        public static class RosaArcana {

        }

        public static class ShulkMeNot {

        }

        public static class Spectrolus {

        }

        public static class Thermalily {

        }
    }

    public static class Functional {
        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Agricarnation agricarnation = new Agricarnation();

        public Agricarnation getAgricarnation() {
            return agricarnation;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Bellethorne bellethorne = new Bellethorne();

        public Bellethorne getBellethorne() {
            return bellethorne;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Bergamute bergamute = new Bergamute();

        public Bergamute getBergamute() {
            return bergamute;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Bubbell bubbell = new Bubbell();

        public Bubbell getBubbell() {
            return bubbell;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Clayconia clayconia = new Clayconia();

        public Clayconia getClayconia() {
            return clayconia;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Daffomill daffomill = new Daffomill();

        public Daffomill getDaffomill() {
            return daffomill;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Dreadthorne dreadthorne = new Dreadthorne();

        public Dreadthorne getDreadthorne() {
            return dreadthorne;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Exoflame exoflame = new Exoflame();

        public Exoflame getExoflame() {
            return exoflame;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private FallenKanade fallenKanade = new FallenKanade();

        public FallenKanade getFallenKanade() {
            return fallenKanade;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private HeiseiDream heiseiDream = new HeiseiDream();

        public HeiseiDream getHeiseiDream() {
            return heiseiDream;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Hopperhock hopperhock = new Hopperhock();

        public Hopperhock getHopperhock() {
            return hopperhock;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Hyacidus hyacidus = new Hyacidus();

        public Hyacidus getHyacidus() {
            return hyacidus;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private JadedAmaranthus jadedAmaranthus = new JadedAmaranthus();

        public JadedAmaranthus getJadedAmaranthus() {
            return jadedAmaranthus;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Jiyuulia jiyuulia = new Jiyuulia();

        public Jiyuulia getJiyuulia() {
            return jiyuulia;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Labellia labellia = new Labellia();

        public Labellia getLabellia() {
            return labellia;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Loonium loonium = new Loonium();

        public Loonium getLoonium() {
            return loonium;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Marimorphosis marimorphosis = new Marimorphosis();

        public Marimorphosis getMarimorphosis() {
            return marimorphosis;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Medumone medumone = new Medumone();

        public Medumone getMedumone() {
            return medumone;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Orechid orechid = new Orechid();

        public Orechid getOrechid() {
            return orechid;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private OrechidIgnem orechidIgnem = new OrechidIgnem();

        public OrechidIgnem getOrechidIgnem() {
            return orechidIgnem;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Pollidisiac pollidisiac = new Pollidisiac();

        public Pollidisiac getPollidisiac() {
            return pollidisiac;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Rannucarpus rannucarpus = new Rannucarpus();

        public Rannucarpus getRannucarpus() {
            return rannucarpus;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Solegnolia solegnolia = new Solegnolia();

        public Solegnolia getSolegnolia() {
            return solegnolia;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Spectrantheum spectrantheum = new Spectrantheum();

        public Spectrantheum getSpectrantheum() {
            return spectrantheum;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Tangleberrie tangleberrie = new Tangleberrie();

        public Tangleberrie getTangleberrie() {
            return tangleberrie;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Tigerseye tigerseye = new Tigerseye();

        public Tigerseye getTigerseye() {
            return tigerseye;
        }

        @SuppressWarnings("FieldMayBeFinal")
        @SerialEntry
        private Vinculotus vinculotus = new Vinculotus();

        public Vinculotus getVinculotus() {
            return vinculotus;
        }

        public static class Agricarnation {
            @SerialEntry
            public int manaCapacity = 200;
            @SerialEntry
            public int manaCost = 5;
            @SerialEntry
            public int range = 5;
            @SerialEntry
            public int rangeMini = 2;

        }

        public static class Bellethorne {
            @SerialEntry
            public int manaCapacity = 1000;
            @SerialEntry
            public int manaCost = 24;
            @SerialEntry
            public int range = 6;
            @SerialEntry
            public int rangeMini = 1;
        }

        public static class Bergamute {
            @SerialEntry
            public double radius = 4;
        }

        public static class Bubbell {
            @SerialEntry
            public int manaCapacity = 2000;
            @SerialEntry
            public int manaCost = 4;
            @SerialEntry
            public int range = 12;
            @SerialEntry
            public int rangeMini = 6;
        }

        public static class Clayconia {
            @SerialEntry
            public int manaCapacity = 640;
            @SerialEntry
            public int manaCost = 80;
            @SerialEntry
            public int rangeXZ = 5;
            @SerialEntry
            public int rangeY = 3;
            @SerialEntry
            public int rangeXZMini = 2;
            @SerialEntry
            public int rangeYMini = 1;
        }

        public static class Daffomill {
            @SerialEntry
            public int manaCapacity = 100;
            @SerialEntry
            public int width = 2;
            @SerialEntry
            public int height = 3;
            @SerialEntry
            public int length = 16;
            @SerialEntry
            public double strength = 0.05;
        }

        public static class Dreadthorne {
            @SerialEntry
            public int manaCost = 30;
        }

        public static class Exoflame {
            @SerialEntry
            public int manaCapacity = 300;
            @SerialEntry
            public int manaCost = 300;
            @SerialEntry
            public int rangeXZ = 5;
            @SerialEntry
            public int rangeY = 2;
        }

        public static class FallenKanade {
            @SerialEntry
            public int manaCapacity = 900;
            @SerialEntry
            public int manaCost = 120;
            @SerialEntry
            public int range = 2;
        }

        public static class HeiseiDream {
            @SerialEntry
            public int manaCapacity = 1000;
            @SerialEntry
            public int manaCost = 100;
            @SerialEntry
            public int range = 5;
        }

        public static class Hopperhock {
            @SerialEntry
            public int manaCostPerPull = 1;
            @SerialEntry
            public int manaCapacity = 20;
            @SerialEntry
            public int range = 6;
            @SerialEntry
            public int rangeMana = 10;
            @SerialEntry
            public int rangeMini = 1;
            @SerialEntry
            public int rangeManaMini = 2;
        }

        public static class Hyacidus {
            @SerialEntry
            public int manaCapacity = 180;
            @SerialEntry
            public int manaCost = 20;
            @SerialEntry
            public int range = 6;
        }

        public static class JadedAmaranthus {
            @SerialEntry
            public int manaCapacity = 100;
            @SerialEntry
            public int manaCost = 100;
            @SerialEntry
            public int range = 4;
            @SerialEntry
            public int delay = 30;
        }

        public static class Jiyuulia {
            @SerialEntry
            public int range = 8;
            @SerialEntry
            public int rangeMini = 3;
        }

        public static class Labellia {
            @SerialEntry
            public int manaCapacity = 6000;
            @SerialEntry
            public int manaCost = 500;
            @SerialEntry
            public int pickupRange = 0;
            @SerialEntry
            public int renameRange = 2;
        }

        public static class Loonium {
            @SerialEntry
            public int manaCapacity = 35000;
            @SerialEntry
            public int manaCost = 35000;
        }

        public static class Marimorphosis {
            @SerialEntry
            public int manaCapacity = 1000;
            @SerialEntry
            public int manaCost = 12;
            @SerialEntry
            public int delay = 2;
            @SerialEntry
            public int rangeXZ = 8;
            @SerialEntry
            public int rangeY = 5;
            @SerialEntry
            public int rangeXZMini = 2;
            @SerialEntry
            public int rangeYMini = 1;
        }

        public static class Medumone {
            @SerialEntry
            public int manaCapacity = 4000;
            @SerialEntry
            public int manaCost = 1;
            @SerialEntry
            public int range = 6;
        }

        public static class Orechid {
            @SerialEntry
            public int manaCapacity = 17500;
            @SerialEntry
            public int manaCost = 17500;
            @SerialEntry
            public int delay = 100;
            @SerialEntry
            public int rangeXZ = 5;
            @SerialEntry
            public int rangeY = 3;

        }

        public static class OrechidIgnem {
            @SerialEntry
            public int manaCapacity = 20000;
            @SerialEntry
            public int manaCost = 20000;
            @SerialEntry
            public int delay = 100;
            @SerialEntry
            public int rangeXZ = 5;
            @SerialEntry
            public int rangeY = 3;
            @SerialEntry
            public boolean onlyWorksInNether = true;
        }

        public static class Pollidisiac {
            @SerialEntry
            public int manaCapacity = 120;
            @SerialEntry
            public int manaCost = 12;
            @SerialEntry
            public int range = 6;
        }

        public static class Rannucarpus {
            @SerialEntry
            public int manaCapacity = 120;
            @SerialEntry
            public int manaCost = 12;
            @SerialEntry
            public int pickupRangeXZ = 2;
            @SerialEntry
            public int pickupRangeY = 3;
            @SerialEntry
            public int placementRangeXZ = 6;
            @SerialEntry
            public int placementRangeXZMana = 8;
            @SerialEntry
            public int placementRangeY = 6;
            @SerialEntry
            public int placementRangeXZMini = 3;
            @SerialEntry
            public int placementRangeXZManaMini = 2;
            @SerialEntry
            public int placementRangeYMini = 2;
            @SerialEntry
            public int delay = 10;
        }

        public static class Solegnolia {
            @SerialEntry
            public double radius = 5;
            @SerialEntry
            public double radiusMini = 1;
        }

        public static class Spectrantheum {
            @SerialEntry
            public int manaCapacity = 5000;
            @SerialEntry
            public double manaCostMultiplier = 1;
            @SerialEntry
            public int pickupRange = 2;
        }

        public static class Tangleberrie {
            @SerialEntry
            public int manaCapacity = 20;

            @SerialEntry
            public int manaCost = 1;

            @SerialEntry
            public int range = 7;

            @SerialEntry
            public int maxDistance = 6;

            @SerialEntry
            public int rangeMini = 3;

            @SerialEntry
            public int maxDistanceMini = 2;
        }

        public static class Tigerseye {
            @SerialEntry
            public int manaCapacity = 1000;
            @SerialEntry
            public int manaCost = 70;
            @SerialEntry
            public int rangeXZ = 10;
            @SerialEntry
            public int rangeY = 4;
        }

        public static class Vinculotus {
            @SerialEntry
            public int manaCapacity = 500;
            @SerialEntry
            public int manaCost = 50;
            @SerialEntry
            public double radius = 64;
        }
    }
}
