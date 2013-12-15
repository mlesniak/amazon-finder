package com.mlesniak.amazon.backend;

import java.util.Arrays;
import java.util.List;

public enum SearchIndex {
    Appareal,
    Automotive,
    Baby,
    Beauty,
    Books,
    Classical,
    DVD,
    Electronics,
    ForeignBooks,
    Grocery,
    HealthPersonalCare,
    HomeGarden,
    Jewelry,
    KindleStore,
    Kitchen,
    Lighting,
    Magazines,
    Marketplace,
    MP3Downloads,
    Music,
    MusicalInstruments,
    MusicTracks,
    OfficeProducts,
    OutdoorLiving,
    Outlet,
    PCHardware,
    Photo,
    Shoes,
    Software,
    SoftwareVideoGames,
    SportingGoods,
    Tools,
    Toys,
    VHS,
    Video,
    VideoGames,
    Watches;

    public static List<SearchIndex> asList() {
        return Arrays.asList(values());
    }
}