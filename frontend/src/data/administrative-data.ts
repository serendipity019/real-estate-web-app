
const unsortedRegionData = {
    Attiki: {
        "Athens Central": ["Athens", "Nea Philadelphia", "Zografou", "Vyronas", "Galatsi", "Kaisariani", "Dafni", "Ymittos", "Nea Chalkidon", "Ilioupoli"],
        "Piraeus": ["Piraeus", "Keratsini", "Drapetsona", "Perama", "Korydallos", "Agios Ioannis Rentis"],    
    },
    "Central Macedonia": {
        "Thessaloniki": ["Thessaloniki", "Kalamaria", "Neapoli", "Pilea", "Pylaia", "Thermi", "Halkidona", "Evosmos", "Menemeni", "Ampelokipoi", "Thermaikos", "Delta", "Oraiokastro","Langadas", ],
        "Serres": ["Serres", "Sintiki", "Nea Zichni", "Visaltia", "Irakleia", "Emmanouil Pappas", "Amphipolis"],
    },
} as const;

// export const regionData: Record<string, Record<string, string[]>> = Object.fromEntries(
//     Object.entries(unsortedRegionData).map(([region, counties]) => [
//         region,
//         Object.fromEntries(
//             Object.entries(counties).map(([county, areas]) => [county, areas.sort()])
//         ).sort(),
//     ])
// );

const regionData = Object.fromEntries(
  Object.entries(unsortedRegionData)
    .sort(([regionA], [regionB]) => regionA.localeCompare(regionB)) // Sort regions alphabetically
    .map(([region, counties]) => [
      region,
      Object.fromEntries(
        Object.entries(counties)
          .sort(([countyA], [countyB]) => countyA.localeCompare(countyB)) // Sort counties alphabetically
          .map(([county, areas]) => [
            county,
            [...areas].sort((areaA, areaB) => areaA.localeCompare(areaB)), // Sort areas alphabetically
          ])
      ),
    ])
);  

export { regionData };