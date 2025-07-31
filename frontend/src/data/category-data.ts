
const unsortedCategories = {
    "Residential": [
        "Apartment",
        "Floor Apartment",
        "Maisonette",
        "Detached House",
        "Studio",
        "Penthouse",
        "Villa",
        "Small Studio",
        "Building",
        "Residence Complex",
        "Prefabricated House",
        "Other Residential property" ],
    "Commercial": [
        "Building",
        "Retail Store",
        "Advertising Space",
        "Commercial Property",
        "Complex",
        "Conference Room",
        "Detached House",
        "Floor Office",
        "Industrial Area",
        "Logistic Strage Space",
        "Office",
        "Parking Building",
        "Plot",
        "Showroom",
        "Small Industrial Area",
        "Warehouse",
        "Hotel",
        "Apartment",
        "Floor Apartment",
        "Other Commercial property" ],
    "Land": [
        "Agricultural Land",
        "Plot",
        "Industrial Land",
        "Land for Development",
        "Land out of City Plan",
        "Land out of Settlement",
        "Land withn Settlement",
        "Large Land",
        "Other Land" ],
    "Other Properties": [
        "Block of Apartments",
        "Garage",
        "Business",
        "Investment property",
        "Island",
        "Open ground Parking",
        "Open Parking",
        "Underground Parking",
        "Unfinished Building",
        "Air",
        "Other Property" ]
} as const;

const categories = Object.fromEntries(
    Object.entries(unsortedCategories)
        .sort(([katA], [katB]) => katA.localeCompare(katB)) // Sort by category name
        .map(([category, PropType]) => [
            category,
                [...PropType].sort((propTypeA, propTypeB)=> propTypeA.localeCompare(propTypeB)) // Sort property types within each category
            ])            
);

export {categories}; 