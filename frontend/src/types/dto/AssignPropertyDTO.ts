
export interface AssignPropertyDTO {
    regionId: number;
    countyId: number;
    areaId: number;
    categoryId: number;
    typeId: number;
    type: "Sale" | "Rent";
    status: "AVAILABLE" | "UNOBSERVED" | "RESERVED" | "SOLD" | "RENTED" | "MATCHED" | "UNMATCHED"; //PropertyStatusEnum
    description: string;
    price: number;
    squareMeters: number;
    street: string;
    streetNumber: string;
    postCode?: string;
};

