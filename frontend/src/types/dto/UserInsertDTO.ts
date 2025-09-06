
export interface UsernameDTO {
    name: string;
    surname: string;
    email: string;
    phoneNumber: string;
    contactHours: "All day" | "Morning" | "Afternoon";
    password?: string;
};