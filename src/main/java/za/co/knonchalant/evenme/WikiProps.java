package za.co.knonchalant.evenme;

import java.util.HashMap;
import java.util.Map;

public enum WikiProps {
    cites_work("P2860"),
    series_ordinal("P1545"),
    author_name_string("P2093"),
    instance_of("P31"),
    stated_in("P248"),
    retrieved("P813"),
    reference_URL("P854"),
    PubMed_ID("P698"),
    title("P1476"),
    publication_date("P577"),
    published_in("P1433"),
    page("P304"),
    volume("P478"),
    apparent_magnitude("P1215"),
    astronomical_filter("P1227"),
    issue("P433"),
    catalog_code("P528"),
    DOI("P356"),
    catalog("P972"),
    author("P50"),
    main_subject("P921"),
    language_of_work_or_name("P407"),
    country("P17"),
    PMCID("P932"),
    located_in_the_administrative_territorial_entity("P131"),
    point_in_time("P585"),
    object_stated_as("P1932"),
    of("P642"),
    proper_motion("P2215"),
    determination_method("P459"),
    occupation("P106"),
    coordinate_location("P625"),
    sex_or_gender("P21"),
    SIMBAD_ID("P3083"),
    right_ascension("P6257"),
    declination("P6258"),
    epoch("P6259"),
    start_time("P580"),
    Google_Knowledge_Graph_ID("P2671"),
    constellation("P59"),
    found_in_taxon("P703"),
    based_on_heuristic("P887"),
    given_name("P735"),
    VIAF_ID("P214"),
    parallax("P2214"),
    date_of_birth("P569"),
    Wikimedia_import_URL("P4656"),
    subject_named_as("P1810"),
    imported_from_Wikimedia_project("P143"),
    radial_velocity("P2216"),
    ResearchGate_publication_ID("P5875"),
    country_of_citizenship("P27"),
    Freebase_ID("P646"),
    part_of("P361"),
    image("P18"),
    ortholog("P684"),
    end_time("P582"),
    family_name("P734"),
    GeoNames_ID("P1566"),
    parent_taxon("P171"),
    taxon_name("P225"),
    taxon_rank("P105"),
    Commons_category("P373"),
    Entrez_Gene_ID("P351"),
    distance_from_Earth("P2583"),
    chromosome("P1057"),
    subclass_of("P279"),
    exact_match("P2888"),
    collection("P195"),
    place_of_birth("P19"),
    described_by_source("P1343"),
    date_of_death("P570"),
    Elo_rating("P1087"),
    GNS_Unique_Feature_ID("P2326"),
    location("P276"),
    UniProt_protein_ID("P352"),
    GBIF_taxon_ID("P846"),
    inception("P571"),
    population("P1082"),
    languages_spoken_written_or_signed("P1412"),
    category_combines_topics("P971"),
    educated_at("P69"),
    ORCID_iD("P496"),
    applies_to_jurisdiction("P1001"),
    heritage_designation("P1435"),
    GND_ID("P227"),
    full_work_available_at_URL("P953"),
    follows("P155"),
    followed_by("P156"),
    located_in_time_zone("P421"),
    postal_code("P281"),
    has_part_or_parts("P527"),
    sport("P641"),
    WorldCat_Identities_ID("P7859"),
    copyright_status("P6216"),
    official_website("P856"),
    official_name("P1448"),
    pronunciation("P7243"),
    employer("P108"),
    member_of_sports_team("P54");

    static final Map<String, WikiProps> LOOKUP = new HashMap<>();

    static {
        for (WikiProps value : values()) {
            LOOKUP.put(value.propertyType, value);
        }
    }

    private final String propertyType;

    WikiProps(String propertyType) {
        this.propertyType = propertyType;
    }

    public static WikiProps get(String id) {
        return LOOKUP.get(id);
    }
}
