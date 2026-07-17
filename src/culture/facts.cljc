(ns culture.facts
  "Regional-culture catalog for Kuala Lumpur -- local dishes, protected
  products, beverages, festivals and heritage sites, piggybacked onto this
  municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"kuala-lumpur"
   [{:culture/id "kuala-lumpur.dish.nasi-lemak"
     :culture/name "Nasi lemak"
     :culture/municipality "kuala-lumpur"
     :culture/country "MYS"
     :culture/kind :dish
     :culture/summary "Coconut rice dish considered the national dish of Malaysia, sold across the country including Kuala Lumpur rather than being city-specific."
     :culture/url "https://en.wikipedia.org/wiki/Nasi_lemak"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kuala-lumpur.dish.hokkien-mee"
     :culture/name "Hokkien mee (Kuala Lumpur style)"
     :culture/municipality "kuala-lumpur"
     :culture/country "MYS"
     :culture/kind :dish
     :culture/summary "The Kuala Lumpur variant of Hokkien mee, called Hokkien char mee: thick yellow noodles braised in thick dark soy sauce with pork, squid, fish cake and cabbage."
     :culture/url "https://en.wikipedia.org/wiki/Hokkien_mee"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kuala-lumpur.dish.bak-kut-teh"
     :culture/name "Bak kut teh"
     :culture/municipality "kuala-lumpur"
     :culture/country "MYS"
     :culture/kind :dish
     :culture/summary "Pork rib dish cooked in broth, popularly served in Malaysia and Singapore; in Malaysia it is popularly associated with Klang, which locals believe to be its place of origin."
     :culture/url "https://en.wikipedia.org/wiki/Bak_kut_teh"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kuala-lumpur.dish.roti-canai"
     :culture/name "Roti canai"
     :culture/municipality "kuala-lumpur"
     :culture/country "MYS"
     :culture/kind :dish
     :culture/summary "Unleavened flatbread served with dal or other curries, particularly popular in Malaysia as a breakfast item; national rather than Kuala Lumpur-specific."
     :culture/url "https://en.wikipedia.org/wiki/Roti_canai"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kuala-lumpur.beverage.teh-tarik"
     :culture/name "Teh tarik"
     :culture/municipality "kuala-lumpur"
     :culture/country "MYS"
     :culture/kind :beverage
     :culture/summary "Hot 'pulled' milk tea considered the unofficial national drink of Malaysia, nominated by Malaysia for UNESCO intangible-cultural-heritage recognition."
     :culture/url "https://en.wikipedia.org/wiki/Teh_tarik"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kuala-lumpur.craft.royal-selangor-pewter"
     :culture/name "Royal Selangor pewter"
     :culture/municipality "kuala-lumpur"
     :culture/country "MYS"
     :culture/kind :craft
     :culture/summary "Pewter tableware, gifts and jewellery from Royal Selangor, founded in 1885 and headquartered in Kuala Lumpur, the largest pewter manufacturer of its type in the world."
     :culture/url "https://en.wikipedia.org/wiki/Royal_Selangor"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kuala-lumpur.festival.thaipusam"
     :culture/name "Thaipusam"
     :culture/municipality "kuala-lumpur"
     :culture/country "MYS"
     :culture/kind :festival
     :culture/summary "Tamil Hindu festival commemorating the deity Murugan; in Malaysia it attracts thousands of people for elaborate festivities at Batu Caves near Kuala Lumpur."
     :culture/url "https://en.wikipedia.org/wiki/Thaipusam"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kuala-lumpur.heritage.sultan-abdul-samad-building"
     :culture/name "Sultan Abdul Samad Building"
     :culture/name-local "Bangunan Sultan Abdul Samad"
     :culture/municipality "kuala-lumpur"
     :culture/country "MYS"
     :culture/kind :heritage
     :culture/summary "Late-19th-century building in Kuala Lumpur (built 1894-1897) in Indo-Saracenic style with a 41 m clock tower topped by copper domes."
     :culture/url "https://en.wikipedia.org/wiki/Sultan_Abdul_Samad_Building"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kuala-lumpur.heritage.batu-caves"
     :culture/name "Batu Caves"
     :culture/municipality "kuala-lumpur"
     :culture/country "MYS"
     :culture/kind :heritage
     :culture/summary "Series of limestone caves with Hindu temples, the most popular a shrine to Murugan, about 13 km north of Kuala Lumpur in Gombak, Selangor."
     :culture/url "https://en.wikipedia.org/wiki/Batu_Caves"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-mys-kuala-lumpur culture catalog "
                 "(ADR-2607171400): " (count (get catalog "kuala-lumpur"))
                 " Kuala Lumpur entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
