package com.cbamz.lcsdashboard.domain.game;

public enum Champion {
    AATROX("Aatrox"),
    AHRI("Ahri"),
    AKALI("Akali"),
    ALISTAR("Alistar"),
    AKSHAN("Akshan"),
    AMUMU("Amumu"),
    ANIVIA("Anivia"),
    ANNIE("Annie"),
    APHELIOS("Aphelios"),
    ASHE("Ashe"),
    AURELIONSOL("Aurelion Sol"),
    AZIR("Azir"),
    BARD("Bard"),
    BLITZCRANK("Blitzcrank"),
    BRAND("Brand"),
    BRAUM("Braum"),
    CAITLYN("Caitlyn"),
    CAMILLE("Camille"),
    CASSIOPEIA("Cassiopeia"),
    CHOGATH("Chogath"),
    CORKI("Corki"),
    DIANA("Diana"),
    DRMUNDO("Dr. Mundo"),
    DRAVEN("Draven"),
    EKKO("Ekko"),
    ELISE("Elise"),
    EVELYNN("Evelynn"),
    EZREAL("Ezreal"),
    FIDDLESTICKS("Fiddlesticks"),
    FIORA("Fiora"),
    FIZZ("Fizz"),
    GALIO("Galio"),
    GANGPLANK("Gangplank"),
    GAREN("Garen"),
    GNAR("Gnar"),
    GRAGAS("Gragas"),
    GRAVES("Graves"),
    GWEN("Gwen"),
    HECARIM("Hecarim"),
    HEIMERDINGER("Heimerdinger"),
    ILLAOI("Illaoi"),
    IRELIA("Irelia"),
    IVERN("Ivern"),
    JANNA("Janna"),
    JARVANIV("Jarvan IV"),
    JAX("Jax"),
    JAYCE("Jayce"),
    JHIN("Jhin"),
    JINX("Jinx"),
    KAISA("Kaisa"),
    KALISTA("Kalista"),
    KARMA("Karma"),
    KARTHUS("Karthus"),
    KASSADIN("Kassadin"),
    KATARINA("Katarina"),
    KAYLE("Kayle"),
    KAYN("Kayn"),
    KENNEN("Kennen"),
    KHAZIX("Kha'Zix"),
    KINDRED("Kindred"),
    KLED("Kled"),
    KOGMAW("Kog'Maw"),
    LEBLANC("LeBlanc"),
    LEESIN("Lee Sin"),
    LEONA("Leona"),
    LILLIA("Lillia"),
    LISSANDRA("Lissandra"),
    LUCIAN("Lucian"),
    LULU("Lulu"),
    LUX("Lux"),
    MALPHITE("Malphite"),
    MALZAHAR("Malzahar"),
    MAOKAI("Maokai"),
    MASTERYI("Master Yi"),
    MISSFORTUNE("Miss Fortune"),
    MORDEKAISER("Mordekaiser"),
    MORGANA("Morgana"),
    NAMI("Nami"),
    NASUS("Nasus"),
    NAUTILUS("Nautilus"),
    NEEKO("Neeko"),
    NIDALEE("Nidalee"),
    NOCTURNE("Nocturne"),
    NUNUWILLUMP("Nunu & Willump"),
    OLAF("Olaf"),
    ORIANNA("Orianna"),
    ORNN("Ornn"),
    PANTHEON("Pantheon"),
    POPPY("Poppy"),
    PYKE("Pyke"),
    QIYANA("Qiyana"),
    QUINN("Quinn"),
    RAKAN("Rakan"),
    RAMMUS("Rammus"),
    REKSAI("Rek'Sai"),
    RELL("Rell"),
    RENATAGLASC("Renata Glasc"),
    RENEKTON("Renekton"),
    RENGAR("Rengar"),
    RIVEN("Riven"),
    RUMBLE("Rumble"),
    RYZE("Ryze"),
    SAMIRA("Samira"),
    SEJUANI("Sejuani"),
    SENNA("Senna"),
    SERAPHINE("Seraphine"),
    SETT("Sett"),
    SHACO("Shaco"),
    SHEN("Shen"),
    SHYVANA("Shyvana"),
    SINGED("Singed"),
    SION("Sion"),
    SIVIR("Sivir"),
    SKARNER("Skarner"),
    SONA("Sona"),
    SORAKA("Soraka"),
    SWAIN("Swain"),
    SYLAS("Sylas"),
    SYNDRA("Syndra"),
    TAHMKENCH("Tahm Kench"),
    TALIYAH("Taliyah"),
    TALON("Talon"),
    TARIC("Taric"),
    THRESH("Thresh"),
    TRISTANA("Tristana"),
    TRUNDLE("Trundle"),
    TRYNDAMERE("Tryndamere"),
    TWISTEDFATE("Twisted Fate"),
    TWITCH("Twitch"),
    UDYR("Udyr"),
    URGOT("Urgot"),
    VARUS("Varus"),
    VAYNE("Vayne"),
    VEIGAR("Veigar"),
    VELKOZ("Vel'Koz"),
    VEX("Vex"),
    VI("Vi"),
    VIKTOR("Viktor"),
    VIEGO("Viego"),
    VLADMIR("Vladmir"),
    VOLIBEAR("Volibear"),
    WARWICK("Warwick"),
    WUKONG("Wukong"),
    XAYAH("Xayah"),
    XERATH("Xerath"),
    XINZHAO("Xin Zhao"),
    YASUO("Yasuo"),
    YONE("Yone"),
    YORICK("Yorick"),
    YUUMI("Yuumi"),
    ZAC("Zac"),
    ZED("Zed"),
    ZERI("Zeri"),
    ZIGGS("Ziggs"),
    ZILEAN("Zilean"),
    ZOE("Zoe"),
    ZYRA("Zyra");

    // Mapping Enums to custom display value.
    private final String displayValue;

    private Champion(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}