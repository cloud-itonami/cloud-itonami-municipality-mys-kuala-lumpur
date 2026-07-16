(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Kuala Lumpur -- the
  FORTIETH municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam, -esp-madrid, -kor-seoul, -ita-roma, -aus-sydney,
  -arg-buenos-aires, -fin-helsinki, -dnk-copenhagen, -nor-oslo,
  -bel-brussels, -chl-santiago, -col-bogota, -cri-san-jose,
  -bra-sao-paulo, -ury-montevideo, -zaf-cape-town, -ecu-quito,
  -swe-gothenburg, -pry-asuncion, -mex-guadalajara, -fra-lyon,
  -ind-new-delhi, -pol-warsaw, -ken-nairobi, -tha-bangkok, -are-abu-dhabi,
  -vnm-hanoi, -idn-jakarta, -phl-manila, -egy-cairo, -tur-ankara,
  -nga-abuja, -sau-riyadh for the first thirty-nine) per
  ADR-2607141700 (cloud-itonami-compliance-fact-federation). Malaysia's
  first entry across any of the 3 axes, and the axis's fifth ASEAN
  entry (after Bangkok, Hanoi, Jakarta, Manila).

  Malaysia operates a stable, SETTLED dual-capital arrangement (unlike
  Egypt/Indonesia's ongoing transitions): Kuala Lumpur remains the
  constitutional/national/royal capital (seat of the Yang di-Pertuan
  Agong and Parliament) per Article 154 of the Federal Constitution,
  while Putrajaya has been the separate administrative/judicial
  capital since 1999/2003 -- this arrangement has been stable for over
  two decades, not an unresolved transition, and Wikidata's P36 for
  Malaysia correctly lists Kuala Lumpur.

  Federal Capital Act 1960 (Act 190) -- title, act number, and 1 April
  1961 commencement date directly confirmed by reading the official
  'Laws of Malaysia' reprint text (published by the Commissioner of
  Law Revision, Malaysia) hosted at simplymalaysia.wordpress.com via
  the Read-tool saved-path fallback (WebFetch itself reported the PDF
  as illegible/binary); the founding law that designated Kuala Lumpur
  as the Federal Capital and established its local-government
  Commissioner.

  City of Kuala Lumpur Act 1971 (Act 59) -- title, act number, and 1
  February 1972 commencement date directly confirmed via the same
  official-reprint mirror, its first page reading verbatim: 'An Act to
  make provision for changes in title and other matters consequential
  on the conferment of the status of a City on the Municipality of the
  Federal Capital of Kuala Lumpur. [1 February 1972]' -- the law that
  elevated Kuala Lumpur to city status and renamed its governing
  corporation to Dewan Bandaraya Kuala Lumpur (DBKL), both directly
  confirmed by DBKL's own official domain 'Legislation List' page
  first, before verifying exact dates via the primary text.

  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"kuala-lumpur"
   [{:ordinance/id "kuala-lumpur.act-190-1960-federal-capital-act"
     :ordinance/title "Federal Capital Act 1960"
     :ordinance/municipality "kuala-lumpur"
     :ordinance/country "MYS"
     :ordinance/kind :local-act
     :ordinance/number "Act 190"
     :ordinance/url "https://simplymalaysia.wordpress.com/wp-content/uploads/2011/10/act-190-federal-capital-act-1960.pdf"
     :ordinance/url-provenance :laws-of-malaysia-official-reprint-mirror
     :ordinance/enacted-date "1961-04-01"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:governance}}
    {:ordinance/id "kuala-lumpur.act-59-1971-city-of-kuala-lumpur-act"
     :ordinance/title "City of Kuala Lumpur Act 1971"
     :ordinance/municipality "kuala-lumpur"
     :ordinance/country "MYS"
     :ordinance/kind :local-act
     :ordinance/number "Act 59"
     :ordinance/url "https://simplymalaysia.wordpress.com/wp-content/uploads/2011/10/act-59-city-of-kuala-lumpur-act-1971.pdf"
     :ordinance/url-provenance :laws-of-malaysia-official-reprint-mirror
     :ordinance/enacted-date "1972-02-01"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:governance}}]})

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
      :note (str "cloud-itonami-municipality-mys-kuala-lumpur Wave 0 (ADR-2607141700): "
                 (count (get catalog "kuala-lumpur")) " Kuala Lumpur entries seeded "
                 "with Laws of Malaysia official-reprint citations. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
