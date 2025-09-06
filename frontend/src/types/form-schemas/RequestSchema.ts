import {z} from "zod"; 
import { contactFormBase } from "./ContactSchema";

export const requestForm = z.object({
    region: z.string().min(1, "Region is required"),
    county: z.string().min(1, "County is required"),
    area: z.string().min(1, "Area is required"),
    priceFrom: z.coerce.number().positive(),
    priceTo: z.coerce.number().positive(),
    category:z.string().min(1, "Category is required"),
    type: z.string().min(1, "Property type is required"),
    status: z.enum(["Rent", "Buy"]),
    squareMetersFrom: z.coerce.number().positive(),
    squareMetersTo: z.coerce.number().positive(),
    description: z.string().min(7)
});

export const requestSchema = requestForm.merge(contactFormBase).refine((data) => {
        if(data.password || data.confirmPassword){
            return data.password === data.confirmPassword;
        }
        return true; 
    }, {message: "Passwords don't match", path: ["confirmPassword"]});

export type requestPropertyType = z.infer<typeof requestSchema>;

export type requestOnlyType = z.infer<typeof requestForm>;